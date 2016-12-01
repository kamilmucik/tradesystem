package com.gft.model;

import com.gft.dto.model.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kamu on 2016-09-05.
 */
@Entity
@Table(name="TBL_USER_ASSET")
public class UserAsset {

    @Id
    @SequenceGenerator( name = "appUserAssetSeq", sequenceName = "TBL_USER_ASSET_SEQ", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "appUserAssetSeq" )
    @Column( name = "ID" )
    private Long id;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "VOLUME", nullable = false)
    private Long volume;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTRACTOR_1_USER_ID", nullable = false)
    private User contractor1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTOR_2_USER_ID")
    private User contractor2;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "IS_DONE", nullable = false)
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    private Date createdDate ;

    public UserAsset(){
        isDone = false;
        createdDate = new Date();
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public Long getVolume() {return volume;}

    public void setVolume(Long volume) {this.volume = volume;}

    public Boolean getDone() {return isDone;}

    public void setDone(Boolean done) {isDone = done;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public User getContractor1() {return contractor1;}

    public void setContractor1(User contractor1) {this.contractor1 = contractor1;}

    public User getContractor2() {return contractor2;}

    public void setContractor2(User contractor2) {this.contractor2 = contractor2;}

    public TransactionType getTransactionType() {return transactionType;}

    public void setTransactionType(TransactionType transactionType) {this.transactionType = transactionType;}

    public Date getCreatedDate() {return createdDate;}

    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}

    @Override
    public String toString() {
        return "UserAsset [id=" + id
                + ", price=" + price
                + ", volume=" + volume
                + ", createdDate=" + createdDate
                + ", contractor1=" + contractor1
                + ", contractor2=" + contractor2
                + ", transactionType=" + transactionType
                + ", isDone=" + isDone + "]";
    }
}
