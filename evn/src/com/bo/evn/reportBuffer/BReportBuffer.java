package com.bo.evn.reportBuffer;

import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.dao.connection.DBTypes;
import com.dao.evn.reportBuffer.DReportBuffer;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.reportBuffer.FReportBuffer;
import com.inf.IDBKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BReportBuffer
{
	public BReportBuffer()
  {
  }

	public FBeans getAllColumnsInTable(FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this + "->getAllColumnsInTable()";
		FBeans result = null;
		Connection conn = null;
		try {
			if (IDBKey.getValue(DBTypes.DATABASE_TYPE.toString()).equals("-1")) {
				try {
					Class.forName(IDBKey.getValue(DBTypes.DATABASE_DRIVER
							.toString()));
				} catch (Exception e) {
				}
				conn = DriverManager.getConnection(IDBKey
						.getValue(DBTypes.DATABASE_URL.toString()));
			} else {
				conn = DBConnector.getConnection();
			}
			// DBConnector.startTransaction(conn);
			DReportBuffer dao = new DReportBuffer();
			result = dao.getAllColumnsInTable(conn, seed);
			// DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}
     
    public String[][] getAllRecord(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         String[][] result = null;
         Connection conn = null;
         try {
            if (IDBKey.getValue(DBTypes.DATABASE_TYPE.toString()).equals("-1")){

                  try {
                      Class.forName(IDBKey.getValue(DBTypes.DATABASE_DRIVER.toString()));
                  } catch (Exception e) {     
                  }
                  conn = DriverManager.getConnection(IDBKey.getValue(DBTypes.DATABASE_URL.toString()));
              }else{
                  conn = DBConnector.getConnection();
              }
    //             DBConnector.startTransaction(conn);
             DReportBuffer dao = new DReportBuffer();          
             result = dao.getAllRecord(conn,seed);
    //             DBConnector.endTransaction(conn);
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
    public FBeans getPaging(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getPaging()";
         FBeans result = null;
         Connection conn = null;
         try {
            if (IDBKey.getValue(DBTypes.DATABASE_TYPE.toString()).equals("-1")){
                try {
                    Class.forName(IDBKey.getValue(DBTypes.DATABASE_DRIVER.toString()));
                } catch (Exception e) {     
                }
                conn = DriverManager.getConnection(IDBKey.getValue(DBTypes.DATABASE_URL.toString()));
            }else{
                conn = DBConnector.getConnection();
            }

    //             DBConnector.startTransaction(conn);
             DReportBuffer dao = new DReportBuffer();          
             result = dao.getPaging(conn,seed);
    //             DBConnector.endTransaction(conn);
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
   public FReportBuffer getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getAppByID()";
        FReportBuffer result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DReportBuffer dao = new DReportBuffer();          
            result = dao.getRecordByID(conn,seed);
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
    public FBeans getAllRecord()throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DReportBuffer dao = new DReportBuffer();          
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_REPORTS_BUFFER,null);
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
             DReportBuffer dao = new DReportBuffer();          
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
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DReportBuffer dao = new DReportBuffer();          
             if(!dao.isExist(conn,seed)){
                result = dao.insert(conn,seed);
             }
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
    public boolean update(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DReportBuffer dao = new DReportBuffer();          
             if(!dao.isExist(conn,seed)){
                 result = dao.update(conn,seed);
             }
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

  
}
