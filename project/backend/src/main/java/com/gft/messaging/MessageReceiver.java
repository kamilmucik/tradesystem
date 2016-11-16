package com.gft.messaging;

import javax.jms.Message;
import javax.jms.MessageListener;

import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import com.gft.bean.InventoryResource;
import com.gft.configuration.RepositoryConfig;
import com.gft.services.persist.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kamu on 8/18/2016.
 */
@Component
public class MessageReceiver implements MessageListener{

    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    MessageConverter messageConverter;

    @Autowired
    MessageSender messageSender;

    @Autowired
    RepositoryConfig repositoryConfig;

    @Override
    public void onMessage(Message message) {
        LOG.debug("onMessage: "+ message);
        try {
            Inventory inventory = (Inventory) messageConverter.fromMessage(message);
            LOG.debug("-----------------------------------------------------");
            LOG.debug("inventory: "+ inventory);
            InventoryResource inventoryResource = new InventoryBuilder().deserialize(inventory);

            Class clssSrvc = null;
            if ("userService".equals(inventory.getObjService())){
                clssSrvc = UserService.class;
            }

            LOG.debug("criteria[{}]: ", inventoryResource.getCriteria().size());
            for (Object object : inventoryResource.getCriteria()){
                LOG.debug(object + " : " + object.getClass().getTypeName());
            }

            LOG.debug("object[{}]: {}", inventoryResource.getObj(), inventoryResource.getObj().getClass().getName());
            if (inventoryResource.getCriteria().isEmpty()){
                inventoryResource.getCriteria().add(inventoryResource.getObj());
            }

            Object service = repositoryConfig.lookupBean(inventory.getObjService(),  clssSrvc);
            for (Method method : service.getClass().getMethods()) {
                if (inventory.getMethod().equals(method.getName())) {


                    Object response = method.invoke(service, inventoryResource.getCriteria().toArray());
                    inventory = new InventoryBuilder(inventoryResource.getId())
                            .setObj(response)
                            .setJavaType(inventory.getObjClass())
                            .setReturnCode(200)
                            .setMethod(inventory.getMethod())
                            .build();
                    LOG.debug("UserServiceImpl:inventory: {}", inventory);
                    messageSender.sendMessage(inventory);



                }
            }

        } catch (Exception e) {
            LOG.error(e.getStackTrace().toString());
        }
    }
}