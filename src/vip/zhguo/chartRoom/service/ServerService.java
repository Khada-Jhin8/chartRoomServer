package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;
import vip.zhguo.chartRoom.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerService {
    private ServerSocket serverSocket = null;
    private static HashMap<String, User> userMap = new HashMap<>();

    //使用hashmap装用户，伪装数据库
    static {
        userMap.put("zhangsan", new User("zhangsan", "123456"));
        userMap.put("lisi", new User("lisi", "123456"));
        userMap.put("wangwu", new User("wangwu", "123456"));
        userMap.put("zhaoliu", new User("zhaoliu", "123456"));
        userMap.put("zhenghg", new User("zhenghg", "123456"));
    }

    public boolean cheackUserInfo(User user) {
        boolean flag = false;
        User userValue = userMap.get(user.getuID());
        if (userValue == null) {
            return flag;
        }
        if (userValue.getPassword().equals(user.getPassword())) {
            flag = true;
        }
        return flag;


    }

    public ServerService() {
        try {
            // 在9999端口监听
            serverSocket = new ServerSocket(9999);
            System.out.println("监听在9999端口");
            // 循环监听
            while (true) {
                Socket socket = serverSocket.accept();
                // 拿到输入输出流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();
                // 向下转型判断用户名和密码
                User user = (User) ois.readObject();
                if (cheackUserInfo(user)) {
                    // 返回登陆成功
                    message.setMessagerType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    oos.writeObject(message);
                    // 创建与客户端保持连接的线程
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, user.getuID());
                    serverConnectClientThread.start();
                    // 将该线程保存到集合中，便于后续使用
                    ManageClientThread.addServerConnectClientThread(user.getuID(), serverConnectClientThread);
                } else {
                    System.out.println("客户端" + user.getuID() + "验证失败");
                    message.setMessagerType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    if (socket != null) socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

