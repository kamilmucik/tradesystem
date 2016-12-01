package com.gft.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Created by kamu on 8/16/2016.
 */
@Entity
@Table(name="TBL_USER")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "ID" )
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "LAST_LOGIN", nullable = false)
    private Date lastLogin;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL  )
    @JoinTable(
            name = "TBL_USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")}
    )
    private List<Authority> authorities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor1")
    private List<UserAsset> contractor1Assets = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor2")
    private List<UserAsset> contractor2Assets = new ArrayList<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getLastLogin() {return lastLogin;}

    public void setLastLogin(Date lastLogin) {this.lastLogin = lastLogin;}

    public List<Authority> getAuthorities() {return authorities;}

    public void setAuthorities(List<Authority> authorities) {this.authorities = authorities;}

    public List<UserAsset> getContractor1Assets() {return contractor1Assets;}

    public void setContractor1Assets(List<UserAsset> contractor1Assets) {this.contractor1Assets = contractor1Assets;}

    public List<UserAsset> getContractor2Assets() {return contractor2Assets;}

    public void setContractor2Assets(List<UserAsset> contractor2Assets) {this.contractor2Assets = contractor2Assets;}

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.intValue();
        result = prime * result + ((id.equals(null)) ? 0 : id.hashCode());
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
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (id == null) {
            if (!other.id.equals(null))
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firsName=" + firstName + "]";
    }
}
