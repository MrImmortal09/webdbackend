package com.erp.erptool.auth.models;

public class JwtResponseModel {

    String token;

    public JwtResponseModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public JwtResponseModel(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}