package com.dao.admin.apps;
import com.dao.DSqlAdmin;

import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.apps.FApp;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DApps  extends DSqlAdmin
{
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FApp bean = (FApp)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_APPS_INFORMATION);    
          ////.println(SQL_SELECT_APPS_INFORMATION);
          pstmt.setString(PARAM_01,bean.getCode());
          pstmt.setInt(PARAM_02,bean.getId());
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
    public FApp getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FApp bean = new FApp();
      bean = (FApp)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_APPS_BY_ID);
        //println(SQL_SELECT_APPS_BY_ID);
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
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(seed);
          params.add(getMaxAppId(cnn));
          //.print(SQL_INSERT_APPS_APP);
          result = execute(cnn,SQL_INSERT_APPS_APP,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public int getMaxAppId(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getMaxAppId()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      int result = 0;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_APPS_MAX);
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            result = rs.getInt(APPS_APP_ID);
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
      return result;        
    }
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FApp bean = (FApp)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_APPS_APP,params)>0;
        //.print(SQL_UPDATE_APPS_APP);
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    public boolean swap(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FApp bean = (FApp)seed;
        int id = bean.getId();
        int idSwap = bean.getIdSwap();
      //   //println(SELECT + APPS_ORDERBY + FROM + TABLE_APPS + WHERE + APPS_APP_ID + EQUAL + id);

        int order = getValue(cnn,SELECT + APPS_ORDERBY + FROM + TABLE_APPS + WHERE + APPS_APP_ID + EQUAL + id,APPS_ORDERBY);
        List params = new ArrayList();
        params.add(idSwap);
        params.add(id);
        result = execute(cnn,SQL_UPDATE_APPS_SWAP,params)>0;
        //.println(SQL_UPDATE_APPS_SWAP);
        if(result){
            params.set(0,order);
            params.set(1,idSwap);
            result = execute(cnn,SQL_UPDATE_APPS_ORDER,params)>0;
            //.println(SQL_UPDATE_APPS_ORDER);
        }
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
      FApp bean = (FApp)seed;
      return 0 < delete(cnn, TABLE_APPS, APPS_APP_ID + EQUAL + bean.getId());
    }    

    public FApp getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FApp app = new FApp();
         try {
           app.setId(rs.getInt(APPS_APP_ID));
           app.setCode(rs.getString(APPS_CODE));
           app.setName(rs.getString(APPS_NAME));
           app.setLink(rs.getString(APPS_LINK));
           app.setBlock(rs.getInt(APPS_BLOCK));
           app.setClassName(rs.getString(APPS_CLASSNAME));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return app;
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
        FApp bean = (FApp)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCode());
             params.add(bean.getName());
             params.add(bean.getLink());                           
             params.add(bean.getBlock());                           
             params.add(bean.getClassName());                           
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
