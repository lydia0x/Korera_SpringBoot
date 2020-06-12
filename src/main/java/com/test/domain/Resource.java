package com.test.domain;

import javax.persistence.*;

@Entity
@Table(name = "RESOURCE")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String resourcecode;
    @Column
    private String resourcename;
    @Column
    private String columnname;
    @Column
    private String rowdata;

    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(String resourcecode) {
        this.resourcecode = resourcecode;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String getRowdata() {
        return rowdata;
    }

    public void setRowdata(String rowdata) {
        this.rowdata = rowdata;
    }
}
