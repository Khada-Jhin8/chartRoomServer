package vip.zhguo.chartRoom.service;

import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class ManageClientThread {
    private static HashMap<String, ServerConnectClientThread> map = new HashMap();

    public static void addServerConnectClientThread(String key, ServerConnectClientThread scct) {
        map.put(key, scct);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String key) {
        return map.get(key);
    }

    public static void removeServerConnectClientThread(String key) {
        ServerConnectClientThread remove = map.remove(key);
    }

    public static String getMembers() {
        Set<String> keySet = map.keySet();
        String members = "";
        for (String key : keySet) {
//            members+=","+key 该写法导致split存储是头部会有个空值
            members += key + ",";
        }
        return members;
    }


}
