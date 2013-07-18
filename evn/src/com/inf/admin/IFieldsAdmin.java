package com.inf.admin;

import com.inf.IParams;

public interface IFieldsAdmin extends IParams {

//  SET OF FIELDS ON DEPARTMENT TABLES
     public final String DEPARTMENTS_DEPARTMENT_ID="DEPARTMENT_ID";
     public final String DEPARTMENTS_CODE="CODE";
     public final String DEPARTMENTS_NAME="NAME";
     public final String DEPARTMENTS_PARENT_ID="PARENT_ID";
     public final String DEPARTMENTS_MAX_USERS="MAX_USERS";
     public final String[] DEPARTMENTS_ALL_FIELDS = {DEPARTMENTS_CODE, DEPARTMENTS_NAME,DEPARTMENTS_PARENT_ID}; 

//  SET OF FIELDS ON ADMIN TABLES
     public final String USERS_USER_ID ="USER_ID";
     public final String USERS_USERNAME="USERNAME";
     public final String USERS_PASSWORD="PASSWORD";
     public final String USERS_FULLNAME="FULLNAME";
     public final String USERS_PICTURE ="PICTURE";
     public final String USERS_EMAIL="EMAIL";
     public final String USERS_PHONE="PHONE";
     public final String USERS_ADDRESS="ADDRESS";
     public final String USERS_DESCRIPTION="DESCRIPTION";
     public final String USERS_DATE_CREATE="DATE_CREATE";
     public final String USERS_DATE_PASSWORD="DATE_PASSWORD";
     public final String USERS_DATE_LOGIN="DATE_LOGIN";
     public final String USERS_SEX="SEX";
     public final String USERS_DEPARTMENT_ID="DEPARTMENT_ID";
     public final String USERS_ROLE="ROLE";
     public final String USERS_PRIVILEGE="PRIVILEGE";
     public final String USERS_ACTIVE="ACTIVE";
     public final String USERS_PERIOD="PERIOD";
     public final String USERS_GROUP_ID="GROUP_ID";
     public final String USERS_APP="APP";
     public final String USERS_CHANGE_PASSWORD="CHANGE_PASSWORD";
    public final String USERS_SMS="SMS";
    public final String USERS_AREA="AREA";

    public final String[] USERS_ALL_FIELDS = {USERS_USERNAME, USERS_PASSWORD, USERS_FULLNAME, USERS_PICTURE,
                                              USERS_EMAIL, USERS_PHONE, USERS_ADDRESS, USERS_DESCRIPTION,
                                              USERS_DATE_CREATE,USERS_DATE_PASSWORD,USERS_DATE_LOGIN,
                                              USERS_SEX, USERS_DEPARTMENT_ID, USERS_ROLE, USERS_PRIVILEGE,
                                              USERS_ACTIVE, USERS_PERIOD,USERS_GROUP_ID,USERS_APP,USERS_CHANGE_PASSWORD,USERS_SMS,USERS_AREA
                                              };

    //  SET OF FIELDS ON TABLE PRIVILEGE
    public final String PRIVILEGE_ROLE_ID="PRIVILEGE_ID";
    public final String PRIVILEGE_NAME="NAME";
    public final String PRIVILEGE_DESCRIPTION="DESCRIPTION";

    //  SET OF FIELDS ON TABLE ROLE
    public final String ROLE_ROLE_ID="ROLE_ID";
    public final String ROLE_NAME="NAME";
    public final String ROLE_DESCRIPTION="DESCRIPTION";
    
    //  SET OF FIELDS ON TABLE GROUPS
    public final String GROUPS_GROUP_ID="GROUP_ID";
    public final String GROUPS_CODE="CODE";
    public final String GROUPS_NAME="NAME";
    public final String GROUPS_PARENT_ID="PARENT_ID";
    public final String GROUPS_ROLE="ROLE";
    public final String GROUPS_PRIVILEGE="PRIVILEGE";
    public final String GROUPS_DATE_CREATE="DATE_CREATE";
    public final String GROUPS_APP="APP";

    public final String[] GROUPS_ALL_FIELDS = {GROUPS_CODE, GROUPS_NAME, GROUPS_PARENT_ID,
                                               GROUPS_ROLE, GROUPS_PRIVILEGE,GROUPS_APP}; 

    //  SET OF FIELDS ON TABLE APPS
    public final String APPS_APP_ID="APP_ID";
    public final String APPS_CODE="CODE";
    public final String APPS_NAME="NAME";
    public final String APPS_LINK="LINK";
    public final String APPS_BLOCK="BLOCK";
    public final String APPS_CLASSNAME="CLASSNAME";
    public final String APPS_ORDERBY="ORDERBY";

    public final String[] APPS_ALL_FIELDS = {APPS_CODE, APPS_NAME, APPS_LINK, APPS_BLOCK, APPS_CLASSNAME}; 

    public final String LOG_LOG_ID="LOG_ID";
    public final String LOG_USERNAME="USERNAME";
    public final String LOG_DATE_LOG="DATE_LOG";
    public final String LOG_RESULT="RESULT"; //0 : LOGIN IS FAIL; 1: LOGIN SUCCESS; 2: LOGOUT
    public final String LOG_APP_ID="APP_ID";
    public final String LOG_HOST="HOST";
    public final String[] LOG_ALL_FIELDS = {LOG_USERNAME,LOG_DATE_LOG, LOG_RESULT, LOG_APP_ID,LOG_HOST}; 
    
    // FIELDS GROUP_USER TABLE
    public final String GROUP_USER_GROUP_ID = "GROUP_ID";
    public final String GROUP_USER_USER_ID = "USER_ID";    
    public final String[] GROUP_USER_ALL_FIELDS = {GROUP_USER_GROUP_ID,GROUP_USER_USER_ID};
 
   
   
      
    //SET FIELDS ON SMS
    public final String REPORT_SYSTEM_ID            =   "ID";
    public final String REPORT_SYSTEM_FROM_DATE=   "FROM_DATE";
    public final String REPORT_SYSTEM_TO_DATE=   "TO_DATE";
    public final String REPORT_SYSTEM_ROW_NUMBER=   "ROW_NUMBER";
    public final String REPORT_SYSTEM_COLUM_NUMBER=   "COLUM_NUMBER";
    public final String REPORT_SYSTEM_TOTAL=   "TOTAL";
    public final String REPORT_SYSTEM_SELECT_SQL=   "SELECT_SQL";
    public final String REPORT_SYSTEM_NAME_FILE=   "NAME_FILE";
    public final String REPORT_SYSTEM_NAME_OF_FILE_VN=   "NAME_OF_FILE_VN";
    public final String REPORT_SYSTEM_STYLE_PRINT=   "STYLE_PRINT";
    public final String REPORT_SYSTEM_ENABLE_TITLE=   "ENABLE_TITLE";
    public final String REPORT_SYSTEM_TYPECHART=   "TYPECHART";
    public final String REPORT_SYSTEM_POINTINTERVAL=   "POINTINTERVAL";
    public final String REPORT_SYSTEM_MAXZOOM=   "MAXZOOM";
    public final String REPORT_SYSTEM_TIMEREFRESH=   "TIMEREFRESH";
    public final String REPORT_SYSTEM_DATESTART=   "DATESTART";
    public final String REPORT_SYSTEM_ENABLE_CHART=   "ENABLE_CHART";
    public final String REPORT_SYSTEM_ENABLE_PAGING=   "ENABLE_PAGING";
    
    public final String[] REPORT_SYSTEM_ALL_FIELDS  =   {REPORT_SYSTEM_FROM_DATE,REPORT_SYSTEM_TO_DATE,REPORT_SYSTEM_ROW_NUMBER,REPORT_SYSTEM_COLUM_NUMBER,REPORT_SYSTEM_TOTAL,REPORT_SYSTEM_SELECT_SQL,REPORT_SYSTEM_NAME_FILE,
    REPORT_SYSTEM_NAME_OF_FILE_VN,REPORT_SYSTEM_STYLE_PRINT,REPORT_SYSTEM_ENABLE_TITLE,REPORT_SYSTEM_TYPECHART,REPORT_SYSTEM_POINTINTERVAL,REPORT_SYSTEM_MAXZOOM,
    REPORT_SYSTEM_TIMEREFRESH,REPORT_SYSTEM_DATESTART,REPORT_SYSTEM_ENABLE_CHART,REPORT_SYSTEM_ENABLE_PAGING};
}
