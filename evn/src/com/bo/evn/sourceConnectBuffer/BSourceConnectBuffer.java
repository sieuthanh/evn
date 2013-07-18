package com.bo.evn.sourceConnectBuffer;

import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.dao.evn.sourceConnectBuffer.DSourceConnectBuffer;

import com.dao.evn.thread2.DThread2;

import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.FChangeData;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class BSourceConnectBuffer
{
    public String getConn(FSeed seed){
         final String LOCATION = this + "->getConn()";
         String result = "";
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;

        try {
            Class.forName(bean.getConnection());
            conn = DriverManager.getConnection(bean.getUrl());
                  result=conn.toString();
        } catch (ClassNotFoundException e) {
            // TODO
             result=e.getMessage();
        }catch (Exception ex){
            result=ex.getMessage();
        }
     return result;    
     } 

   public FSourceConnectBuffer getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getAppByID()";
        FSourceConnectBuffer result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DSourceConnectBuffer dao = new DSourceConnectBuffer();          
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
             DSourceConnectBuffer dao = new DSourceConnectBuffer();          
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_SOURCE_CONNECT_BUFFER,null);
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

    public FBeans getAllRecordByExitsTable(String nameTable)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordByExitsTable()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DSourceConnectBuffer dao = new DSourceConnectBuffer();   
              List params = new ArrayList();
              params.add(nameTable);
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_SOURCE_CONNECT_BUFFER_BY_EXITS_TABLE,params);
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
             DSourceConnectBuffer dao = new DSourceConnectBuffer();          
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
             DSourceConnectBuffer dao = new DSourceConnectBuffer();          
//             if(!dao.isExist(conn,seed)){
                result = dao.insert(conn,seed);
//             }
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
             DSourceConnectBuffer dao = new DSourceConnectBuffer();          
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

  
}
