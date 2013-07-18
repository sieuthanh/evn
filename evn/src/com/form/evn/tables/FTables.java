package com.form.evn.tables;

import com.form.FSeed;

public class FTables extends FSeed
{
    private int id;
    private String name;
    private String description;
    private String key;
    private String nameTable;
    private int src_connect_id;
    private String data_fields;
    private int des_connect_id;
    

    public void reset(){
        this.setId(0);
        this.setName("");
        this.setDescription("");
        this.setData_fields("");
        this.setSrc_connect_id(0);
        this.setDes_connect_id(0);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getSrc_connect_id() {
        return src_connect_id;
    }

    public void setSrc_connect_id(int src_connect_id) {
        this.src_connect_id = src_connect_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData_fields() {
        return data_fields;
    }

    public void setData_fields(String data_fields) {
        this.data_fields = data_fields;
    }

    public int getDes_connect_id() {
        return des_connect_id;
    }

    public void setDes_connect_id(int des_connect_id) {
        this.des_connect_id = des_connect_id;
    }
}
