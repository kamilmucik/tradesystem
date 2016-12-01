package com.gft.socket.rate;

import com.gft.dto.GetSocketPackageRequest;
import com.gft.dto.GetSocketPackageResponse;
import com.gft.messaging.MessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by kamu on 2016-09-27.
 */
@Controller
public class RateTimer {

    static final Logger LOG = LoggerFactory.getLogger(RateTimer.class);

    private static final long TICK_DELAY = 5000;

    private static final Object MONITOR = new Object();

    private static final ConcurrentHashMap<Integer, RateClient> clients = new ConcurrentHashMap<Integer, RateClient>();

    private static Timer rateTimer = null;

    static MessageBase messageBase;

    @Autowired
    void setMessageBase(MessageBase messageBase) {
        RateTimer.messageBase = messageBase;
    }

    public static void addClient(RateClient client) {
        synchronized (MONITOR) {
            if (clients.isEmpty()) {
                startTimer();
            }
            clients.put(Integer.valueOf(client.getId()), client);
        }
    }

    public static void removeClient(RateClient client) {
        synchronized (MONITOR) {
            clients.remove(Integer.valueOf(client.getId()));
            if (clients.isEmpty()) {
                stopTimer();
            }
        }
    }

    public static void broadcast(String message) throws Exception {
        Collection<RateClient> rateClients = new CopyOnWriteArrayList<>(RateTimer.getClients());
        for (RateClient client : rateClients) {
            try {
                client.sendMessage(message);
            } catch (Throwable ex) {
                // if Snake#sendMessage fails the client is removed
                LOG.error(ex.getMessage());
            }
        }
    }

    public static void send(String sessionId, String message) throws Exception {
        Collection<RateClient> rateClients = new CopyOnWriteArrayList<>(RateTimer.getClients());
        for (RateClient client : rateClients) {
            if (sessionId.equals(client.getSession().getId()))
                try {
                    client.sendMessage(message);
                } catch (Throwable ex) {
                    // if Snake#sendMessage fails the client is removed
                    LOG.error(ex.getMessage());
                }
        }
    }

    public static void tick() throws Exception {
        GetSocketPackageRequest request = new GetSocketPackageRequest();

        Collection<RateClient> rateClients = new CopyOnWriteArrayList<>(RateTimer.getClients());
        for (RateClient client : rateClients) {
            try {
                request.getUserEmailList().add(client.getEmail());
                request.getUserMap().put(client.getSession().getId(),client.getEmail());
            } catch (Throwable ex) {
                LOG.error(ex.getMessage());
            }
        }

        request.setCorrelationId(UUID.randomUUID().toString());

        messageBase.send(request);
    }

    public static void startTimer() {
        rateTimer = new Timer(RateTimer.class.getSimpleName() + " Timer");
        rateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (Throwable ex) {
                    LOG.error("Caught to prevent timer from shutting down", ex);
                }
            }
        }, TICK_DELAY, TICK_DELAY);
    }

    public static void stopTimer() {
        if (rateTimer != null) {
            rateTimer.cancel();
        }
    }

    public static Collection<RateClient> getClients() {
        return Collections.unmodifiableCollection(clients.values());
    }


}
