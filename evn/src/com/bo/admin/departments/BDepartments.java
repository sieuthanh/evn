package com.bo.admin.departments;

import com.dao.admin.departments.DDepartments;
import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;



import java.sql.Connection;
import java.sql.SQLException;

public class BDepartments
{
  
   
    
   
   
   
   
  
  
   public FDepartment getRecordByID(FSeed seed)throws EException,SQLException
  {
        final String LOCATION = this + "->getRecordByID()";
        FDepartment result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDepartments dao = new DDepartments();          
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
    public FBeans getAllRecord(int id)throws EException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();          
             result = dao.getMultiRecords(conn,id);
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
     
    public String getMembersDep(String members,int ruleId,long userId)throws EException
    {
         final String LOCATION = this + "->getMembersDep()";
         String result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();          
             result = dao.getUserByDeparmentInGroup(conn,members,ruleId,userId);
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
     
    public String getMembersGroup(String members,int ruleId,long userId)throws EException
    {
         final String LOCATION = this + "->getMembersGroup()";
         String result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();          
             result = dao.getUserMemberGroup(conn,members,ruleId,userId);
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
     
     
//    public FBeans getAllRecordByRule(FDocAssign bean,long userId)throws EException
//      {
//         final String LOCATION = this + "->getAllRecordByRule()";
//         FBeans result = null;
//         Connection conn = null;
//         try {
//             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
//             DBConnector.startTransaction(conn);
//             DDepartments dao = new DDepartments();          
//             result = dao.getAllRecordByRule(conn,bean,userId);
//             DBConnector.endTransaction(conn);            
//          }
//          catch (EException ex) {
//             DBConnector.rollBackTransaction(conn);
//             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
//          }
//          finally {
//             DBConnector.closeConnection(conn);
//         }
//     return result;    
//     }
     
   
     
  
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();    
             if(!dao.haveChild(conn,seed)){
                result = dao.delete(conn,seed);
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
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();          
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
             DDepartments dao = new DDepartments();          
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
    public FBeans getTreeMultiRecords(int id)throws EException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DDepartments dao = new DDepartments();          
             result = dao.getTreeMultiRecords(conn,id);
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
