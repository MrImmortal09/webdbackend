package com.erp.erptool.auth.models;

public class JwtRequestModel {
    String userName;
    String password;

    public JwtRequestModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public JwtRequestModel() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}