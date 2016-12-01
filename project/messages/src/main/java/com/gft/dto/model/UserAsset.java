package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 2016-09-21.
 */
public class UserAsset implements Serializable {

    private Long id;

    private String name;

    private Long volume;

    private BigDecimal buyPrice;

    private BigDecimal buyValue;

    private BigDecimal profit;

    public UserAsset (Long id, String name, Long volume,BigDecimal buyPrice ,BigDecimal buyValue){
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.buyPrice = buyPrice;
        this.buyValue = buyValue;
        this.profit = new BigDecimal(0);
    }

    public UserAsset (Long id, String name, Long volume,BigDecimal buyPrice ,BigDecimal buyValue, BigDecimal profit){
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.buyPrice = buyPrice;
        this.buyValue = buyValue;
        this.profit = profit;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Long getVolume() {return volume;}

    public void setVolume(Long volume) {this.volume = volume;}

    public BigDecimal getBuyPrice() {return buyPrice;}

    public void setBuyPrice(BigDecimal buyPrice) {this.buyPrice = buyPrice;}

    public BigDecimal getBuyValue() {return buyValue;}

    public void setBuyValue(BigDecimal buyValue) {this.buyValue = buyValue;}

    public BigDecimal getProfit() { return profit;}

    public void setProfit(BigDecimal profit) {this.profit = profit;}
}
