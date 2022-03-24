package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *与客户端保持连接线程
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String uId;


    public ServerConnectClientThread(Socket socket, String uId) {
        this.socket = socket;
        this.uId = uId;
    }

    @Override
    public void run() {
        // 与客户端保持连接
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                System.out.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
