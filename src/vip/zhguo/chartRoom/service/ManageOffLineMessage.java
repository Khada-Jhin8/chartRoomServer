package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageOffLineMessage {
    private static HashMap<String, List<Message>> offMessageList = new HashMap<>();

    public static void addOfflienMessage(String uId, Message message) {
        if (offMessageList.get(uId) == null) {
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message);
            offMessageList.put(uId, messages);
        } else {
            List<Message> messages = offMessageList.get(uId);
            messages.add(message);
        }
    }
    public static List<Message> getOffLineMessage(String uId){
        return offMessageList.get(uId);
    }

}
