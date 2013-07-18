package com.bo.evn.ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.connection.DBConnector;
import com.dao.evn.alertBuffer.DAlertBuffer;
import com.dao.evn.ticket.DTicket;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.ticket.FTicket;
import com.inf.IKey;
import com.lib.AppConfigs;


public class BTicket {
    public FBeans getAllRecordRaSoat(int active)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordRaSoat()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();   
              ArrayList params=new ArrayList();
              params.add(active);
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_TICKET_BUFFER_ALL,params);
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
    public FBeans getAllRecordActiveO(int active)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecordActiveO()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();   
              ArrayList params=new ArrayList();
              params.add(active);
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_TICKET_BUFFER2,params);
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
    
	public FTicket getTicketByID(long id) throws Exception {

		final String LOCATION = this + "->getTicketByID()";
		FTicket bean = new FTicket();
		Connection cnn = null;
		try {
			cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(cnn);
			DTicket dao = new DTicket();
			bean = dao.getTicketByID(cnn, id);			
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
    public FTicket insert(FSeed seed) throws Exception {

            final String LOCATION = this + "->insert()";
            FTicket bean = new FTicket();
            Connection cnn = null;
            FTicket beanS = (FTicket)seed;
            try {
                    cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                    DBConnector.startTransaction(cnn);
                    DTicket dao = new DTicket();
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
	public FTicket getTicketByReportCode(String code) throws Exception {

		final String LOCATION = this + "->getTicketByReportCode()";
		FTicket bean = new FTicket();
		Connection cnn = null;
		try {
			cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(cnn);
			DTicket dao = new DTicket();
			bean = dao.getTicketByReportCode(cnn, code);			
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
    public boolean update_ticket_rasoat(long id,String nameFile)throws EException,SQLException
      {
         final String LOCATION = this + "->update()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
                 result = dao.update_ticket_rasoat(conn,id,nameFile);

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
    public boolean updateAll(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateAll()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
//             if(!dao.isExist(conn,seed)){
                 result = dao.updateAll(conn,seed);
//             }
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
    public boolean updateStatusInsert(long id,String status,int active,int where)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
             result = dao.updateStatusInsert(conn,id,status, active, where);
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
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
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
    public boolean updateActive(long id,int active)throws EException,SQLException
      {
         final String LOCATION = this + "->updateActive()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
                 result = dao.updateActive(conn,id,active);

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
   
    public boolean updateWhere(long id,int where)throws EException,SQLException
      {
         final String LOCATION = this + "->updateWhere()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DTicket dao = new DTicket();          
                 result = dao.updateWhere(conn,id,where);

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
    public String exportXml(String sessionID,FTicket bean) throws  EException,FileNotFoundException,IOException
    {
        final String LOCATION = this.toString() + "~>exportExcell()";
        Connection cnn = null;
        String result = null; 
          DAlertBuffer dao = new DAlertBuffer();  
        try
        {      
            cnn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(cnn);
            String SQL="select * from "+bean.getNameTable()+" where ticket_id="+bean.getTicket_id();

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