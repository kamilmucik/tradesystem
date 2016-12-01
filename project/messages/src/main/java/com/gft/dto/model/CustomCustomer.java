package com.gft.dto.model;

import java.io.Serializable;

/**
 * Created by kamu on 8/11/2016.
 */

public class CustomCustomer  implements Serializable {

    private static final long serialVersionUID = 5626740758276999371L;

    private String customerName;

    private String[] products;

    public CustomCustomer(String customerName){
        this.customerName = customerName;
    }

    public CustomCustomer(String customerName, String[] products){
        this.customerName = customerName;
        this.products =  products;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CustomCustomer{" +
                "customerName='" + customerName + '\'' +
                ", products=" + products.toString() +
                '}';
    }
}
