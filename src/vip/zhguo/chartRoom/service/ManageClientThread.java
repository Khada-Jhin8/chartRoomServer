package vip.zhguo.chartRoom.service;

import java.net.Socket;
import java.util.HashMap;

public class ManageClientThread {
    private static HashMap<String, ServerConnectClientThread> map = new HashMap();

    public static void addServerConnectClientThread(String key, ServerConnectClientThread scct) {
        map.put(key, scct);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String key) {
        return map.get(key);
    }


}
