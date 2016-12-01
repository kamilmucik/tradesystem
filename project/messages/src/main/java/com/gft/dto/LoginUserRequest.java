package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-08-30.
 */
public class LoginUserRequest extends Parent implements Serializable  {

    private String login;

    private String password;

    public LoginUserRequest(String login, String password){
        this.login = login;
        this.password = password;
    }

    public LoginUserRequest(String login, String password, Integer notificationCode, String notification){
        this.login = login;
        this.password = password;
        this.notificationCode = notificationCode;
        this.notification = notification;
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

    @Override
    public String toString() {
        return "LoginUserRequest [login=" + login
                + ", password=" + password
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
