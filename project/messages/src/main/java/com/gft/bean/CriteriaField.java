package com.gft.bean;

import com.gft.dto.Product;

/**
 * Created by kamu on 2016-08-25.
 */
public class CriteriaField implements Comparable<CriteriaField> {

    private String name;

    private String type;

    private int order;

    private Object value;

    public CriteriaField(){}

    public CriteriaField(String name,String type, int order, Object value ){
        this.name = name;
        this.type = type;
        this.order = order;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int compareTo(CriteriaField o) {
        return this.getOrder() - o.getOrder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CriteriaField other = (CriteriaField) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CriteriaField [name=" + name + ", type=" + type + ", order=" + order + ", value=" + value + "]";
    }
}
