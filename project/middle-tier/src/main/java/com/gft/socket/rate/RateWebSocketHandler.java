package com.gft.socket.rate;

import com.gft.messaging.MessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kamu on 2016-09-27.
 */
@Controller
public class RateWebSocketHandler extends TextWebSocketHandler {

    private static Logger LOG = LoggerFactory.getLogger(RateWebSocketHandler.class);

    private static final AtomicInteger clientIds = new AtomicInteger(0);
    private static final Random random = new Random();

    private final int id;
    private RateClient client;

    @Autowired
    MessageBase messageBase;

    public RateWebSocketHandler() {
        this.id = clientIds.getAndIncrement();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.client = new RateClient(this.id, session);
        RateTimer.addClient(this.client);
        RateTimer.broadcast(
                String.format("{'type': 'join','data':[%s]}", "")
        );
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        for (Iterator<RateClient> iterator = RateTimer.getClients().iterator(); iterator.hasNext();) {
            RateClient client = iterator.next();
            if (client.getSession().getId().equals(session.getId())) {
                String[] split = payload.split(":");
                client.setEmail("" + split[1]);
            }
        }
        LOG.debug("payload: {}",payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        RateTimer.removeClient(this.client);
        RateTimer.broadcast(
                String.format("{'type': 'leave', 'id': %d}", Integer.valueOf(this.id))
        );
    }
}
