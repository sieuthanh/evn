package com.form.admin.reportSystem.sum;
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
    private int enableChart;
    private String chartData="[]";
    private String chartCategory="[]";
    private String chartDataMt="[]";
    private String chartDataCDR="[]";
    private String chartSeriesContent="[]";
    private String[][] values=null;
    private int xlength;
    private int typeChart;
    private String curentChart;
    private int pointInterval;
    private int maxZoom;
    private int timeRefresh;
    private int dateStart;
    private String telco;
    private String service_number;
    private String service_code;
    private String phone_Number;
    private int enablePaging;
    
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

    public String getChartData() {
        return chartData;
    }

    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    public String getChartCategory() {
        return chartCategory;
    }

    public void setChartCategory(String chartCategory) {
        this.chartCategory = chartCategory;
    }

    public String getChartDataMt() {
        return chartDataMt;
    }

    public void setChartDataMt(String chartDataMt) {
        this.chartDataMt = chartDataMt;
    }

    public String getChartDataCDR() {
        return chartDataCDR;
    }

    public void setChartDataCDR(String chartDataCDR) {
        this.chartDataCDR = chartDataCDR;
    }

    public int getEnableChart() {
        return enableChart;
    }

    public void setEnableChart(int enableChart) {
        this.enableChart = enableChart;
    }

    public String getChartSeriesContent() {
        return chartSeriesContent;
    }

    public void setChartSeriesContent(String chartSeriesContent) {
        this.chartSeriesContent = chartSeriesContent;
    }

    public String[][] getValues() {
        return values;
    }

    public void setValues(String[][] values) {
        this.values = values;
    }

    public int getXlength() {
        return xlength;
    }

    public void setXlength(int xlength) {
        this.xlength = xlength;
    }

    public int getTypeChart() {
        return typeChart;
    }

    public void setTypeChart(int typeChart) {
        this.typeChart = typeChart;
    }

    public String getCurentChart() {
        return curentChart;
    }

    public void setCurentChart(String curentChart) {
        this.curentChart = curentChart;
    }


    public int getPointInterval() {
        return pointInterval;
    }

    public void setPointInterval(int pointInterval) {
        this.pointInterval = pointInterval;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    public int getTimeRefresh() {
        return timeRefresh;
    }

    public void setTimeRefresh(int timeRefresh) {
        this.timeRefresh = timeRefresh;
    }

    public int getDateStart() {
        return dateStart;
    }

    public void setDateStart(int dateStart) {
        this.dateStart = dateStart;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getService_number() {
        return service_number;
    }

    public void setService_number(String service_number) {
        this.service_number = service_number;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public int getEnablePaging() {
        return enablePaging;
    }

    public void setEnablePaging(int enablePaging) {
        this.enablePaging = enablePaging;
    }
}
