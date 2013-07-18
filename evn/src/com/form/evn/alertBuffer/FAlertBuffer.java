package com.form.evn.alertBuffer;

import com.form.FSeed;

public class FAlertBuffer extends FSeed
{
    private int alert_Id;
    private int src_Connect_Id;
    private String src_name;
    private int ticket_Id;
    private int src_Record_Id;
    private String status;
    private String error_Code;
    private String error_Description;
    private int request_Id;
    private String detect_Time;
    private String table_Name;
    private int concurent_Id;
    private String description;

    private String nameTable;  
    private int delay;
    private int thread3;
    
    private String columnName;
    private String sqlWhere;
    
    private String search_time; 
    private String search_type; 
    private int search_type_time; 
  public FAlertBuffer()
  {
  }

    public int getAlert_Id() {
        return alert_Id;
    }

    public void setAlert_Id(int alert_Id) {
        this.alert_Id = alert_Id;
    }

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

    public String getError_Code() {
        return error_Code;
    }

    public void setError_Code(String error_Code) {
        this.error_Code = error_Code;
    }

    public String getError_Description() {
        return error_Description;
    }

    public void setError_Description(String error_Description) {
        this.error_Description = error_Description;
    }

    public int getRequest_Id() {
        return request_Id;
    }

    public void setRequest_Id(int request_Id) {
        this.request_Id = request_Id;
    }


    public String getTable_Name() {
        return table_Name;
    }

    public void setTable_Name(String table_Name) {
        this.table_Name = table_Name;
    }

    public int getConcurent_Id() {
        return concurent_Id;
    }

    public void setConcurent_Id(int concurent_Id) {
        this.concurent_Id = concurent_Id;
    }


    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getThread3() {
        return thread3;
    }

    public void setThread3(int thread3) {
        this.thread3 = thread3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc_name() {
        return src_name;
    }

    public void setSrc_name(String src_name) {
        this.src_name = src_name;
    }

    public String getDetect_Time() {
        return detect_Time;
    }

    public void setDetect_Time(String detect_Time) {
        this.detect_Time = detect_Time;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }

    public String getSearch_time() {
        return search_time;
    }

    public void setSearch_time(String search_time) {
        this.search_time = search_time;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public int getSearch_type_time() {
        return search_type_time;
    }

    public void setSearch_type_time(int search_type_time) {
        this.search_type_time = search_type_time;
    }
}
