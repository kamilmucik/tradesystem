package com.gft.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamu on 2016-09-07.
 */
public class RegisterBackendRequest extends Parent implements Serializable {

    private Long instance;

    private Map<Long,String> products = new HashMap<>();

    public RegisterBackendRequest(Long instance,Map<Long,String> products ){
        this.instance = instance;
        this.products = products;
    }

    public Long getInstance() {return instance;}

    public void setInstance(Long instance) {this.instance = instance;}

    public Map<Long, String> getProducts() {return products;}

    public void setProducts(Map<Long, String> products) {this.products = products;}

    @Override
    public String toString() {
        return "RegisterBackendRequest [instance=" + instance
                + ", products=" + products
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
