package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by kamu on 2016-09-30.
 */
public class UserComplex implements Serializable {

    private UserDetails userDetails;

    private List<UserAsset> assetList  = new ArrayList<UserAsset>(0);

    private Map<Date, BigDecimal> chart = new TreeMap<Date, BigDecimal>();

    public UserComplex(){}

    public UserComplex(UserDetails userDetails){
        this.userDetails = userDetails;
    }

    public UserDetails getUserDetails() {return userDetails;}

    public void setUserDetails(UserDetails userDetails) {this.userDetails = userDetails;}

    public List<UserAsset> getAssetList() {return assetList;}

    public void setAssetList(List<UserAsset> assetList) {this.assetList = assetList;}

    public Map<Date, BigDecimal> getChart() {return chart;}

    public void setChart(Map<Date, BigDecimal> chart) {this.chart = chart;}
}
