package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-09-05.
 */
public class GetUserDetailsRequest extends Parent implements Serializable {

    private String login;

    public GetUserDetailsRequest(String login, Integer notificationCode){
        this.login = login;
        this.notificationCode = notificationCode;
    }

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    @Override
    public String toString() {
        return "GetUserDetailsRequest [login=" + login
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
