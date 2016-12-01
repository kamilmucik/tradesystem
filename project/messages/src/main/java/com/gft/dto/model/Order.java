package com.gft.dto.model;

import java.io.Serializable;

/**
 * Created by kamu on 2016-09-06.
 */
public class Order implements Serializable {

    private Long userId;

    private Long productId;

    private Integer volume;

    public Order(Long userId, Long productId, Integer volume){
        this.userId = userId;
        this.productId = productId;
        this.volume = volume;
    }

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public Long getProductId() {return productId;}

    public void setProductId(Long productId) {this.productId = productId;}

    public Integer getVolume() {return volume;}

    public void setVolume(Integer volume) {this.volume = volume;}
}
