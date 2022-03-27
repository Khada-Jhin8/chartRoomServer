package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * 与客户端保持连接线程
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String uId;

    public Socket getSocket() {
        return socket;
    }

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


                } else if (message.getMessagerType().equals(MessageType.MESSAGE_TO_ALL_MSG)) {
                    String onlineMembers = ManageClientThread.getMembers();
                    ObjectOutputStream oos = null;
                    String[] member = onlineMembers.split(",");
                    for (String m : member
                    ) {
                        oos = new ObjectOutputStream(ManageClientThread.getServerConnectClientThread(m.trim())
                                .getSocket()
                                .getOutputStream());
                        oos.writeObject(message);
                    }
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_COMMON_MSG)) {
                    System.out.println();
                    System.out.println(message.getSendTime() + ":将<" + message.getSender() + "> 的消息转发给 <" + message.getGetter() + "> 内容为\"" + message.getContent() + "\"");

                    // 判断接收者是否在线，如果不在线给通知发送者。
                    if (ManageClientThread.getServerConnectClientThread(message.getGetter()) == null) {
//                        ObjectOutputStream oos1 = new ObjectOutputStream(ManageClientThread.getServerConnectClientThread(message.getSender())
//                                .getSocket()
//                                .getOutputStream());
//                        Message message1 = new Message();
//                        message1.setMessagerType(MessageType.MESSAGE_COMMON_MSG);
//                        message1.setContent("您发送的对象不在线，发送失败。");
//                        oos1.writeObject(message1);
//                         优化上面代码，将不在线的用户消息缓存到的服务器，等待上线后发送。
                        ManageOffLineMessage.addOfflienMessage(message.getGetter(),message);
                    } else {
                        ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerConnectClientThread(message.getGetter())
                                .getSocket()
                                .getOutputStream());
                        oos.writeObject(message);
                    }
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_FILE_MSG)) {
                    System.out.println("传文件了");
                    String getter = message.getGetter();
                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerConnectClientThread(getter)
                            .getSocket()
                            .getOutputStream());
                    oos.writeObject(message);
                }else if (message.getMessagerType().equals(MessageType.MESSAGE_GET_OFFLINE_MSG)){
                    List<Message> offLineMessage = ManageOffLineMessage.getOffLineMessage(message.getSender());
                    if (offLineMessage == null)
                        continue;
                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerConnectClientThread(message.getSender())
                            .getSocket()
                            .getOutputStream());
                    Message message1 = new Message();
                    message1.setMessagerType(MessageType.MESSAGE_GET_OFFLINE_MSG);
                    message1.setOffLineMsgs(offLineMessage);
                    oos.writeObject(message1);

                } else if (message.getMessagerType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(message.getSender() + "断开了与系统的连接");
                    ManageClientThread.removeServerConnectClientThread(uId);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
