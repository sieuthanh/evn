package com.form.evn.sourceConnectBuffer;

import com.form.FSeed;

public class FSourceConnectBuffer extends FSeed
{
    private int src_Connect_Id;
    private String src_Code;
    private String fullName;
    private String shortName;
    private String type;
    private String description;
    private String url;
    private String connection;
    private int active;
    private int des_Connect_Id;
    private String nameTable;
    private String nameSQL="";
    
    private int pageIndex;
  public FSourceConnectBuffer()
  {
  }

    public void reset(){
        this.src_Connect_Id=0;
        this.src_Code="";
        this.fullName="";
        this.shortName="";
        this.type="";
        this.description="";
        this.url="";
        this.connection="";
        this.active=0;
    }
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getSrc_Connect_Id() {
        return src_Connect_Id;
    }

    public void setSrc_Connect_Id(int src_Connect_Id) {
        this.src_Connect_Id = src_Connect_Id;
    }

    public String getSrc_Code() {
        return src_Code;
    }

    public void setSrc_Code(String src_Code) {
        this.src_Code = src_Code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getDes_Connect_Id() {
        return des_Connect_Id;
    }

    public void setDes_Connect_Id(int des_Connect_Id) {
        this.des_Connect_Id = des_Connect_Id;
    }

    public String getNameSQL() {
        return nameSQL;
    }

    public void setNameSQL(String nameSQL) {
        this.nameSQL = nameSQL;
    }
}
