package com.inf.admin;

public interface ITablesAdmin {
    //  SET OF ADMIN TABLES IN DATABASE
    
    public final String TABLE_USERS      =   "USERS";     //COMMENT
    public final String TABLE_GROUPS     =   "GROUPS";     //COMMENT
    public final String TABLE_ROLE       =   "ROLE";     //COMMENT
    public final String TABLE_PRIVILEGE  =   "PRIVILEGE";     //COMMENT
    public final String TABLE_APPS       =   "APPS";     //COMMENT
    public final String TABLE_LOG        =   "LOG";     //COMMENT
    
    //  SET OF CATEGORY TABLES IN DATABASE
    public final String TABLE_DEPARTMENTS       =   "DEPARTMENT";     //COMMENT
    
    public final String TABLE_GROUP_USER       =   "GROUP_USER";
    
    public final String TABLE_TASK_ASSIGN_RULES          =   "TASK_ASSIGN_RULES";
    public final String TABLE_TASK_ASSIGN_BOSS           =   "TASK_ASSIGN_BOSS";
    public final String TABLE_TASK_ASSIGN_OFFICERS       =   "TASK_ASSIGN_OFFICERS";
    
    // table rule from foryou
    public final String TABLE_FORYOU_RULES          =   "FORYOU_RULES";
    public final String TABLE_FORYOU_BOSS           =   "FORYOU_BOSS";
    public final String TABLE_FORYOU_OFFICERS       =   "FORYOU_OFFICERS";
    
   
    
    public final String TABLE_REPORT_RULES           = "REPORT_RULES";
    public final String TABLE_REPORT_BOSS            = "REPORT_BOSS";
    public final String TABLE_REPORT_OFFICERS        = "REPORT_OFFICERS";

    public final String TABLE_MENU        = "MENU";
    public final String TABLE_UPERMISION        = "UPERMISION";
    public final String TABLE_GPERMISION        = "GPERMISION";
    
    public final String TABLE_TEMPLATE_CATEGORY_ADMIN = "TEMPLATE_CATEGORY_ADMIN";
    
    public final String TABLE_MYCONTACT = "MYCONTACT";
    public final String TABLE_PGROUPS = "PGROUPS";
    public final String TABLE_TRAILER_STORE = "TRAILER_STORE";
    public final String TABLE_PRESENT_MODULE = "PRESENT_MODULE";
    
    public final String TABLE_SMS= "SMS";
    public final String TABLE_LOG_SMS= "LOG_SMS";
    public final String TABLE_REPORT_SYSTEM= "REPORTSYSTEM_ZOOM";
}
