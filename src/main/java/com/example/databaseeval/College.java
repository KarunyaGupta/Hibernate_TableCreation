package com.example.databaseeval;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "college")
public class College {

    @Id
    @Column(name = "cid")
    private int cid;

    @Column(name = "cName", nullable = false)
    private String cName;

    @Column(name = "clocation", nullable = false)
    private String cLocation;

    public College() {}

    public College(int cid, String cName, String cLocation) {
        this.cid = cid;
        this.cName = cName;
        this.cLocation = cLocation;
    }

    public int getCid() {
        return cid;
    }

    public String getCName() {
        return cName;
    }

    public String getCLocation() {
        return cLocation;
    }
}


