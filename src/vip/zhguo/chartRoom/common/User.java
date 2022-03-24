package vip.zhguo.chartRoom.common;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID= 1L;
    private String uID;
    private String password;

    public User(String uID, String password) {
        this.uID = uID;
        this.password = password;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
