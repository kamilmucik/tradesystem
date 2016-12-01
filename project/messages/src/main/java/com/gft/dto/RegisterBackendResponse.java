package com.gft.dto;

import java.io.Serializable;

/**
 * Created by kamu on 2016-09-07.
 */
public class RegisterBackendResponse extends Parent implements Serializable {

    @Override
    public String toString() {
        return "RegisterBackendResponse [productList=" + "productList"
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
