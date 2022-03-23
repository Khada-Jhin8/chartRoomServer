package vip.zhguo.chartRoom;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID= 1L;
    private String sender;
    private String getter;
    private String content;
    private String messagerType;
    private String sendTime;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessagerType() {
        return messagerType;
    }

    public void setMessagerType(String messagerType) {
        this.messagerType = messagerType;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
