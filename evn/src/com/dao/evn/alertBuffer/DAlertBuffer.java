package com.dao.evn.alertBuffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dao.DSqlEvn;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.ticket.FTicket;

import com.inf.DateProc;
import com.lib.AppConfigs;

import huyenhoc.language;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class DAlertBuffer extends DSqlEvn {

	public FAlertBuffer getAlertBufferByID(Connection cnn,int id) throws Exception {
		final String LOCATION = this.toString() + "~~>getAlertBufferByID()";
		ResultSet rs = null;
		PreparedStatement prstm = null;
		FAlertBuffer bean = new FAlertBuffer();
		try {
			List<Object> params = new ArrayList<Object>();
			String SQL = SQL_SElECT_ALERT_BUFFER_BY_ID ;
			params.add(id);
			System.out.println(SQL);
			prstm = prepareStatement(cnn, SQL, params);
			rs = prstm.executeQuery();
			while (rs != null && rs.next()) {				
				bean= getInformation(rs);
			}
			
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new Exception(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return bean;
	}
    public FAlertBuffer getAlertBufferTop(Connection cnn,int top) throws Exception {
            final String LOCATION = this.toString() + "~~>getAlertBufferTop()";
            ResultSet rs = null;
            PreparedStatement prstm = null;
            FAlertBuffer bean = new FAlertBuffer();
            try {
                    List<Object> params = new ArrayList<Object>();
                    String SQL = "select * from ("+ SQL_SElECT_ALERT_BUFFER_TOP+") where ROWNUM<="+top;
                    System.out.println(SQL);
                    prstm = prepareStatement(cnn, SQL, params);
                    rs = prstm.executeQuery();
                    while (rs != null && rs.next()) {                               
                            bean= getInformation(rs);
                    }
                    
            } catch (SQLException sqle) {
                    if (AppConfigs.APP_DEBUG)
                            throw new Exception(LOCATION, sqle);
            } finally {
                    closeResultSet(rs);
                    closePreparedStatement(prstm);
            }
            return bean;
    }
    public FAlertBuffer insert(Connection cnn, FAlertBuffer bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(bean);
          System.out.println(SQL_INSERT_ALERT_BUFFER);
          result = execute(cnn,SQL_INSERT_ALERT_BUFFER,params)>0;
         
          if(!result)bean=null;
          //beanrs=getAlertBufferByID(cnn,bean.getEvn_id());
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return bean;    
    }
    public boolean insertAlert(Connection cnn, ArrayList params,String sql) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      try
      {
          result = execute(cnn,sql,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    public boolean update(Connection cnn) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
       // List params = setParams(seed);
       // params.add(bean.getSrc_Connect_Id());
       System.out.println(SQL_UPDATE_ALERT_BUFFER_ACTIVE);
        result = execute(cnn,SQL_UPDATE_ALERT_BUFFER_ACTIVE,null)>0;
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
      FAlertBuffer bean = (FAlertBuffer)seed;
      return 0 < delete(cnn, TABLE_ALERT_BUFFER, ALERT_BUFFER_ALERT_ID + EQUAL + bean.getAlert_Id());
    }     
    public boolean updateStatus(Connection cnn,long id,String status) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        List params = new ArrayList();
            params.add(status);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_ALERT_BUFFER_ACTIVE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
 
    public boolean updateStatusByIDTicket(Connection cnn,long id,String status) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        List params = new ArrayList();
            params.add(status);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_ALERT_BUFFER_ACTIVE_BY_IDTICKET,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
	public FAlertBuffer getInformation(ResultSet rs) throws EException {
		final String LOCATION = "->getInformation()";
		FAlertBuffer bean = new FAlertBuffer();
		try {
			bean.setAlert_Id(rs.getInt(ALERT_BUFFER_ALERT_ID));
			bean.setSrc_Connect_Id(rs.getInt(ALERT_BUFFER_SRC_CONNECT_ID));
			bean.setTicket_Id(rs.getInt(ALERT_BUFFER_TICKET_ID));
			bean.setSrc_Record_Id(rs.getInt(ALERT_BUFFER_SRC_RECORD_ID));
                        bean.setStatus(rs.getString(ALERT_BUFFER_STATUS));
			bean.setError_Code(rs.getString(ALERT_BUFFER_ERROR_CODE));
                        bean.setError_Description(rs.getString(ALERT_BUFFER_ERROR_DESCRIPTION));
			bean.setRequest_Id(rs.getInt(ALERT_BUFFER_REQUEST_ID));
                        bean.setDetect_Time(rs.getString(ALERT_BUFFER_DETECT_TIME));
			bean.setTable_Name(rs.getString(ALERT_BUFFER_TABLE_NAME));
			bean.setConcurent_Id(rs.getInt(ALERT_BUFFER_CONCURENT_ID));
			bean.setDescription(rs.getString(ALERT_BUFFER_DESCRIPTION));
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
		}
		return bean;
	}
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FAlertBuffer bean = (FAlertBuffer)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getAlert_Id());
             params.add(bean.getSrc_Connect_Id());
             params.add(bean.getTicket_Id());
             params.add(bean.getSrc_Record_Id());
             params.add(bean.getStatus());
             params.add(bean.getError_Code());
             params.add(bean.getError_Description());                           
             params.add(bean.getRequest_Id());                                               
             params.add(bean.getDetect_Time());                                               
             params.add(bean.getTable_Name());
             params.add(bean.getConcurent_Id());                                               
             params.add(bean.getDescription());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
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
    
    public static String readDB2Xml(Connection cnn, String nameTable,String SQL)throws EException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder;
        Document doc;
        Element results;
        ResultSet rs=null;
        ResultSetMetaData rsmd;
        int colCount;
        String page="";
        PreparedStatement prstm = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            results = doc.createElement(nameTable);
            doc.appendChild(results);
                try {
                    rs = cnn.createStatement().executeQuery(SQL);
                    rsmd = rs.getMetaData();
                    colCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        Element row = doc.createElement("ROW");
                        results.appendChild(row);
                        for (int i = 1; i <= colCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        Object value = rs.getObject(i);
            
                        if((rsmd.getColumnTypeName(i).toLowerCase().indexOf("time")==0)||(rsmd.getColumnTypeName(i).toLowerCase().indexOf("date")==0)){
                            value = DateProc.getYYYYMMDDHHMMSSString(rs.getTimestamp(i));
                        }
                        Element node = doc.createElement(columnName);
                          if(value==null){
                                     node.setAttribute("null", "true");
                            value="";
                          }
                                  node.appendChild(doc.createTextNode(language.toOnSign(value.toString())));
                                  row.appendChild(node);
                        }
                    }
//                    cnn.close();
                    rs.close();
                } catch (SQLException e)  {
                } finally  {
                }
      
    
        DOMSource domSource = new DOMSource(doc);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
            transformer = tf.newTransformer();
            transformer.transform(domSource, sr);
            page=sw.toString();
        } catch (ParserConfigurationException e) {
        } catch (TransformerConfigurationException e) {
        } catch (TransformerException e) {
            // TODO
        }

        return page;
    }
    
    
}
