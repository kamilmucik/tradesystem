package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-09-14.
 */
public class CreateOrderResponse extends Parent implements Serializable {

    private Long id;

    public CreateOrderResponse(){
        this.id = 0l;
    }

    public CreateOrderResponse(Long id){
        this.id = id;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "CreateOrderResponse [id=" + id
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }

}
