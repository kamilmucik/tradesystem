package com.gft.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by kamu on 2016-08-25.
 */
public class InventoryResource {

    private String id;

    private Class<?> clz;

    private Object obj;

    private Inventory inventory;

    private List criteria = new ArrayList<Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Inventory getInventory() {return inventory;}

    public void setInventory(Inventory inventory) {this.inventory = inventory;}

    public List getCriteria() {return criteria;}

    public void setCriteria(List criteria) {this.criteria = criteria;}

    @Override
    public String toString() {
        return "InventoryResource [id=" + id + ", clz=" + clz + ", obj=" + obj + ", criteria=" + criteria + "]";
    }
}
