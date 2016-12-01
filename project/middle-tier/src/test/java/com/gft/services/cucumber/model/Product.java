package com.gft.services.cucumber.model;

import java.math.BigDecimal;

/**
 * Created by kamu on 2016-09-20.
 */
public class Product {

    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal rate;

    public Product(Long id, String name, BigDecimal price, BigDecimal rate ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
