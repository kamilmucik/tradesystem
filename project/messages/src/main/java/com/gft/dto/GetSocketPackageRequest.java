package com.gft.dto;

import com.gft.dto.model.Product;
import com.gft.dto.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamu on 2016-09-28.
 */
public class GetSocketPackageRequest extends Parent implements Serializable {

    private List<String> userEmailList  = new ArrayList<String>(0);

    private Map<String,String> userMap = new HashMap<String, String>();

    public Map<String, String> getUserMap() {return userMap;}

    public void setUserMap(Map<String, String> userMap) {this.userMap = userMap;}

    public List<String> getUserEmailList() {return userEmailList;}

    public void setUserEmailList(List<String> userEmailList) {this.userEmailList = userEmailList;}

    @Override
    public String toString() {
        return "GetSocketPackageRequest [users=" + userEmailList.size()
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
