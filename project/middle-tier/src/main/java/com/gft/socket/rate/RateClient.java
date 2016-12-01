package com.gft.socket.rate;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by kamu on 2016-09-27.
 */
public class RateClient {

    private final Object monitor = new Object();

    private final int id;

    private String email;

    private final WebSocketSession session;

    public RateClient(int id, WebSocketSession session) {
        this.id = id;
        this.session = session;
    }

    protected void sendMessage(String msg) throws Exception {
        this.session.sendMessage(new TextMessage(msg));
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public WebSocketSession getSession() {return session;}
}
