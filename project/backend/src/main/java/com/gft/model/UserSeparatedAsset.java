package com.gft.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Model UserSeparatedAsset asset represent situation when user want to buy more then sellers want to sell
 * rest of volume wait for another transaction.
 * Created by kamu on 2016-09-12.
 */

@Entity
@Table(name="TBL_USER_SEPARATED_ASSET")
@PrimaryKeyJoinColumn(name="ID")
public class UserSeparatedAsset extends UserAsset {

    @Column(name = "SEPARATED_DATE")
    private Date separatedDate;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="USER_ASSET_ID")
    private UserAsset userAsset;

    public UserSeparatedAsset(){}

    public UserSeparatedAsset(UserAsset userAsset){
        this.setPrice(userAsset.getPrice());
        this.setVolume(userAsset.getVolume());
        this.setContractor1(userAsset.getContractor1());
        this.setProduct(userAsset.getProduct());
        this.setTransactionType(userAsset.getTransactionType());
        this.setDone(false);
        this.setCreatedDate(userAsset.getCreatedDate());
        this.setSeparatedDate(new Date());
        this.setUserAsset(userAsset);
    }

    public Date getSeparatedDate() {return separatedDate;}

    public void setSeparatedDate(Date separatedDate) {this.separatedDate = separatedDate;}

    public UserAsset getUserAsset() {return userAsset;}

    public void setUserAsset(UserAsset userAsset) {this.userAsset = userAsset;}

}
