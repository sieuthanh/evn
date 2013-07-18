package com.form.evn;

import com.form.FBeans;
import com.form.FSeed;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class FXml extends FSeed
{
    private String sql;
    private ArrayList params;
    private FBeans con;
    private int level;


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ArrayList getParams() {
        return params;
    }

    public void setParams(ArrayList params) {
        this.params = params;
    }

    public FBeans getCon() {
        return con;
    }

    public void setCon(FBeans con) {
        this.con = con;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
