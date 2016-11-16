package com.gft.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import com.gft.bean.InventoryResource;
import com.gft.dto.Product;
import com.gft.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamu on 8/18/2016.
 */
@Component
public class MessageReceiver implements MessageListener {

    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    MessageConverter messageConverter;

    @Autowired
    MessageBase messageBase;

    @Override
    public void onMessage(Message message) {
        LOG.debug("onMessage: "+ message);
        String correlationId = null;
        try {
            Inventory inventory = (Inventory) messageConverter.fromMessage(message);
            LOG.debug("inventory: "+ inventory);
            InventoryResource inventoryResource = new InventoryBuilder().deserialize(inventory);
            correlationId = message.getJMSCorrelationID();
            if(messageBase.getFutures().keySet().contains(correlationId)) {


                if (inventoryResource.getInventory().getReturnCode() != 0 && "findAll".equals(inventoryResource.getInventory().getMethod())){


                    List users = new ArrayList<User>();
                    users.addAll((List<User>)inventoryResource.getObj());
                    long total = users.size();
                    Page<User> imp = new PageImpl<>(users,new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "name")),total);
                    messageBase.getFutures().get(correlationId).complete(imp);
                } else if (inventoryResource.getInventory().getReturnCode() != 0) {
                    messageBase.getFutures().get(correlationId).complete(inventoryResource.getObj());
                }




                messageBase.getFutures().remove(correlationId);
            }
        } catch (JMSException e) {
            LOG.error(e.getMessage());
        }


        try {
            LOG.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Inventory response = (Inventory) messageConverter.fromMessage(message);
            LOG.debug("Application : order response received : " + response);
            LOG.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
