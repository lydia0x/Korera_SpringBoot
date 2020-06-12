package com.test.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String projectId;
    @Column
    private String resourcecode;
    @Column
    private String resourcename;
    @Column
    private String columnname;
    @Column
    private String rowdata;
    @Column
    private String fieldType;

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getprojectId() {
        return projectId;
    }

    public void setprojectId(String projectId) {
        this.projectId = projectId;
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

    public String getfieldType() { return fieldType; }

    public void setfieldType(String fieldType) { this.rowdata = fieldType; }

}
