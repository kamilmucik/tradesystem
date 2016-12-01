package com.gft.dto;

import com.gft.dto.model.ActiveOrder;
import com.gft.dto.model.Product;
import com.gft.dto.model.UserComplex;
import com.gft.dto.model.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamu on 2016-09-28.
 */
public class GetSocketPackageResponse extends Parent implements Serializable {

    /**
     * Notowania, wspólne dla każdego
     */
    private List<Product> productList  = new ArrayList<Product>(0);

    /**
     * Zlecenia aktywne, wspólne dla każdego
     */
    private List<ActiveOrder> activeOrderList  = new ArrayList<ActiveOrder>(0);

    /**
     * Map <socketSession,UserDetails> custom information about connected user
     */
    private Map<String,UserComplex> users  = new HashMap<String,UserComplex>();

    public List<Product> getProductList() {return productList;}

    public void setProductList(List<Product> productList) {this.productList = productList;}

    public Map<String, UserComplex> getUsers() {return users;}

    public void setUsers(Map<String, UserComplex> users) {this.users = users;}

    public List<ActiveOrder> getActiveOrderList() {return activeOrderList;}

    public void setActiveOrderList(List<ActiveOrder> activeOrderList) {this.activeOrderList = activeOrderList;}

    @Override
    public String toString() {
        return "GetSocketPackageResponse [notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
