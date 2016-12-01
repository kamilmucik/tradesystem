package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kamu on 2016-09-20.
 */
public class ActiveOrder implements Serializable {

    private Long id;

    private String name;

    private Long volumen;

    private BigDecimal price;

    private BigDecimal valuation;

    private TransactionType type;

    private Date date;

    public ActiveOrder (Long id, String name, Long volumen,BigDecimal price ,BigDecimal valuation, TransactionType type){
        this.id = id;
        this.name = name;
        this.volumen = volumen;
        this.price = price;
        this.valuation = valuation;
        this.type = type;
    }

    public ActiveOrder (Long id, String name, Long volumen,BigDecimal price ,BigDecimal valuation, TransactionType type, Date date){
        this.id = id;
        this.name = name;
        this.volumen = volumen;
        this.price = price;
        this.valuation = valuation;
        this.type = type;
        this.date = date;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Long getVolumen() {return volumen;}

    public void setVolumen(Long volumen) {this.volumen = volumen;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public BigDecimal getValuation() {return valuation;}

    public void setValuation(BigDecimal valuation) {this.valuation = valuation;}

    public TransactionType getType() {return type;}

    public void setType(TransactionType type) {this.type = type;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}
}
