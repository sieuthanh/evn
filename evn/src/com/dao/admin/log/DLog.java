package com.dao.admin.log;
import com.dao.DSqlAdmin;

import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.log.FLog;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class DLog  extends DSqlAdmin
{
    public boolean addnew(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>addnew";
      Boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          FLog bean = (FLog)seed;
          prstm = cnn.prepareStatement(SQL_ADDNEW_LOG_LOGIN);
          prstm.setString(PARAM_01,bean.getUsername());
          Timestamp now = new Timestamp(System.currentTimeMillis());
          prstm.setTimestamp(PARAM_02,now);
          prstm.setInt(PARAM_03,bean.getResult());
          prstm.setString(PARAM_04, bean.getAppName());
          prstm.setString(PARAM_05,bean.getHost());
          result = prstm.executeUpdate()>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;    
    }
    

    public boolean deleteOldLog(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>deleteOldLog";
      boolean result = false;
      PreparedStatement prstm = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_DELETE_LOG_LOGIN);
          FSeed seed = new FSeed();
          prstm.setDate(PARAM_01,seed.addDays(seed.getCurrentSqlDate(),-1*AppConfigs.LOG_MAX_DAYS));
          result = prstm.executeUpdate()>=0;
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closePreparedStatement(prstm);
        }
        return result;
    } 
    
    
    
    public FBeans getAllLog(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAllLogSearch()";
      FLog bean = new FLog();
      if(seed!=null)  bean = (FLog)seed;
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {   
        prstm = getPreparedStatement(cnn,seed);        
        rs = prstm.executeQuery();
        //Lay thong tin ra
        beans = new FBeans();
        rs.last(); 
        beans.setTotalRows(rs.getRow());
        beans.setPageIndex(bean.getPageIndex());
          if(beans.getFirstRecord()<=1){
              rs.beforeFirst();
          }else{
              rs.absolute(beans.getFirstRecord()-1);
          }
          int i=0;
          while((rs != null) && (rs.next())&& (i<AppConfigs.APP_ROWS_VIEW))
          {
            i++;
            FLog userlog = new FLog();
            userlog.setAppName(rs.getString(LOG_APP_ID));
            Timestamp now = rs.getTimestamp(LOG_DATE_LOG);
            userlog.setDateLogin(userlog.dateToString(new Date(now.getTime())));
            userlog.setTimeLogin(userlog.dateToString(new Date(now.getTime()),AppConfigs.APP_TIME_DEFAULT));
            userlog.setUsername(rs.getString(LOG_USERNAME));
            userlog.setResult(rs.getInt(LOG_RESULT));
            userlog.setHost(rs.getString(LOG_HOST));
            beans.add(userlog);
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
    private PreparedStatement getPreparedStatement(Connection cnn,FSeed seed) throws EException{
        String WHERE = "" ;
        String ORDER = ORDER_BY + LOG_DATE_LOG + DESC;
        List params = new ArrayList();
        if(seed!=null){
            FLog bean = (FLog)seed;
            if (bean.isDate(bean.getDateLogin())){
//            //.println(AND + "TO_CHAR" + OPEN + LOG_DATE_LOG + COMMA + QUESTION + CLOSE + EQUAL + QUESTION);
              
                WHERE += AND + LOG_DATE_LOG + ">=" + QUESTION + AND + LOG_DATE_LOG + "<" + QUESTION ;
//trong oracle
//                WHERE += AND + "TO_CHAR" + OPEN + LOG_DATE_LOG + COMMA + QUESTION + CLOSE + EQUAL + QUESTION;
//                params.add(AppConfigs.APP_DATE_DEFAULT);
 //end oracle
                Date date = bean.stringToSqlDate(bean.getDateLogin());
                params.add(date);
                params.add(bean.addDays(date,1));
            }
            if (bean.getUsername().length()>0) {
                WHERE += AND + LOG_USERNAME +EQUAL + QUESTION;
                params.add(bean.getUsername());
            }
            if (bean.getResult()>-1) {
                WHERE += AND + LOG_RESULT +EQUAL + QUESTION;
                params.add(bean.getResult());
            }
            if (bean.getHost().length()>0) {
                WHERE += AND + LOG_HOST +EQUAL + QUESTION;
                params.add(bean.getHost());
            }
        }
        
        String SQL_SEARCH_LOGS_ALL = SQL_SEARCH_LOGS + WHERE + ORDER;
        //.println(SQL_SEARCH_LOGS_ALL);
        return prepareStatement(cnn,SQL_SEARCH_LOGS_ALL,params);
    }

  
      public FBeans getbeansLogUsername(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getbeansLogUsername()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_LOGS_USERNAME);
        ////.println(SQL_SELECT_LOGS_USERNAME);
        rs = prstm.executeQuery();
        beans = new FBeans();
            while((rs != null) && (rs.next()))
            {
              FLog userlog = new FLog();
              userlog.setUsername(rs.getString(LOG_USERNAME));
              beans.add(userlog);
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
    public FBeans getbeansLogHost(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getbeansLogHost()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_LOGS_HOST);
        ////.println(SQL_SELECT_LOGS_HOST);
        rs = prstm.executeQuery();
        beans = new FBeans();
            while((rs != null) && (rs.next()))
            {
              FLog userlog = new FLog();
              userlog.setHost(rs.getString(LOG_HOST));
              beans.add(userlog);
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
}
