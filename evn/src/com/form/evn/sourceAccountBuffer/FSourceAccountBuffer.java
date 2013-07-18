package com.form.evn.sourceAccountBuffer;

import com.form.FSeed;

public class FSourceAccountBuffer extends FSeed
{
    private int account_Id;
    private String account_Pass;
    private String fullName;
    private String shortName;
    private int src_Connect_Id;
    private String description;
    private int quota;
    private int active;

    
    private String nameTable;
    
    public FSourceAccountBuffer()
    {
    }
    public void reset(){
        this.account_Id=0;
        this.account_Pass="";
        this.fullName="";
        this.shortName="";
        this.setQuota(0);
        this.description="";
        this.setSrc_Connect_Id(0);
        this.active=0;
    }  



    public int getAccount_Id() {
        return account_Id;
    }

    public void setAccount_Id(int account_Id) {
        this.account_Id = account_Id;
    }

    public String getAccount_Pass() {
        return account_Pass;
    }

    public void setAccount_Pass(String account_Pass) {
        this.account_Pass = account_Pass;
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

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getSrc_Connect_Id() {
        return src_Connect_Id;
    }

    public void setSrc_Connect_Id(int src_Connect_Id) {
        this.src_Connect_Id = src_Connect_Id;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
