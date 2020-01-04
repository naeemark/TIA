package com.zaeem.tia.model;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String forGender;

    public User(String username, String password, String forGender) {
        this.username = username;
        this.password = password;
        this.forGender = forGender;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "password='" + password + '\'' +
                "forGender='" + forGender + '\'' +
                '}';
    }
}
