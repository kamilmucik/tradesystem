package com.gft.model;

/**
 * Created by kamu on 8/12/2016.
 */
public class ResponseBean {

    private String sessionId;

    public ResponseBean(String sessionId){
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
