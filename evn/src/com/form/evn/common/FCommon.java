package com.form.evn.common;

import com.form.FSeed;

public class FCommon extends FSeed
{
    private int src_Record_Id;
    private int ticket_Id;
    private int src_Connect_Id;
    private String status;
    private int create_By;
    private String create_Date;
    private int update_By;
    private String update_Date;
    private String nameTable;  
    
    private String error_code;  
    private String error_description;  


    public int getSrc_Connect_Id() {
        return src_Connect_Id;
    }

    public void setSrc_Connect_Id(int src_Connect_Id) {
        this.src_Connect_Id = src_Connect_Id;
    }

    public int getTicket_Id() {
        return ticket_Id;
    }

    public void setTicket_Id(int ticket_Id) {
        this.ticket_Id = ticket_Id;
    }

    public int getSrc_Record_Id() {
        return src_Record_Id;
    }

    public void setSrc_Record_Id(int src_Record_Id) {
        this.src_Record_Id = src_Record_Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }



    public String getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(String create_Date) {
        this.create_Date = create_Date;
    }

 

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public int getCreate_By() {
        return create_By;
    }

    public void setCreate_By(int create_By) {
        this.create_By = create_By;
    }

    public int getUpdate_By() {
        return update_By;
    }

    public void setUpdate_By(int update_By) {
        this.update_By = update_By;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
