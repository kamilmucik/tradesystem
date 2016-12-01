package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-09-01.
 */
public class Parent implements Serializable {

    private static final long serialVersionUID = 5626740758276999372L;

    public final static int MESSAGE_CODE_CORRECT = 211;

    public final static int MESSAGE_CODE_INCORRECT = 2;

    protected String correlationId;

    protected Integer notificationCode;

    protected String notification;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Integer getNotificationCode() {return notificationCode;}

    public void setNotificationCode(Integer notificationCode) {this.notificationCode = notificationCode;}

    public String getNotification() {return notification;}

    public void setNotification(String notification) {this.notification = notification;}
}
