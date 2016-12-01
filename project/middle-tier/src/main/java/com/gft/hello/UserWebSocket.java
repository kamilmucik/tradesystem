package com.gft.hello;

import com.gft.messaging.MessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by Kamil Bemowski on 2016-06-29.
 */
public class UserWebSocket extends TextWebSocketHandler {

    static final Logger LOG = LoggerFactory.getLogger(UserWebSocket.class);

    @Autowired
    MessageBase messageBase;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOG.debug("messageBase: {}" , messageBase);
        System.out.println("connected");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
            throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
    }

}
