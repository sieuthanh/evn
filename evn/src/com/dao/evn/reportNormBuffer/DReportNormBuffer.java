package com.dao.evn.reportNormBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.evn.reportNormBuffer.FReportNormBuffer;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DReportNormBuffer  extends DSqlEvn
{
 
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FReportNormBuffer bean = (FReportNormBuffer)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_REPORTS_NORM_BUFFER_INFORMATION);    
          pstmt.setString(PARAM_01,bean.getNorm_Code());
          pstmt.setInt(PARAM_02,bean.getNorm_Id());
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
    public FReportNormBuffer getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportNormBuffer bean = new FReportNormBuffer();
      bean = (FReportNormBuffer)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_REPORTS_NORM_BUFFER_BY_ID);
        prstm.setInt(PARAM_01,bean.getNorm_Id());
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
          result = execute(cnn,SQL_INSERT_REPORTS_NORM_BUFFER,params)>0;
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
        FReportNormBuffer bean = (FReportNormBuffer)seed;
        List params = setParams(seed);
        params.add(bean.getNorm_Id());
        result = execute(cnn,SQL_UPDATE_REPORTS_NORM_BUFFER,params)>0;
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
      FReportNormBuffer bean = (FReportNormBuffer)seed;
      return 0 < delete(cnn, TABLE_REPORTS_NORM_BUFFER, REPORT_NORM_BUFFER_NORM_ID + EQUAL + bean.getNorm_Id());
    }    

    public FReportNormBuffer getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FReportNormBuffer bean = new FReportNormBuffer();
         try {
           bean.setNorm_Id(rs.getInt(REPORT_NORM_BUFFER_NORM_ID));
           bean.setReport_Id(rs.getInt(REPORT_NORM_BUFFER_REPORT_ID));
           bean.setNorm_Code(rs.getString(REPORT_NORM_BUFFER_NORM_CODE));
           bean.setNorm_Name(rs.getString(REPORT_NORM_BUFFER_NORM_NAME));
           bean.setType(rs.getString(REPORT_NORM_BUFFER_NORM_TYPE));
           bean.setNorm_Total(rs.getInt(REPORT_NORM_BUFFER_NORM_TOTAL));
           bean.setDescription(rs.getString(REPORT_NORM_BUFFER_DESCRIPTION));
           bean.setVersion(rs.getString(REPORT_NORM_BUFFER_VERSION));
           bean.setStart_Date(bean.dateToString(rs.getDate(REPORT_NORM_BUFFER_START_DATE)));
           bean.setActive(rs.getInt(REPORT_NORM_BUFFER_ACTIVE));
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
        FReportNormBuffer bean = (FReportNormBuffer)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getReport_Id());                           
             params.add(bean.getNorm_Code());
             params.add(bean.getNorm_Name());
             params.add(bean.getType());
             params.add(bean.getNorm_Total());                           
             params.add(bean.getDescription());                           
             params.add(bean.getVersion());                           
             params.add(new Timestamp(new Date().getTime()));                           
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
