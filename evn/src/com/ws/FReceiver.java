/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;

/**
 *
 * @author hungnn
 */
public class FReceiver {

    private String cpCode;
    private String requestID;
    private String userID;
    private String receiverID;
    private String serviceID;
    private String commandCode;
    private String info;
    private String receiverTime;    
    

    public FReceiver(String cpCode, String requestID, String userID,
            String receiverID, String serviceID, String commandCode,
            String info, String receiverTime) {
        this.cpCode = cpCode;
        this.requestID = requestID;
        this.userID = userID;
        this.receiverID = receiverID;
        this.serviceID = serviceID;
        this.commandCode = commandCode;
        this.info = info;
        this.receiverTime = receiverTime;
    }

    public FReceiver() {
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSyntax(int i) {
        String syntax = "";
        if (this.info != null && !this.info.equals("")) {
            String[] items = this.info.trim().replaceAll(" +", " ").toUpperCase().split(" ");
            if (items.length >= i) {
                for (int j = 0; j < i; j++) {
                    syntax += items[j] + " ";
                }
                syntax = syntax.trim();
            }
        }
        return syntax;
    }

    public String getParam(int i) {
        String param = "";
        if (this.info != null && !this.info.equals("")) {
            String syntax = getSyntax(i);
            String formattedInfo = this.info.trim().replaceAll(" +", " ").toUpperCase();
            param = formattedInfo.substring(syntax.length()).trim();
        }
        return param;
    }

    public String getParamNormal(int i) {
        String param = "";
        if (this.info != null && !this.info.equals("")) {
            String syntax = getSyntax(i);
            String formattedInfo = this.info.trim().replaceAll(" +", " ");
            param = formattedInfo.substring(syntax.length()).trim();
        }
        return param;
    }

    public String debugString() {
        return "mo (CpCode=" + cpCode + ",RequestID=" + requestID
                + ",UserID=" + userID + ",ReceiverID=" + receiverID
                + ",ServiceID=" + serviceID + ",CommandCode=" + commandCode
                + ",Info=" + info + ",ReceiverTime=" + receiverTime + ")";
    }
}
