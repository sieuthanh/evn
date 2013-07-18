package com.dao.evn.sourceConnectBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;

import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DSourceConnectBuffer  extends DSqlEvn
{
 
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FSourceConnectBuffer bean = (FSourceConnectBuffer)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_SOURCE_CONNECT_BUFFER_INFORMATION);    
          pstmt.setString(PARAM_01,bean.getSrc_Code());
          pstmt.setInt(PARAM_02,bean.getSrc_Connect_Id());
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
    public FSourceConnectBuffer getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FSourceConnectBuffer bean = new FSourceConnectBuffer();
      bean = (FSourceConnectBuffer)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_SOURCE_CONNECT_BUFFER_BY_ID);
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
          result = execute(cnn,SQL_INSERT_SOURCE_CONNECT_BUFFER,params)>0;
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
        FSourceConnectBuffer bean = (FSourceConnectBuffer)seed;
        List params = setParams(seed);
        params.add(bean.getSrc_Connect_Id());
        result = execute(cnn,SQL_UPDATE_SOURCE_CONNECT_BUFFER,params)>0;
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
      FSourceConnectBuffer bean = (FSourceConnectBuffer)seed;
      return 0 < delete(cnn, TABLE_SOURCE_CONNECT_TO_BUFFER, SOURCE_ACCOUNT_BUFFER_SRC_CONNECT_ID + EQUAL + bean.getSrc_Connect_Id());
    }    

    public FSourceConnectBuffer getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FSourceConnectBuffer bean = new FSourceConnectBuffer();
         try {
           bean.setSrc_Connect_Id(rs.getInt(SOURCE_CONNECT_TO_BUFFER_SRC_CONNECT_ID));
           bean.setSrc_Code(rs.getString(SOURCE_CONNECT_TO_BUFFER_SRC_CODE));
           bean.setFullName(rs.getString(SOURCE_CONNECT_TO_BUFFER_FULLNAME));
           bean.setShortName(rs.getString(SOURCE_CONNECT_TO_BUFFER_SHORTNAME));
           bean.setType(rs.getString(SOURCE_CONNECT_TO_BUFFER_TYPE));
           bean.setDescription(rs.getString(SOURCE_CONNECT_TO_BUFFER_DESCRIPTION));
           bean.setUrl(rs.getString(SOURCE_CONNECT_TO_BUFFER_URL));
           bean.setConnection(rs.getString(SOURCE_CONNECT_TO_BUFFER_CONNECTION));
           bean.setActive(rs.getInt(SOURCE_CONNECT_TO_BUFFER_ACTIVE));
           bean.setDes_Connect_Id(rs.getInt(SOURCE_CONNECT_TO_BUFFER_DES_CONNECT_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public FSourceConnectBuffer getInformationDes(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FSourceConnectBuffer bean = new FSourceConnectBuffer();
         try {
           bean.setDes_Connect_Id(rs.getInt(SOURCE_CONNECT_TO_BUFFER_SRC_CONNECT_ID));
           bean.setSrc_Code(rs.getString(SOURCE_CONNECT_TO_BUFFER_SRC_CODE));
           bean.setFullName(rs.getString(SOURCE_CONNECT_TO_BUFFER_FULLNAME));
           bean.setShortName(rs.getString(SOURCE_CONNECT_TO_BUFFER_SHORTNAME));
           bean.setType(rs.getString(SOURCE_CONNECT_TO_BUFFER_TYPE));
           bean.setDescription(rs.getString(SOURCE_CONNECT_TO_BUFFER_DESCRIPTION));
           bean.setUrl(rs.getString(SOURCE_CONNECT_TO_BUFFER_URL));
           bean.setConnection(rs.getString(SOURCE_CONNECT_TO_BUFFER_CONNECTION));
           bean.setActive(rs.getInt(SOURCE_CONNECT_TO_BUFFER_ACTIVE));
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
        FSourceConnectBuffer bean = (FSourceConnectBuffer)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getSrc_Code());
             params.add(bean.getFullName());
             params.add(bean.getShortName());                           
             params.add(bean.getType());                           
             params.add(bean.getDescription());                           
             params.add(bean.getUrl());                           
             params.add(bean.getConnection());                           
             params.add(bean.getActive());                           
             params.add(bean.getDes_Connect_Id());                           
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
