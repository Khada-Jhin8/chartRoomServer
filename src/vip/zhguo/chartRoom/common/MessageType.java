package vip.zhguo.chartRoom.common;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCESS = "1";//登陆成功
    String MESSAGE_LOGIN_FAIL = "2";//登陆失败
    String MESSAGE_GET_ONLINE_FRIENDS = "3";//获取在线用户列表
    String MESSAGE_RETURN_ONLINE_FRIENDS = "4";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "5";//客户端推出
    String MESSAGE_COMMON_MSG = "6"; // 普通消息类型，即聊天包
    String MESSAGE_TO_ALL_MSG = "7";// 群发消息
    String MESSAGE_FILE_MSG = "8"; //发送文件
    String MESSAGE_GET_OFFLINE_MSG = "9";//获取离线消息
}
