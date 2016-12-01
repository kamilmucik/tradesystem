package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kamu on 2016-09-06.
 */
public class Asset implements Serializable, Comparable<Asset> {

    private Long id;

    private Long productId;

    private Long userId;

    private BigDecimal price;

    private Integer volume;

    private Date created;

    public Asset(Long productId, Long userId, BigDecimal price, Integer volume){
        this.productId = productId;
        this.userId = userId;
        this.price = price;
        this.volume = volume;
        this.created = new Date();
    }

    public Long getProductId() {return productId;}

    public void setProductId(Long productId) {this.productId = productId;}

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public Integer getVolume() {return volume;}

    public void setVolume(Integer volume) {this.volume = volume;}

    public Date getCreated() {return created;}

    public void setCreated(Date created) {this.created = created;}

    @Override
    public int compareTo(Asset o) {
        return this.getCreated().compareTo(o.getCreated());
    }
}
