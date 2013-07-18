package com.form.evn.reportBuffer;

import com.form.FSeed;

public class FReportBuffer extends FSeed
{
    private int report_Id;
    private String report_Code;
    private String full_Name;
    private String short_Name;
    private String type;
    private String description;
    private String version;
    private String start_Date;
    
    private String nameTable;
    private int active;
    
  public FReportBuffer()
  {
  }
    public void reset(){
        this.report_Id=0;
        this.report_Code="";
        this.full_Name="";
        this.short_Name="";
        this.type="";
        this.description="";
        this.version="";
        this.start_Date="";
    }
    public int getReport_Id() {
        return report_Id;
    }

    public void setReport_Id(int report_Id) {
        this.report_Id = report_Id;
    }

    public String getReport_Code() {
        return report_Code;
    }

    public void setReport_Code(String report_Code) {
        this.report_Code = report_Code;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getShort_Name() {
        return short_Name;
    }

    public void setShort_Name(String short_Name) {
        this.short_Name = short_Name;
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


    public String getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(String start_Date) {
        this.start_Date = start_Date;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
}
