package com.bo.admin.users;

import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.dao.admin.users.DUsers;
import java.sql.Connection;
import java.sql.SQLException;

public class BUsers
{
  public BUsers()
  {
  }
    public FBeans getAllUser() throws  EException
       {
        final String LOCATION = this.toString() + "~>getAllUser()";
        Connection cnn = null;
        DUsers dao = null;
        FBeans beans =new FBeans();
        try
        {      
          cnn = DBConnector.getConnection();
          DBConnector.startTransaction(cnn);
          dao = new DUsers();
          beans = dao.getAllUser(cnn);    
          DBConnector.endTransaction(cnn);
        }
        catch (EException sqle)
        {
          DBConnector.rollBackTransaction(cnn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
        }
        finally
        {
          DBConnector.closeConnection(cnn);
        }
        return beans;
       }     
       
    public FBeans search(FUser bean)throws EException
      {
         final String LOCATION = this + "->getUserByGroupID()";
         FBeans beans = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             beans = dao.search(conn,bean);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return beans;    
     }
     
     
    public boolean checkLogin(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->checkLogin()";
         boolean result = false;
         Connection conn = null;
         try {
             conn=DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers users = new DUsers();
             result = users.checkLogin(conn,seed) && users.getUserInformation(conn,seed);
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
     

    public boolean updateLastVisit(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateLastVisit()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             result = dao.updateLastVisit(conn,seed);
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
    public FBeans getUserByGroupID_DepID(FUser bean,int idGroup, int idDep, int pageIndex)throws EException
      {
         final String LOCATION = this + "->getUserByGroupID()";
         FBeans beans = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             beans = dao.getUserByGroupID_DepID(conn,bean,idGroup, idDep, pageIndex);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return beans;    
     }
    public FBeans getUserByDepartmentID(int id,int pageIndex)throws EException
      {
         final String LOCATION = this + "->getUserByDepartmentID()";
         FBeans beans = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             beans = dao.getUserByDepartmentID(conn,id,pageIndex);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return beans;    
     }
    public boolean toggleActive(FSeed seed)throws EException
      {
         final String LOCATION = this + "->toggleActive()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             result = dao.toggleActive(conn,seed);
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
             DUsers dao = new DUsers();          
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
    public int insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         int result = 0; //thanh cong
         Connection conn = null;
         
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers(); 
             //Kiem tra xem co trung ten k
             if(!dao.isExist(conn,seed)){
                result = dao.insert(conn,seed);
             }else{ result =1;}
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
             DUsers dao = new DUsers();          
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
    public boolean lock(String username)throws EException
      {
         final String LOCATION = this + "->lock()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();
             result = dao.lock(conn,username);
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
   
    public boolean updateNewpass(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DUsers dao = new DUsers();          
             if(dao.checkOldpass(conn,seed)){
                 result = dao.updateNewpass(conn,seed);
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
   
    public boolean checkChangePas(FSeed seed)throws EException,SQLException
         {
            final String LOCATION = this + "->getRecordByID()";
            boolean result = false;
            Connection conn = null;
            try {
                conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                DBConnector.startTransaction(conn);
                DUsers dao = new DUsers();          
                result = dao.checkChangePas(conn,seed);
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
  
  public FUser getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getRecordByID()";
        FUser result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DUsers dao = new DUsers();          
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
   
}
