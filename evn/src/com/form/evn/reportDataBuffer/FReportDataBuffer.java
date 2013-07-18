package com.form.evn.reportDataBuffer;

import com.form.FSeed;

public class FReportDataBuffer extends FSeed
{
    private int src_Data_Id;
    private int ticket_Id;
    private int src_connect_Id;
    private String status;
    private String create_By;
    private String creation_Date;
    private int updated_By;
    private String update_Date;
    private String norm_Code;
    private String position;
    private String value_Number;
    private String value_Text;
    private String description;

    
  public FReportDataBuffer()
  {
  }


    public int getSrc_Data_Id() {
        return src_Data_Id;
    }

    public void setSrc_Data_Id(int src_Data_Id) {
        this.src_Data_Id = src_Data_Id;
    }

    public int getTicket_Id() {
        return ticket_Id;
    }

    public void setTicket_Id(int ticket_Id) {
        this.ticket_Id = ticket_Id;
    }

    public int getSrc_connect_Id() {
        return src_connect_Id;
    }

    public void setSrc_connect_Id(int src_connect_Id) {
        this.src_connect_Id = src_connect_Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_By() {
        return create_By;
    }

    public void setCreate_By(String create_By) {
        this.create_By = create_By;
    }

    public String getCreation_Date() {
        return creation_Date;
    }

    public void setCreation_Date(String creation_Date) {
        this.creation_Date = creation_Date;
    }

    public int getUpdated_By() {
        return updated_By;
    }

    public void setUpdated_By(int updated_By) {
        this.updated_By = updated_By;
    }

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public String getNorm_Code() {
        return norm_Code;
    }

    public void setNorm_Code(String norm_Code) {
        this.norm_Code = norm_Code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getValue_Number() {
        return value_Number;
    }

    public void setValue_Number(String value_Number) {
        this.value_Number = value_Number;
    }

    public String getValue_Text() {
        return value_Text;
    }

    public void setValue_Text(String value_Text) {
        this.value_Text = value_Text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
