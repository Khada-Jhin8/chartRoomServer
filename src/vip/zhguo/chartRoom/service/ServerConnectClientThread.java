package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 与客户端保持连接线程
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String uId;


    public ServerConnectClientThread(Socket socket, String uId) {
        this.socket = socket;
        this.uId = uId;
    }

    @Override
    public void run() {
        // 与客户端保持连接
        while (true) {
            try {
                System.out.println("与客户端" + uId + "保持连接");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
//                System.out.println(message.getMessagerType());
                if (message.getMessagerType().equals(MessageType.MESSAGE_GET_ONLINE_FRIENDS)) {
                    //需要返回的在线用户列表
                    String members = ManageClientThread.getMembers();
                    System.out.println(members);
                    //封装返回的数据
//                    Message return_message = (Message) ois.readObject();//写错了，致命错误
                    Message return_message = new Message();
                    return_message.setMessagerType(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS);
                    return_message.setContent(members);
                    //回包
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(return_message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
