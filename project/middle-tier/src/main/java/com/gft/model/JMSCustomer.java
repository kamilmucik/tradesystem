package com.gft.model;

import java.util.List;

/**
 * Created by kamu on 8/12/2016.
 */
public class JMSCustomer {

    private String queueName;

    private List<String> productList;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }
}
