package com.form.admin.reportSystem;
import com.form.FSeed;

public class FReportSystem extends FSeed
{
    private int id;
    private String fromDate;
    private String toDate;
    private int rowNumber;
    private int columNumber;
    private int total;
    private String selectSql;
    private String nameFile;
    private String nameOfFileVn;
    private int stylePrint;
    private int checked;
    private int enableTitle;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumNumber() {
        return columNumber;
    }

    public void setColumNumber(int columNumber) {
        this.columNumber = columNumber;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNameOfFileVn() {
        return nameOfFileVn;
    }

    public void setNameOfFileVn(String nameOfFileVn) {
        this.nameOfFileVn = nameOfFileVn;
    }

    public int getStylePrint() {
        return stylePrint;
    }

    public void setStylePrint(int stylePrint) {
        this.stylePrint = stylePrint;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getEnableTitle() {
        return enableTitle;
    }

    public void setEnableTitle(int enableTitle) {
        this.enableTitle = enableTitle;
    }
}
