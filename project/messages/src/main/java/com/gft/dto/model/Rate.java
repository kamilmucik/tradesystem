package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kamu on 2016-09-06.
 */
public class Rate implements Serializable {

    private Integer id = 0;
    private static AtomicInteger incId = new AtomicInteger(0);

    private BigDecimal rate;

    private Date addDate;

    public Rate (){
        this.id = incId.incrementAndGet();
        this.rate = new BigDecimal("1");
        this.addDate = new Date();
    }

    public Rate (Integer id, BigDecimal rate, Date addDate ){
        this.id = id;
        this.rate = rate;
        this.addDate = addDate;
    }

    public Rate (BigDecimal rate, Date addDate ){
        this.id = incId.incrementAndGet();
        this.rate = rate;
        this.addDate = addDate;
    }

    public BigDecimal getRate() {return rate;}

    public void setRate(BigDecimal rate) {this.rate = rate;}

    public Date getAddDate() {return addDate;}

    public void setAddDate(Date addDate) {this.addDate = addDate;}

    public Integer getId() {return id;}

    @Override
    public String toString() {
        return "Rate{id='" + id + '\'' +
                ", rate=" + rate +
                ", addDate=" + addDate +
                '}';
    }
}
