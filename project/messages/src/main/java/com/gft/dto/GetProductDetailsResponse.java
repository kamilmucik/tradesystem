package com.gft.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 2016-08-31.
 */
public class GetProductDetailsResponse extends Parent implements Serializable {

    private Long id;

    private BigDecimal price;

    public GetProductDetailsResponse(Long id, BigDecimal price ){
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GetProductDetailsResponse [id=" + id
                + ", price=" + price
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
