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
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
