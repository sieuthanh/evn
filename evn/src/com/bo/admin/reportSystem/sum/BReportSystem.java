package com.bo.admin.reportSystem.sum;

import com.dao.admin.reportSystem.sum.DReportSystem;
import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.form.FSeed;
import com.form.FBeans;
import com.form.admin.reportSystem.sum.FReportSystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BReportSystem
{
    public FBeans getMutiData(FReportSystem bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getDataToExport()";
      Connection cnn = null;
      DReportSystem dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DReportSystem();
        beans = dao.getMutiData(cnn,bean);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }finally{
        DBConnector.closeConnection(cnn);
      }
      return beans;
    }
    public FBeans getOneData(FReportSystem bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getOneData()";
      Connection cnn = null;
      DReportSystem dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DReportSystem();
        beans = dao.getOneData(cnn,bean);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }finally{
        DBConnector.closeConnection(cnn);
      }
      return beans;
    }
  
 
     
  public FBeans getAll() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAll()";
    Connection cnn = null;
    DReportSystem dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DReportSystem();
      beans = dao.getAll(cnn);
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
    
  public boolean addNew(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = true;
    Connection conn = null;
    FReportSystem bean = (FReportSystem)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportSystem dao = new DReportSystem();             
        result = dao.addNew(conn,bean);
      DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        result = false;
     }
     finally 
     {
        DBConnector.closeConnection(conn);
     }    
     return result;
  }
  
  public boolean update(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->update()";
    Connection conn = null;
    FReportSystem bean = (FReportSystem)seed;
    boolean resutl=false;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportSystem dao = new DReportSystem();         
        resutl=dao.update(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
     }
     finally 
     {
        DBConnector.closeConnection(conn);
     }    
     return resutl;
  }  

  public boolean delete(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    boolean result=false;
    FReportSystem bean = (FReportSystem)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportSystem dao = new DReportSystem();  
        result=dao.delete(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
     }
     finally 
     {
        DBConnector.closeConnection(conn);
    }  
    return result;
  }  

  public FReportSystem getById(FReportSystem bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getById()";
   Connection cnn = null;
   DReportSystem dao = null;
   FReportSystem beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     dao = new DReportSystem();
     beantemp = dao.getById(cnn, bean);   
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
   return beantemp;
  }
//BAT DAU XUAT BAO CAO RA HE THONG

 public String exportExcellVertical(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException
 {
   final String LOCATION = this.toString() + "~>exportExcell()";
   Connection cnn = null;
   String result = null; 
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);        
     DReportSystem dao = new DReportSystem();
     result = dao.exportExcellVertical(beans,seed,excelFile);
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
   return result;
 } 
 
 
    public String exportExcellHorizontal(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException
    {
      final String LOCATION = this.toString() + "~>exportExcell()";
      Connection cnn = null;
      String result = null; 
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        DReportSystem dao = new DReportSystem();
        result = dao.exportExcellHorizontal(beans,seed,excelFile);
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
      return result;
    } 
    public FBeans getAllServicesCode() throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllServicesCode()";
      Connection cnn = null;
      DReportSystem dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DReportSystem();
        beans = dao.getAllServicesCode(cnn);
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
}
