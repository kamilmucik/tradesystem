package com.gft.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @see "http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query"
 * Created by kamu on 8/23/2016.
 */
@Entity
@Table(name="TBL_PRODUCT")
@SqlResultSetMapping(name="scalar", columns=@ColumnResult(name="price"))

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Product.findStockByStockCodeNativeSQL",
                query = "select p.id as id from TBL_PRODUCT p ",
                resultClass = com.gft.model.Product.class

        )
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Product.PROCEDURE3",
                procedureName = "PROCEDURE3",
                parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAM1", type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "PARAM2", type = BigDecimal.class)
                })
})

public class Product implements Serializable {

    static final Logger LOG = LoggerFactory.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ENV_NAME", nullable = false)
    private String envName;

    @Transient
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    @OrderBy("id DESC")
    //TODO: można zrobić to na filtrach ale nie wiem na ten czas jak
    private List<ProductRate> rates = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @OrderBy("id DESC")
    private List<UserAsset> userAssets = new ArrayList<>(0);

    public Product(){}

    public Product(Long id, String name, String envName){
        this.id = id;
        this.name = name;
        this.envName = envName;
    }

    @PostLoad
    public void calculatePrice() {
        if (rates.size() > 0) {
            this.price = rates.get(0).getRate();
        }
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public List<ProductRate> getRates() {

        return rates;
    }

    public void setRates(List<ProductRate> rates) {this.rates = rates;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public List<UserAsset> getUserAssets() {return userAssets;}

    public void setUserAssets(List<UserAsset> userAssets) {this.userAssets = userAssets;}

    public int hashCode() {
        final int prime = 13;
        int result = 1;
        result = prime * result + id.intValue();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", name=" + name
                + ", price=" + price
                + "]";
    }
}

