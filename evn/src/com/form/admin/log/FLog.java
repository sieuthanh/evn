package com.form.admin.log;

import com.form.FSeed;

import com.inf.IConstants;

import com.users.OnlineUser;

import javax.servlet.http.HttpServletRequest;

public class FLog extends FSeed
{
    private int id;
    private String username;
    private String dateLogin;
    private String timeLogin;
    private int result=-1;
    private String host;
    private String appName;
    private int pageIndex;
    
    public FLog()
    {
    }
  public FLog(String username,HttpServletRequest request)
  {
    setUsername(username);
    setAppName(request.getRequestURL().toString());
    setHost(request.getRemoteHost());
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public String getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(String dateLogin) {
        this.dateLogin = dateLogin;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(String timeLogin) {
        this.timeLogin = timeLogin;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
