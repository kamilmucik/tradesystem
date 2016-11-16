package com.gft.messaging;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
/**
 * Created by kamu on 8/18/2016.
 */
@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(Inventory inventory) {
        jmsTemplate.send(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException{
                ObjectMessage objectMessage = session.createObjectMessage(inventory);
                objectMessage.setJMSCorrelationID(inventory.getId());
                return objectMessage;
            }
        });
    }

}
