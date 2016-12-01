package com.gft.dto;

import com.gft.dto.model.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 2016-09-14.
 */
public class CreateOrderRequest extends Parent implements Serializable {

    private Long userId;

    private Long productId;

    private TransactionType type;

    private Long volume;

    private BigDecimal price;

    public CreateOrderRequest (Long userId, Long productId, TransactionType type, Long volume, BigDecimal price){
        this.userId = userId;
        this.productId = productId;
        this.type = type;
        this.volume = volume;
        this.price = price;
    }

    public CreateOrderRequest (Long userId, Long productId, TransactionType type, Long volume, BigDecimal price, Integer notificationCode){
        this.userId = userId;
        this.productId = productId;
        this.type = type;
        this.volume = volume;
        this.price = price;
        this.notificationCode = notificationCode;
    }

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public Long getProductId() {return productId;}

    public void setProductId(Long productId) {this.productId = productId;}

    public TransactionType getType() {return type;}

    public void setType(TransactionType type) {this.type = type;}

    public Long getVolume() {return volume;}

    public void setVolume(Long volume) {this.volume = volume;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    @Override
    public String toString() {
        return "CreateOrderRequest [userId=" + userId
                + ", productId=" + productId
                + ", type=" + type
                + ", volume=" + volume
                + ", price=" + price
                + ", correlationId=" + correlationId + "]";
    }
}
