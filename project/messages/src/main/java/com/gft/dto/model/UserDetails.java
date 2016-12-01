package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 2016-09-06.
 */
public class UserDetails implements Serializable {

    private String firstName;

    private String lastName;

    private BigDecimal freeResources;

    private BigDecimal walletValue;

    private BigDecimal shareholderReturn;

    public UserDetails(String firstName, String lastName, BigDecimal freeResources,BigDecimal walletValue, BigDecimal shareholderReturn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.freeResources = freeResources;
        this.walletValue = walletValue;
        this.shareholderReturn = shareholderReturn;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public BigDecimal getFreeResources() {return freeResources;}

    public void setFreeResources(BigDecimal freeResources) {this.freeResources = freeResources;}

    public BigDecimal getWalletValue() {return walletValue;}

    public void setWalletValue(BigDecimal walletValue) {this.walletValue = walletValue;}

    public BigDecimal getShareholderReturn() {return shareholderReturn;}

    public void setShareholderReturn(BigDecimal shareholderReturn) {this.shareholderReturn = shareholderReturn;}
}
