package com.dao.admin.reportSystem;

import com.dao.DSqlAdmin;

import com.dao.admin.users.DUsers;

import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.reportSystem.FReportSystem;
import com.lib.AppConfigs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DReportSystem  extends DSqlAdmin
{
    public FBeans getMutiData(Connection cnn,FReportSystem bean) throws EException
    {
      final String LOCATION = this.toString() + "~~>getDataToExport()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      String[] values=null;
      FBeans beans=new FBeans();
      try
      {
        List params =new ArrayList();
        if(bean.getFromDate()!=null && !bean.getFromDate().equals("")){
            params.add(bean.getFromDate());
        }
        if(bean.getToDate()!=null && !bean.getToDate().equals("")){
            params.add(bean.getToDate());
        }
        prpstm = prepareStatement(cnn,bean.getSelectSql(),params); 
        rs = prpstm.executeQuery();
          rs.last(); 
          beans.setTotalRows(rs.getRow());
          beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }

            String[] fields=new String[rs.getMetaData().getColumnCount()];
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
            fields[i]=rs.getMetaData().getColumnLabel(i+1);
            }
            beans.add(fields);
          int p=0;
          while((rs != null) && (rs.next())&& (p<AppConfigs.APP_ROWS_VIEW)) {
          p++;
            values=new String[rs.getMetaData().getColumnCount()];
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                
                if(rs.getMetaData().getColumnTypeName(i+1).indexOf("timestamp")>=0){
                    values[i]=bean.dateToString(rs.getDate(i+1));
                }else{
                    values[i]=rs.getString(i+1);
                }
            }
            beans.add(values);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    
    public FBeans getOneData(Connection cnn,FReportSystem bean) throws EException
    {
      final String LOCATION = this.toString() + "~~>getOneData()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      String[][] values=null;
      FBeans beans=new FBeans();
      try
      {
        List params =new ArrayList();
        if(bean.getFromDate()!=null && !bean.getFromDate().equals("")){
            params.add(bean.stringToSqlDate(bean.getFromDate()));
        }
        if(bean.getToDate()!=null && !bean.getToDate().equals("")){
            params.add(bean.stringToSqlDate(bean.getToDate()));
        }
        prpstm = prepareStatement(cnn,bean.getSelectSql(),params); 
        rs = prpstm.executeQuery();
        if(rs != null && rs.next())
        {
            int total=rs.getMetaData().getColumnCount();
            values=new String[total][total];
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                values[i][0]=rs.getMetaData().getColumnLabel(i+1);
                values[i][1]=rs.getString(i+1);
            }
            beans.add(values);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    
 
  public FBeans getAll(Connection cnn) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAll()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FBeans beans = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_REPORT_SYSTEM_SELECT_ALL + ORDER_BY + REPORT_SYSTEM_NAME_OF_FILE_VN);     
      rs = prpstm.executeQuery();
      beans = new FBeans();
      FReportSystem beantemp = null;
      while((rs != null) && (rs.next()))
      {
          beantemp = new FReportSystem();
          beantemp.setId(rs.getInt(REPORT_SYSTEM_ID));
          beantemp.setFromDate(rs.getString(REPORT_SYSTEM_FROM_DATE));
          beantemp.setToDate(rs.getString(REPORT_SYSTEM_TO_DATE));
          beantemp.setRowNumber(rs.getInt(REPORT_SYSTEM_ROW_NUMBER));
          beantemp.setColumNumber(rs.getInt(REPORT_SYSTEM_COLUM_NUMBER));
          beantemp.setTotal(rs.getInt(REPORT_SYSTEM_TOTAL));
          beantemp.setSelectSql(rs.getString(REPORT_SYSTEM_SELECT_SQL));
          beantemp.setNameFile(rs.getString(REPORT_SYSTEM_NAME_FILE));
          beantemp.setNameOfFileVn(rs.getString(REPORT_SYSTEM_NAME_OF_FILE_VN));
          beantemp.setStylePrint(rs.getInt(REPORT_SYSTEM_STYLE_PRINT));
        beans.add(beantemp);
      }
    }
    catch(SQLException sqle)
    {
      if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
    }
    finally
    {
      closeResultSet(rs);
      closePreparedStatement(prpstm);
    }
    return beans;
  }

  
  public boolean addNew(Connection cnn, FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "addNew()";
    boolean result = true;
    PreparedStatement prstm = null;
    FReportSystem bean = null;
    try
    {
      bean = (FReportSystem)seed;
      prstm = cnn.prepareStatement(SQL_REPORT_SYSTEM_ADD_NEW);
        prstm.setString(PARAM_01,bean.getFromDate());
        prstm.setString(PARAM_02,bean.getToDate());
        prstm.setInt(PARAM_03,bean.getRowNumber());
        prstm.setInt(PARAM_04,bean.getColumNumber());
        prstm.setInt(PARAM_05,bean.getTotal());
        prstm.setString(PARAM_06,bean.getSelectSql());
        prstm.setString(PARAM_07,bean.getNameFile());
        prstm.setString(PARAM_08,bean.getNameOfFileVn());
        prstm.setInt(PARAM_09,bean.getStylePrint());
        prstm.setInt(PARAM_10,bean.getEnableTitle());
      prstm.execute();
    } catch(Exception sqle){
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      result= false;
    }
    finally
    {
      closePreparedStatement(prstm);
    }
    return result;
  }
  
  public boolean update(Connection cnn, FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + UPDATE;
    PreparedStatement prstm = null;
    FReportSystem bean = null;
    boolean result=false;
    try
    {
      bean = (FReportSystem)seed;
      prstm = cnn.prepareStatement(SQL_REPORT_SYSTEM_UPDATE);
        prstm.setString(PARAM_01,bean.getFromDate());
        prstm.setString(PARAM_02,bean.getToDate());
        prstm.setInt(PARAM_03,bean.getRowNumber());
        prstm.setInt(PARAM_04,bean.getColumNumber());
        prstm.setInt(PARAM_05,bean.getTotal());
        prstm.setString(PARAM_06,bean.getSelectSql());
        prstm.setString(PARAM_07,bean.getNameFile());
        prstm.setString(PARAM_08,bean.getNameOfFileVn());
        prstm.setInt(PARAM_09,bean.getStylePrint());
        prstm.setInt(PARAM_10,bean.getEnableTitle());
        prstm.setInt(PARAM_11,bean.getId());
      result=prstm.executeUpdate()>0;
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
  
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    FReportSystem bean = (FReportSystem)seed;
    return delete(cnn, TABLE_REPORT_SYSTEM, REPORT_SYSTEM_ID + EQUAL + bean.getId())>0;
  }  
  public FReportSystem getById(Connection cnn, FReportSystem bean) throws EException 
  {
    final String LOCATION = this.toString() + "getById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FReportSystem beantemp = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_REPORT_SYSTEM_SELECT_BY_ID);
      prpstm.setInt(PARAM_01,bean.getId());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
        beantemp = new FReportSystem();
        beantemp.setId(rs.getInt(REPORT_SYSTEM_ID));
        beantemp.setFromDate(rs.getString(REPORT_SYSTEM_FROM_DATE));
        beantemp.setToDate(rs.getString(REPORT_SYSTEM_TO_DATE));
        beantemp.setRowNumber(rs.getInt(REPORT_SYSTEM_ROW_NUMBER));
        beantemp.setColumNumber(rs.getInt(REPORT_SYSTEM_COLUM_NUMBER));
        beantemp.setTotal(rs.getInt(REPORT_SYSTEM_TOTAL));
        beantemp.setSelectSql(rs.getString(REPORT_SYSTEM_SELECT_SQL));
        beantemp.setNameFile(rs.getString(REPORT_SYSTEM_NAME_FILE));
        beantemp.setNameOfFileVn(rs.getString(REPORT_SYSTEM_NAME_OF_FILE_VN));
        beantemp.setStylePrint(rs.getInt(REPORT_SYSTEM_STYLE_PRINT));
        beantemp.setEnableTitle(rs.getInt(REPORT_SYSTEM_ENABLE_TITLE));
      }
    }
    catch(SQLException sqle)
    {
      if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
    }
    finally
    {
      closeResultSet(rs);
      closePreparedStatement(prpstm);
    }
    return beantemp;    
  }    

//XUAT RA BAO CAO 



 public String exportExcellVertical(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException {
     FReportSystem beanT=(FReportSystem)seed;
     String excelPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP;        
     String excelDown= excelPath + beanT.me.getSessionID();        
     File file = new File(excelPath,excelFile);    
     FileInputStream fis = new FileInputStream(file);
     POIFSFileSystem fs = new POIFSFileSystem(fis);
     HSSFWorkbook wb = new HSSFWorkbook(fs);
     HSSFSheet sheet=wb.getSheetAt(0);
     int y =beanT.getRowNumber();
     int k =beanT.getEnableTitle();
      if(beans!=null){
          HSSFRow row;
          if(beans!=null){
          int colum =beanT.getColumNumber();
             // sheet.shiftRows(y,sheet.getLastRowNum(),1); 
              for(int i=k;i<beans.size();i++){
                  String[] scrs=(String[])beans.get(i);
                  row = sheet.createRow(y++);    
                   //  row = sheet.getRow(y++);
                     if(i==0){
                         createCell(row, (short)(colum),"STT",wb);
                     }else{
                         createCell(row, (short)(colum),(i)+"",wb);
                     }
                  for(int j=0;j<scrs.length;j++){    
                        createCell(row, (short)(colum+j+1),scrs[j],wb);
                  }
                 
              }
                for (int i=y; i<sheet.getLastRowNum(); i++) {
                    row = sheet.getRow(y++);
                    try{
                        sheet.removeRow(row);
                    } catch (Exception ex)  {
                        ex.getMessage();
                    }
                    
                }

                if(beanT.getTotal()>0){
                  row = sheet.createRow(y++);
                  createCell(row, (short)(colum),"TC:",wb);
                  createCell(row, (short)(colum+1),beans.size()+"",wb);
              }
          }
      }
      
 
     FileOutputStream fos = new FileOutputStream(excelDown);
     wb.write(fos);        
     fos.close();   
     return excelDown;     
 }
 
    public String exportExcellHorizontal(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException {
        FReportSystem beanT=(FReportSystem)seed;
        String excelPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP;        
        String excelDown= excelPath + beanT.me.getSessionID();        
        File file = new File(excelPath,excelFile);    
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet=wb.getSheetAt(0);
        int y =beanT.getRowNumber();
         if(beans!=null){
             HSSFRow row;
             if(beans!=null){
                 sheet.shiftRows(y,sheet.getLastRowNum(),1); 
                 for(int i=0;i<beans.size();i++){
                     String[][] scrs=(String[][])beans.get(i);
                     for(int j=0;j<scrs.length;j++){    
                        row = sheet.createRow(y++);
                        createCell(row,(short)(beanT.getColumNumber()),scrs[j][1],wb);
                     }
                 }
                 if(beanT.getTotal()>0){
                     row = sheet.createRow(y++);
                     createCell(row, (short)(beanT.getColumNumber()-1),"TC:",wb);
                     createCell(row, (short)(beanT.getColumNumber()),beans.size()+"",wb);
                 }
             }
         }
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);        
        fos.close();   
        return excelDown;   
    }
    
    
    public void createCell(HSSFRow row, short column,String value,HSSFWorkbook wb){
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
//        cell.setEncoding(wb.eENCODING_UTF_16); 
        try  {
            cell.setCellValue(Integer.parseInt(value));    
        } catch (Exception ex)  {
            cell.setCellValue(new HSSFRichTextString(value)+"");
        }
    } 
    
    
 
}
