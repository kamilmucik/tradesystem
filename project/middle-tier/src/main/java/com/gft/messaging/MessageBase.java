package com.gft.messaging;

import com.gft.model.JMSCustomer;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by kamu on 8/19/2016.
 */
@Component
public class MessageBase {

    private ConcurrentHashMap<String ,CompletableFuture> futures = new ConcurrentHashMap<>();

    private ConcurrentSkipListSet<JMSCustomer> jmsCustomers = new ConcurrentSkipListSet<>();

    public ConcurrentHashMap<String, CompletableFuture> getFutures() {
        return futures;
    }

    public void setFutures(ConcurrentHashMap<String, CompletableFuture> futures) {
        this.futures = futures;
    }
}
