package com.gft.messaging;

import com.gft.dto.RegisterBackendRequest;
import com.gft.services.persist.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import javax.jms.Destination;

/**
 * Created by kamu on 2016-09-07.
 */
@Controller
public class BackendSendService {

    static final Logger LOG = LoggerFactory.getLogger(BackendSendService.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ProductService productService;

    @Autowired
    Destination outputQueueDestination;

    public void backendRegister(Long instance, String products){
        productService.getSupportedProduct(products);
        RegisterBackendRequest request = new RegisterBackendRequest(instance,productService.getSupportedProduct(products));
        send(request);
    }

    public void send(Object object){
        jmsTemplate.convertAndSend(outputQueueDestination, object);
    }
}
