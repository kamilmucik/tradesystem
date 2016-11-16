package com.gft.messaging;

import javax.jms.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 8/18/2016.
 */
@Component
public class MessageSender  {

    static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MessageBase messageBase;

    public <T, M extends Serializable> CompletableFuture<T> sendMessage(CompletableFuture<T> completableFuture, Inventory inventory) {
        LOG.debug("inventory[{}]",inventory.getId() );
        messageBase.getFutures().put(inventory.getId(), completableFuture);
            jmsTemplate.send("MT.TO.BACK1.QUEUE", new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException{
                    ObjectMessage objectMessage = session.createObjectMessage(inventory);
                    objectMessage.setJMSCorrelationID(inventory.getId());
                    return objectMessage;
                }
            });
        return completableFuture;

    }

}