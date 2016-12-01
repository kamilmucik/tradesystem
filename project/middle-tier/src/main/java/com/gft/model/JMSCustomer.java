package com.gft.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class represent object of backend instance.
 * Created by kamu on 8/12/2016.
 */
public class JMSCustomer {

    private String queueName;

    private Long instance;

    private Map<Long, String> products = new HashMap<>();

    public JMSCustomer(String queueName,Map<Long, String> products ){
        this.queueName = queueName;
        this.products = products;
        this.instance = 0l;
    }

    public JMSCustomer(Long instance,Map<Long, String> products ){
        this.queueName = "";
        this.instance = instance;
        this.products = products;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Map<Long, String> getProducts() {return products;}

    public void setProducts(Map<Long, String> products) {this.products = products;}

    public Long getInstance() {return instance;}

    public void setInstance(Long instance) {this.instance = instance;}
}
