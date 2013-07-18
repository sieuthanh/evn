package com.dao.evn.sourceAccountBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;

import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DSourceAccountBuffer  extends DSqlEvn
{
    public FSourceAccountBuffer getSrcConnectByAccount(Connection conn,FSeed seed)throws EException{
    final String LOCATION = "->getSrcConnectByAccount()";
    FSourceAccountBuffer bean=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
        bean = (FSourceAccountBuffer)seed;
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_SOURCE_ACCOUNT_BUFFER_EXIT_ACCOUNT);    
          pstmt.setString(PARAM_01,bean.getShortName());
          pstmt.setString(PARAM_02,bean.getAccount_Pass());
          rs = pstmt.executeQuery();
         if((rs != null) && (rs.next()))
         {
             bean = getInformation(rs);
         }
     
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return bean;
    }
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FSourceAccountBuffer bean = (FSourceAccountBuffer)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_SOURCE_ACCOUNT_BUFFER_INFORMATION);    
          pstmt.setString(PARAM_01,bean.getShortName());
          pstmt.setInt(PARAM_02,bean.getAccount_Id());
          rs = pstmt.executeQuery();
          result = rs!=null && rs.next();
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
    public FSourceAccountBuffer getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FSourceAccountBuffer bean = new FSourceAccountBuffer();
      bean = (FSourceAccountBuffer)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_SOURCE_ACCOUNT_BUFFER_BY_ID);
        prstm.setInt(PARAM_01,bean.getAccount_Id());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    
    public FSourceAccountBuffer getRecordBySrc(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordBySrc()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FSourceAccountBuffer bean = new FSourceAccountBuffer();
      bean = (FSourceAccountBuffer)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_SOURCE_ACCOUNT_BUFFER_BY_SRC);
        prstm.setInt(PARAM_01,bean.getSrc_Connect_Id());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_SOURCE_ACCOUNT_BUFFER,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
 
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FSourceAccountBuffer bean = (FSourceAccountBuffer)seed;
        List params = setParams(seed);
        params.add(bean.getAccount_Id());
        result = execute(cnn,SQL_UPDATE_SOURCE_ACCOUNT_BUFFER,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      FSourceAccountBuffer bean = (FSourceAccountBuffer)seed;
      return 0 < delete(cnn, TABLE_SOURCE_ACCOUNT_BUFFER, SOURCE_ACCOUNT_BUFFER_ACCOUNT_ID + EQUAL + bean.getAccount_Id());
    }    

    public FSourceAccountBuffer getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FSourceAccountBuffer bean = new FSourceAccountBuffer();
         try {
           bean.setAccount_Id(rs.getInt(SOURCE_ACCOUNT_BUFFER_ACCOUNT_ID));
           bean.setAccount_Pass(rs.getString(SOURCE_ACCOUNT_BUFFER_ACCOUNT_PASS));
           bean.setFullName(rs.getString(SOURCE_ACCOUNT_BUFFER_FULLNAME));
           bean.setShortName(rs.getString(SOURCE_ACCOUNT_BUFFER_SHORTNAME));
           bean.setSrc_Connect_Id(rs.getInt(SOURCE_ACCOUNT_BUFFER_SRC_CONNECT_ID));
           bean.setDescription(rs.getString(SOURCE_ACCOUNT_BUFFER_DESCRIPTION));
           bean.setQuota(rs.getInt(SOURCE_ACCOUNT_BUFFER_QUOTA));
           bean.setActive(rs.getInt(SOURCE_ACCOUNT_BUFFER_ACTIVE));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            beans.add(getInformation(rs));
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FSourceAccountBuffer bean = (FSourceAccountBuffer)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getAccount_Pass());
             params.add(bean.getFullName());
             params.add(bean.getShortName());                           
             params.add(bean.getSrc_Connect_Id());                           
             params.add(bean.getDescription());                           
             params.add(bean.getQuota());                           
             params.add(bean.getActive());                           
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
