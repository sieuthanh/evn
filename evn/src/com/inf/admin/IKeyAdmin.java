package com.inf.admin;

import com.inf.ICoreKey;

public class IKeyAdmin extends ICoreKey{  
      
      public static final String ADMINISTRATOR   = "Administrator";
      public static final boolean ADMIN_PANEL_ROLE= getValue("ADMIN.PANEL.ROLE","FALSE").equals("TRUE");
      public static final boolean ADMIN_PANEL_PRIVILEGE= getValue("ADMIN.PANEL.PRIVILEGE","FALSE").equals("TRUE");
      public static final boolean ADMIN_PANEL_APPS= getValue("ADMIN.PANEL.APPS","FALSE").equals("TRUE");
      public static final boolean ADMIN_PANEL_DEPARTMENT= getValue("ADMIN.PANEL.DEPARTMENT","FALSE").equals("TRUE");
      public static final boolean ADMIN_AUTO_LOG= getValue("ADMIN.AUTO.LOG","FALSE").equals("TRUE");
      public static final int ADMIN_LOGIN_RETRY=Integer.parseInt(getValue("ADMIN.LOGIN.RETRY","0"));
      public static final String ADMIN_CONNECTION_ID=getValue("ADMIN.CONNECTION.ID");
      public static final int LOG_MAX_DAYS= Integer.parseInt(getValue("LOG.MAX.DAYS","0"));    
      
}