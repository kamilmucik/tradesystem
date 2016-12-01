package com.gft.dto.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kamu on 8/18/2016.
 */
public class Product implements Serializable {

    private Long id;

    private String name;

    private BigDecimal rate;

    private BigDecimal price;

    public Product(Long id, String name, BigDecimal price, BigDecimal rate ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public Product(Long id, BigDecimal price, BigDecimal rate){
        this.id = id;
        this.price = price;
        this.rate = rate;
    }

    public Product(BigDecimal rate, BigDecimal price, String name, Long id ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public Product(BigDecimal price ){
        this.price = price;
    }

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public BigDecimal getRate() {return rate;}

    public void setRate(BigDecimal rate) {this.rate = rate;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [price=" + price + "]";
    }
}
