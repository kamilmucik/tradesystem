package com.gft.messaging;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exeption represent situation when request is unauthorized
 * Created by kamu on 2016-09-19.
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class MiddletierException extends Exception   {

    private String errCode;

    private String errNotication;

    public MiddletierException() {
        super();
    }

    public MiddletierException(String errNotication) {
        super(errNotication);
        this.errNotication = errNotication;
    }

    public MiddletierException(Throwable cause) {
        super(cause);
    }

    public MiddletierException(String errCode,String errNotication) {
        super(errNotication);
        this.errCode = errCode;
        this.errNotication = errNotication;
    }


    public String getErrCode() {return errCode;}

    public void setErrCode(String errCode) {this.errCode = errCode;}

    public String getErrNotication() {return errNotication;}

    public void setErrNotication(String errNotication) {this.errNotication = errNotication;}

    @Override
    public String toString() {
        return "MiddletierException [errCode=" + errCode
                + ", errNotication=" + errNotication + "]";
    }

    @Override
    public String getMessage() {
        return errNotication;
    }

}
