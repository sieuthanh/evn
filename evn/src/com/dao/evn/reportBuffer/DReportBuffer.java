package com.dao.evn.reportBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.reportBuffer.FReportBuffer;

import com.inf.ICoreAction;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DReportBuffer  extends DSqlEvn
{
    public FBeans getAllColumnsInTable(Connection cnn,FSeed seed) throws EException
     {
       final String LOCATION = this.toString() + "getAllColumnsInTable()";
       FBeans beans = new FBeans();
       FChangeData bean=(FChangeData)seed;
       PreparedStatement prstm = null;
         PreparedStatement prstm1 = null;
       ResultSet rs=null;
       ResultSet rs2 = null;
         ResultSet rs3 = null;
       String SQL_SELECT="SELECT * FROM ";
       try {
         List params =new ArrayList();
          List params1 =new ArrayList();
         SQL_SELECT+=bean.getNameTable();
           if(bean.getId_sql()==1){
               SQL_SELECT= bean.getNameSQL();
           }

          String[] tableFKKey=new String[2];
          String[] columnFKKey=new String[2];
           params1.add(bean.getNameTable());
           prstm1 = prepareStatement(cnn,SQL_SELECT_FOREIGN.trim(),params1);
           rs3 = prstm1.executeQuery();
           
           int f=0;
           while(rs3!=null && rs3.next()){
              columnFKKey[f]=rs3.getString("COLUMN_NAME");
              tableFKKey[f]= rs3.getString("TABLE_NAME");
               f++;
           }
          closeResultSet(rs3);
          closePreparedStatement(prstm1);
          
         prstm = cnn.prepareStatement(SQL_SELECT.trim());
         DatabaseMetaData dbmt = cnn.getMetaData();
         try  {
             rs2 = dbmt.getPrimaryKeys(null,null,bean.getNameTable());
         } catch (Exception ex)  {
         } finally  {
         }
          
         String columnPrimaryKey="";
         while(rs2!=null && rs2.next()){
           columnPrimaryKey+=rs2.getString("COLUMN_NAME");
         }
         rs = prstm.executeQuery();
           ResultSetMetaData rsmd =  rs.getMetaData();
           for(int i=1;i<rsmd.getColumnCount()+1;i++){
               FChangeData beanT = new FChangeData();
               if(rs2!=null && columnPrimaryKey.equals(rsmd.getColumnName(i))){
                   beanT.setNotNull(1);
               }else{
                   beanT.setNotNull(0);
               }
               boolean rsmdata_NoNulls =(rsmd.isNullable(i)==java.sql.DatabaseMetaData.columnNoNulls);
               if(rsmdata_NoNulls){
                   beanT.setNotNull(beanT.getNotNull()+1);          
               }
               beanT.setColumnName(rsmd.getColumnName(i));
               //---------
               for (int i1=0 ;i1<columnFKKey.length ;++i1 )  {
                   if(beanT.getColumnName().equals(columnFKKey[i1])){
                       beanT.setColumnFK(1);
                       beanT.setTableFK(tableFKKey[i1]);
                   }
                   
               }
               
               //-----------
               if((rsmd.getColumnTypeName(i).toLowerCase().indexOf("number")==0)||(rsmd.getColumnTypeName(i).toLowerCase().indexOf("int")==0)){
                   beanT.setColumnTypeName("1");
               }else if((rsmd.getColumnTypeName(i).toLowerCase().indexOf("time")==0)||(rsmd.getColumnTypeName(i).toLowerCase().indexOf("date")==0)){
                   beanT.setColumnTypeName("4");
               }else if(rsmd.getColumnTypeName(i).toLowerCase().indexOf("long")==0){
                   beanT.setColumnTypeName("3");
               }else{
                   beanT.setColumnTypeName("2");
               }
               
               beans.add(beanT);
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

    public String[][] getAllRecord(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAllRecord()";
      FChangeData bean=(FChangeData)seed;
      PreparedStatement prstm = null;
      ResultSet rs=null;
      String SQL_SELECT="SELECT * FROM ";
      String[][] mang=null;
      try {
        List params =new ArrayList();
          if(bean.getNameSQL().equals("")||bean.getNameSQL()==null){
              SQL_SELECT+=bean.getNameTable() + bean.getOrderBy();
          }else{
              SQL_SELECT+=bean.getNameTable()+WHERE + " UPPER("+ bean.getColumnName()+")" + LIKE+"'"+PER_CENT+bean.getNameSQL().toUpperCase()+ PER_CENT +"'" + bean.getOrderBy();
          }
         if(bean.getId_sql()==1){
             SQL_SELECT= bean.getNameSQL();
         }
         System.out.println("getAllRecord==>"+SQL_SELECT);
         prstm = prepareStatement(cnn,SQL_SELECT.trim(),params);
         
         rs = prstm.executeQuery();
         FBeans beansT = new FBeans();
         int tt=getValue(cnn,"(" +SQL_SELECT+") a", COUNT("*"), params) ;
         beansT.setTotalRows(tt);//getValue(cnn, "tblUser", COUNT("*"), null) );//count(cnn,SQL_SELECT,params));
          int checkP=(bean.getPageIndex()*AppConfigs.APP_ROWS_VIEW-tt);
         if(checkP>AppConfigs.APP_ROWS_VIEW){
             bean.setPageIndex(1);
         }
         beansT.setPageIndex(bean.getPageIndex());
         if(beansT.getFirstRecord()<=1){
           rs.beforeFirst();
         }else{
           rs.absolute(beansT.getFirstRecord()-1);
         }
          ResultSetMetaData rsmd =  rs.getMetaData();
          
          if(tt<AppConfigs.APP_ROWS_VIEW){
            mang=new String[tt+1][rsmd.getColumnCount()];
         }else{
              int view=(bean.getPageIndex()*AppConfigs.APP_ROWS_VIEW-tt);
              if(view>0){
                 mang=new String[AppConfigs.APP_ROWS_VIEW-view+1][rsmd.getColumnCount()];
              }else{
                  mang=new String[AppConfigs.APP_ROWS_VIEW+1][rsmd.getColumnCount()];
              }
          }
         
         int k=0;       
          for(int i=1;i<rsmd.getColumnCount()+1;i++){
              mang[k][i-1] = rsmd.getColumnName(i);
          }

       int ok=0;
         while((rs != null) && (rs.next())&&(k<AppConfigs.APP_ROWS_VIEW)){
             k++;
    //                System.out.println(rsmd.getColumnCount());
               for (int j = 0; j < rsmd.getColumnCount(); j++){ 
                 mang[k][j] = rs.getString(j+1);
                 if( mang[k][j]==null){
                	 mang[k][j]="(empty)";
                 }
               }
               ok++;
         }
//         if(ok==0){
//            mang=null;
//         }
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
      return mang;        
    }   
    public FBeans getPaging(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getPaging()";
      FBeans beans = new FBeans();
      FChangeData bean=(FChangeData)seed;
      PreparedStatement prstm = null;
      ResultSet rs=null;
      String SQL_SELECT="SELECT * FROM ";
      try {
        List params =new ArrayList();

         if(bean.getNameSQL().equals("")||bean.getNameSQL()==null){
             SQL_SELECT+=bean.getNameTable() + bean.getOrderBy();
         }else{
             SQL_SELECT+=bean.getNameTable()+WHERE + " UPPER("+ bean.getColumnName()+")" + LIKE+"'"+PER_CENT+bean.getNameSQL().toUpperCase()+ PER_CENT +"'"+ bean.getOrderBy();
//             System.out.println(SQL_SELECT);
         }
         if(bean.getId_sql()==1){
             SQL_SELECT= bean.getNameSQL();
         }
        prstm = prepareStatement(cnn,SQL_SELECT.trim(),params);
         System.out.println("getPaging==>"+SQL_SELECT);
        rs = prstm.executeQuery();
//        rs.last();
         beans.setTotalRows(getValue(cnn,"(" +SQL_SELECT+") a", COUNT("*"), params));
         beans.setPageIndex(bean.getPageIndex());
//         if(beans.getFirstRecord()<=1){
//           rs.beforeFirst();
//         }else{
//           rs.absolute(beans.getFirstRecord()-1);
//         }
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
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FReportBuffer bean = (FReportBuffer)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_REPORTS_BUFFER_INFORMATION);    
          pstmt.setString(PARAM_01,bean.getReport_Code());
          pstmt.setInt(PARAM_02,bean.getReport_Id());
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
    public FReportBuffer getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportBuffer bean = new FReportBuffer();
      bean = (FReportBuffer)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_REPORTS_BUFFER_BY_ID);
        prstm.setInt(PARAM_01,bean.getReport_Id());
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
          result = execute(cnn,SQL_INSERT_REPORTS_BUFFER,params)>0;
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
        FReportBuffer bean = (FReportBuffer)seed;
        List params = setParams(seed);
        params.add(bean.getReport_Id());
        result = execute(cnn,SQL_UPDATE_REPORTS_BUFFER,params)>0;
        //.print(SQL_UPDATE_REPORTS_BUFFER_APP);
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
      FReportBuffer bean = (FReportBuffer)seed;
      return 0 < delete(cnn, TABLE_REPORTS_BUFFER, REPORT_BUFFER_REPORT_ID + EQUAL + bean.getReport_Id());
    }    

    public FReportBuffer getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FReportBuffer bean = new FReportBuffer();
         try {
           bean.setReport_Id(rs.getInt(REPORT_BUFFER_REPORT_ID));
           bean.setReport_Code(rs.getString(REPORT_BUFFER_REPORT_CODE));
           bean.setFull_Name(rs.getString(REPORT_BUFFER_FULLNAME));
           bean.setShort_Name(rs.getString(REPORT_BUFFER_SHORTNAME));
           bean.setType(rs.getString(REPORT_BUFFER_TYPE));
           bean.setDescription(rs.getString(REPORT_BUFFER_DESCRIPTION));
           bean.setVersion(rs.getString(REPORT_BUFFER_VERSION));
           bean.setStart_Date(bean.dateToString(rs.getDate(REPORT_BUFFER_START_DATE)));
           bean.setActive(rs.getInt(REPORT_BUFFER_ACTIVE));
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
        FReportBuffer bean = (FReportBuffer)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getReport_Code());
             params.add(bean.getFull_Name());
             params.add(bean.getShort_Name());                           
             params.add(bean.getType());                           
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
