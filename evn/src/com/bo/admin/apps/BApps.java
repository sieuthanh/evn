package com.bo.admin.apps;


import com.dao.admin.apps.DApps;

import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.apps.FApp;
import java.sql.Connection;
import java.sql.SQLException;

public class BApps
{
  public BApps()
  {
  }
   public FApp getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getAppByID()";
        FApp result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DApps dao = new DApps();          
            result = dao.getRecordByID(conn,seed);
            DBConnector.endTransaction(conn);            
         }
         catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
         }
         finally {
            DBConnector.closeConnection(conn);
        }
    return result;    
    }
    public FBeans getAllRecord()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DApps dao = new DApps();          
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_APPS,null);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public FBeans getActiveRecord()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DApps dao = new DApps();          
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_APPS_NONE_BLOCK,null);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DApps dao = new DApps();          
             result = dao.delete(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DApps dao = new DApps();          
             if(!dao.isExist(conn,seed)){
                result = dao.insert(conn,seed);
             }
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean update(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DApps dao = new DApps();          
             if(!dao.isExist(conn,seed)){
                 result = dao.update(conn,seed);
             }
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }

        public boolean swap(FSeed seed)throws EException,SQLException
          {
             final String LOCATION = this + "->insert()";
             boolean result = false;
             Connection conn = null;
             try {
                 conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                 DBConnector.startTransaction(conn);
                 DApps dao = new DApps();          
                 result = dao.swap(conn,seed);
                 DBConnector.endTransaction(conn);            
              }
              catch (EException ex) {
                 DBConnector.rollBackTransaction(conn);
                 if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
              }
              finally {
                 DBConnector.closeConnection(conn);
             }
         return result;    
         }

}
