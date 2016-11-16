package com.gft.dto;

import java.io.Serializable;

/**
 * Model represent user in REST comunication.
 * Created by kamu on 8/12/2016.
 */
public class User implements Serializable {

    private String login;

    private String password;

    private String sessionId;

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.sessionId = "";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
