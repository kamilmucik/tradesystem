package com.gft.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kamu on 8/23/2016.
 */
@Entity
@Table(name="TBL_AUTHORITY")
public class Authority implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ROLE", unique = true, nullable = false)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}
