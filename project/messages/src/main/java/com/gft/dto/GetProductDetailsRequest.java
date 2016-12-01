package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-08-31.
 */
public class GetProductDetailsRequest extends Parent implements Serializable {

    private Long id;

    public GetProductDetailsRequest(Long id){
        this.id = id;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "GetProductDetailsRequest [id=" + id
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
