package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;
import vip.zhguo.chartRoom.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService {
    private ServerSocket serverSocket = null;

    public ServerService() {
        try {
            // 在9999端口监听
            serverSocket = new ServerSocket(9999);

            // 循环监听
            while (true) {
                Socket socket = serverSocket.accept();
                // 拿到输入输出流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();
                // 向下转型判断用户名和密码
                User user = (User) ois.readObject();
                if (user.getuID().equals("zhenghg") && user.getPassword().equals("123456")) {
                    // 返回登陆成功
                    message.setMessagerType(MessageType.MESSAGE_LOGIN_SUCCESSD);
                    oos.writeObject(message);
                    // 创建与客户端保持连接的线程
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, user.getuID());
                    serverConnectClientThread.start();
                    // 将该线程保存到集合中，便于后续使用
                    ManageClientThread.addServerConnectClientThread(user.getuID(), serverConnectClientThread);
                } else {
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

