package com.form.evn.reportNormBuffer;

import com.form.FSeed;

public class FReportNormBuffer extends FSeed
{
    private int norm_Id;
    private int report_Id;
    private String norm_Code;
    private String norm_Name;
    private String type;
    private int norm_Total;
     private String description;
    private String version;
    private String start_Date;
    private int active;
    
    private String nameTable;
  public FReportNormBuffer()
  {
  }
    public void reset(){
        this.report_Id=0;
        this.norm_Id=0;
        this.norm_Code="";
        this.norm_Name="";
        this.type="";
        this.setNorm_Total(0);
        this.description="";
        this.setVersion("");
        this.start_Date="";
    }

    public int getNorm_Id() {
        return norm_Id;
    }

    public void setNorm_Id(int norm_Id) {
        this.norm_Id = norm_Id;
    }

    public int getReport_Id() {
        return report_Id;
    }

    public void setReport_Id(int report_Id) {
        this.report_Id = report_Id;
    }

    public String getNorm_Code() {
        return norm_Code;
    }

    public void setNorm_Code(String norm_Code) {
        this.norm_Code = norm_Code;
    }

    public String getNorm_Name() {
        return norm_Name;
    }

    public void setNorm_Name(String norm_Name) {
        this.norm_Name = norm_Name;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getNorm_Total() {
        return norm_Total;
    }

    public void setNorm_Total(int norm_Total) {
        this.norm_Total = norm_Total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
