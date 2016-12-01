package com.gft.dto.model;

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

    public User(String login, String password, String sessionId){
        this.login = login;
        this.password = password;
        this.sessionId = sessionId;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }
}
