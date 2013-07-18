package com.dao.evn.tables;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.evn.tables.FTables;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTables  extends DSqlEvn
{
 
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FTables bean = (FTables)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_TABLES_EXIST);    
          pstmt.setString(PARAM_01,bean.getName());
          pstmt.setInt(PARAM_02,bean.getSrc_connect_id());
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
    public boolean isExistName(Connection conn, String name)throws EException{
    final String LOCATION = "->isExistName()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_TABLES_ISEXIST_NAME);    
          pstmt.setString(PARAM_01,name);
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
    public FTables getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTables bean = new FTables();
      bean = (FTables)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_TABLES_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
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
    public FTables getRecordByName(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByName()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTables beanT = new FTables();
      FTables bean = (FTables)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_TABLES_BY_NAME);
        prstm.setString(PARAM_01,bean.getName());
          prstm.setInt(PARAM_02,bean.getSrc_connect_id());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beanT = getInformation(rs);
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
      return beanT;        
    }
    public FTables getRecordByTop1(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByTop1()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTables bean = new FTables();
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_TABLES_BY_TOP1);
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
          result = execute(cnn,SQL_INSERT_TABLES,params)>0;
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
        FTables bean = (FTables)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_TABLES,params)>0;
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
      FTables bean = (FTables)seed;
      return 0 < delete(cnn, TABLE_TABLES, TABLES_ID + EQUAL + bean.getId());
    }    

    public FTables getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FTables bean = new FTables();
         try {
           bean.setId(rs.getInt(TABLES_ID));
           bean.setName(rs.getString(TABLES_NAME));
           bean.setKey(rs.getString(TABLES_KEY));
           bean.setSrc_connect_id(rs.getInt(TABLES_SRC_CONNECT_ID));
           bean.setDescription(rs.getString(TABLES_DESCRIPTION));
             bean.setDes_connect_id(rs.getInt(TABLES_DES_CONNECT_ID));
             bean.setData_fields(rs.getString(TABLES_DATA_FIELDS));
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
        String checkName="";
        while((rs != null) && (rs.next()))
        {
            if(!checkName.equals(rs.getString(TABLES_NAME))){
                checkName=rs.getString(TABLES_NAME);
                beans.add(getInformation(rs));
            }
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
        FTables bean = (FTables)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getName());
             params.add(bean.getKey());
             params.add(bean.getSrc_connect_id());                           
             params.add(bean.getDescription());                           
             params.add(bean.getDes_connect_id());                           
             params.add(bean.getData_fields());                           
                         
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
