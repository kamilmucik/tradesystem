package com.gft.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kamu on 2016-09-05.
 */
@Entity
@Table(name="TBL_PRODUCT_RATE")
public class ProductRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Column(name = "RATE", nullable = false)
    private BigDecimal rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Date getCreated() {return created;}

    public void setCreated(Date created) {this.created = created;}

    public BigDecimal getRate() {return rate;}

    public void setRate(BigDecimal rate) {this.rate = rate;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

}
