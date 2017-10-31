package com.delivery.seproject2.Data;

/**
 * Created by Waithid on 10/30/2017.
 */

public class User {

    private String User;
    private String tel;
    private String uid;
    private  String gendar;


    public User() {

    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGendar() {
        return gendar;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }
}
