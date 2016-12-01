package com.gft.messaging;

import com.gft.dto.GetSocketPackageResponse;
import com.gft.dto.Parent;
import com.gft.model.JMSCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by kamu on 8/19/2016.
 */
@Component
public class MessageBase {

    static final Logger LOG = LoggerFactory.getLogger(MessageBase.class);

    private ConcurrentHashMap<String ,CompletableFuture> futures = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String ,Long> customers = new ConcurrentHashMap<>();

    private ConcurrentSkipListSet<JMSCustomer> jmsCustomers = new ConcurrentSkipListSet<>();

    @Autowired(required = false)
    private JmsTemplate jmsTemplate;

    public ConcurrentHashMap<String, CompletableFuture> getFutures() {
        return futures;
    }

    public void setFutures(ConcurrentHashMap<String, CompletableFuture> futures) {
        this.futures = futures;
    }

    public ConcurrentSkipListSet<JMSCustomer> getJmsCustomers() {return jmsCustomers;}

    public void setJmsCustomers(ConcurrentSkipListSet<JMSCustomer> jmsCustomers) {this.jmsCustomers = jmsCustomers;}

    public ConcurrentHashMap<String, Long> getCustomers() {return customers;}

    public void setCustomers(ConcurrentHashMap<String, Long> customers) {this.customers = customers;}

    public void send(Parent object){
        // TODO: here is place to set destination queue, filtered by product id and backend instance
        //jmsTemplate.convertAndSend("MT.TO.BACK1.QUEUE", object);

        jmsTemplate.send("MT.TO.BACK1.QUEUE", new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(object);
                objectMessage.setJMSCorrelationID(object.getCorrelationId());
                return objectMessage;
            }
        });
    }
}
