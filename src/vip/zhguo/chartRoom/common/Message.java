package vip.zhguo.chartRoom.common;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID= 1L;
    private String sender;
    private String getter;
    private String content;
    private String messagerType;
    private String sendTime;
    private byte[] filedata;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
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
