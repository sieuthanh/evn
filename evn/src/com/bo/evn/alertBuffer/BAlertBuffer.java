package com.bo.evn.alertBuffer;

import java.sql.Connection;
import com.dao.connection.DBConnector;
import com.dao.evn.alertBuffer.DAlertBuffer;
import com.dao.evn.ticket.DTicket;

import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.ticket.FTicket;

import com.inf.DateProc;
import com.inf.IKey;

import com.lib.AppConfigs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import java.sql.SQLException;
import java.util.ArrayList;


public class BAlertBuffer {
    public FBeans getAllRecordBySentTimeIsNull()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordBySentTimeIsNull()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();   
             result = dao.getMultiRecords(conn,dao.SQL_SElECT_ALERT_BUFFER_BY_SENTTIME_ISNULL,null);
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
    public FBeans getAllRecordStatus_OP()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordStatus_OP()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();   
              ArrayList params=new ArrayList();
              params.add("F");
             result = dao.getMultiRecords(conn,dao.SQL_SElECT_ALERT_BUFFER_BY_STATUS,params);
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
    public FBeans getAllRecordActiveO()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordActiveO()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();   
              ArrayList params=new ArrayList();
             result = dao.getMultiRecords(conn,dao.SQL_SElECT_ALERT_BUFFER_BY_ID,params);
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
	public FAlertBuffer getAlertBufferByID(int id) throws Exception {

		final String LOCATION = this + "->getAlertBufferByID()";
		FAlertBuffer bean = new FAlertBuffer();
		Connection cnn = null;
		try {
			cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(cnn);
			DAlertBuffer dao = new DAlertBuffer();
			bean = dao.getAlertBufferByID(cnn, id);			
			DBConnector.endTransaction(cnn);
			
		} catch (Exception sqle) {
			DBConnector.rollBackTransaction(cnn);
			if (AppConfigs.APP_DEBUG)
				throw new Exception(LOCATION, sqle);
		} finally {
			DBConnector.closeConnection(cnn);
		}
		return bean;
	}
    public FAlertBuffer getAlertBufferTop(int top) throws Exception {

            final String LOCATION = this + "->getAlertBufferTop()";
            FAlertBuffer bean = new FAlertBuffer();
            Connection cnn = null;
            try {
                    cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                    DBConnector.startTransaction(cnn);
                    DAlertBuffer dao = new DAlertBuffer();
                    bean = dao.getAlertBufferTop(cnn, top);                 
                    DBConnector.endTransaction(cnn);
                    
            } catch (Exception sqle) {
                    DBConnector.rollBackTransaction(cnn);
                    if (AppConfigs.APP_DEBUG)
                            throw new Exception(LOCATION, sqle);
            } finally {
                    DBConnector.closeConnection(cnn);
            }
            return bean;
    }
    public FAlertBuffer insert(FSeed seed) throws Exception {

            final String LOCATION = this + "->insert()";
            FAlertBuffer bean = new FAlertBuffer();
            Connection cnn = null;
            FAlertBuffer beanS = (FAlertBuffer)seed;
            try {
                    cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                    DBConnector.startTransaction(cnn);
                    DAlertBuffer dao = new DAlertBuffer();
                    bean = dao.insert(cnn, beanS);                      
                    DBConnector.endTransaction(cnn);
                    
            } catch (Exception sqle) {
                    DBConnector.rollBackTransaction(cnn);
                    if (AppConfigs.APP_DEBUG)
                            throw new Exception(LOCATION, sqle);
            } finally {
                    DBConnector.closeConnection(cnn);
            }
            return bean;
    }
    public boolean insertAlert(ArrayList param,String sql) throws Exception {

            final String LOCATION = this + "->insert()";
            boolean bean = false;
            Connection cnn = null;
            try {
                    cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                    DBConnector.startTransaction(cnn);
                    DAlertBuffer dao = new DAlertBuffer();
                    bean = dao.insertAlert(cnn, param,sql);                      
                    DBConnector.endTransaction(cnn);
                    
            } catch (Exception sqle) {
                    DBConnector.rollBackTransaction(cnn);
                    if (AppConfigs.APP_DEBUG)
                            throw new Exception(LOCATION, sqle);
            } finally {
                    DBConnector.closeConnection(cnn);
            }
            return bean;
    }
    public boolean update()throws EException,SQLException
      {
         final String LOCATION = this + "->update()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();          
                 result = dao.update(conn);

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
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();          
             result = dao.delete(conn,seed);
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
    public boolean updateStatus(long id,String status)throws EException,SQLException
      {
         final String LOCATION = this + "->updateStatus()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();          
                 result = dao.updateStatus(conn,id,status);

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
    public boolean updateStatusByIDTicket(long id,String status)throws EException,SQLException
      {
         final String LOCATION = this + "->updateStatusByIDTicket()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAlertBuffer dao = new DAlertBuffer();          
                 result = dao.updateStatusByIDTicket(conn,id,status);

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
    public String exportXml(String sessionID,FAlertBuffer bean) throws  EException,FileNotFoundException,IOException
    {
      final String LOCATION = this.toString() + "~>exportExcell()";
      Connection cnn = null;
      String result = null; 
        DAlertBuffer dao = new DAlertBuffer();  
      try
      {      
          cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
          DBConnector.startTransaction(cnn);
          String SQL="select * from "+bean.getNameTable()+" where alert_id="+bean.getAlert_Id();
          String page=dao.readDB2Xml(cnn,bean.getNameTable(),SQL);
             String mt_log_folder = IKey.SYSTEM_FILE_XML;
           File dir = new File(mt_log_folder);
           if (!dir.exists()) {
               dir.mkdirs();
           }
          String nameFile=mt_log_folder+IKey.SYSTEM_FILE_SCHIP+sessionID;
           saveToFile(page,nameFile);

        result = nameFile;
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
    public static void saveToFile(String output, String filename) throws FileNotFoundException, 
                                                          UnsupportedEncodingException, 
                                                          IOException {
    
            FileOutputStream fos = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            Writer out = new BufferedWriter(osw);
                out.write(output);
            out.close();
    }
}