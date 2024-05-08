package com.erp.erptool.auth.models;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityModel implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private String authority;

    public AuthorityModel(String authority) {
        super();
        this.authority = authority;
    }

    @Override
    public String getAuthority() {

        // TODO Auto-generated method stub
        return null;
    }
}