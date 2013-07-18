package com.ws;

import com.form.FSeed;

/**
 *
 * @author hungnn
 */
public class FRequestTicket extends FSeed{

	private String userName; 
	private String passWord; 
	private String reportCode; 
	private String totalRecord;
	private String tableName;
	private String fromDate; 
	private String toDate; 
	private String result;
    private int evn_id;
    private int scr_connect_id;
    private int ticket_id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getEvn_id() {
        return evn_id;
    }

    public void setEvn_id(int evn_id) {
        this.evn_id = evn_id;
    }

    public int getScr_connect_id() {
        return scr_connect_id;
    }

    public void setScr_connect_id(int scr_connect_id) {
        this.scr_connect_id = scr_connect_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }
}
