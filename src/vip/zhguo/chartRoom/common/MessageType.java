package vip.zhguo.chartRoom.common;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCESS = "1";//登陆成功
    String MESSAGE_LOGIN_FAIL = "2";//登陆失败
    String MESSAGE_GET_ONLINE_FRIENDS = "3";//获取在线用户列表
    String MESSAGE_RETURN_ONLINE_FRIENDS = "4";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "5";//客户端推出
}
