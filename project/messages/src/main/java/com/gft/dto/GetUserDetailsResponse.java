package com.gft.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 2016-09-05.
 */
public class GetUserDetailsResponse extends Parent implements Serializable {

    private String firstName;

    private String lastName;

    private BigDecimal freeResources;

    private BigDecimal walletValue;

    private BigDecimal shareholderReturn;

    public GetUserDetailsResponse(String firstName, String lastName, BigDecimal freeResources, BigDecimal walletValue, BigDecimal shareholderReturn, Integer notificationCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.freeResources = freeResources;
        this.walletValue = walletValue;
        this.shareholderReturn = shareholderReturn;
        this.notificationCode = notificationCode;
    }

    public GetUserDetailsResponse(String firstName, String lastName, Integer notificationCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.freeResources = new BigDecimal(0);
        this.walletValue =  new BigDecimal(0);
        this.shareholderReturn =  new BigDecimal(0);
        this.notificationCode = notificationCode;
    }

    public String getUserName() {return firstName;}

    public void setUserName(String firstName) {this.firstName = firstName;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public BigDecimal getShareholderReturn() {return shareholderReturn;}

    public void setShareholderReturn(BigDecimal shareholderReturn) {this.shareholderReturn = shareholderReturn;}

    public BigDecimal getFreeResources() {return freeResources;}

    public void setFreeResources(BigDecimal freeResources) {this.freeResources = freeResources;}

    public BigDecimal getWalletValue() {return walletValue;}

    public void setWalletValue(BigDecimal walletValue) {this.walletValue = walletValue;}

    public BigDecimal getReturnValue() {return shareholderReturn;}

    public void setReturnValue(BigDecimal shareholderReturn) {this.shareholderReturn = shareholderReturn;}

    @Override
    public String toString() {
        return "GetUserDetailsResponse [firstName=" + firstName
                + ", freeResources=" + freeResources
                + ", walletValue=" + walletValue
                + ", shareholderReturn=" + shareholderReturn
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
