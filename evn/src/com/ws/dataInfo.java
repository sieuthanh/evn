package com.ws;

import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;

import com.dao.connection.DBConnector;
import com.dao.evn.alertBuffer.DAlertBuffer;

import com.exp.EException;

import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;

import com.inf.ITables;

import com.lib.AppConfigs;

import huyenhoc.language;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class dataInfo extends HttpServlet { 
      

              public static String invoke (String aClass, String aMethod, Class[] params, Object[] args)
                 throws Exception {
                      
                Class c = Class.forName(aClass);
                Method m = c.getDeclaredMethod(aMethod, params);
                Object i = c.newInstance();
                Object r = m.invoke(i, args);
                return r.toString();
              }
    private FRequestTicket invalid(FRequestTicket bean){
        BSourceAccountBuffer bo =new BSourceAccountBuffer();
        BSourceConnectBuffer boCon=new BSourceConnectBuffer();
        FSourceConnectBuffer beanCon=new FSourceConnectBuffer();
        FSourceAccountBuffer beanAc=new FSourceAccountBuffer();
        try{
            beanAc.setShortName(bean.getUserName());
            beanAc.setAccount_Pass(bean.getPassWord());
            beanAc=bo.getSrcConnectByAccount(beanAc);
            
            bean.setEvn_id(beanAc.getAccount_Id());
            bean.setScr_connect_id(beanAc.getSrc_Connect_Id());
          if(beanAc.getSrc_Connect_Id()==0){
            bean.setResult("1");
            return bean;
          }
          beanCon.setSrc_Connect_Id(beanAc.getSrc_Connect_Id());
          beanCon=boCon.getRecordByID(beanCon);
          System.out.println("Connection:"+beanCon.getConnection());
        }catch (SQLException ex) {
        } catch (EException e) {
          // TODO
        }
        if(beanAc.getSrc_Connect_Id()<=0)bean.setResult("1");
        return bean;
    }        
    
    private long getNumber(String value){
    long result=0;
    try  {
        result=Long.parseLong(value);
    } catch (Exception ex)  {
        result=0;
    } finally  {
    }
    
        return result;
    }
      protected void doGet(HttpServletRequest request, 
          HttpServletResponse response) throws ServletException, IOException{
        // reading the user input
         String page="<?xml version=\"1.0\"?><datainfo>ERROR</datainfo>";

          FRequestTicket beanR=new FRequestTicket();
          beanR.setUserName(request.getParameter("usr"));
          beanR.setPassWord(request.getParameter("pas"));
          beanR.setResult("0");
          FRequestTicket beanRT=new FRequestTicket();
          beanRT=invalid(beanR);
          long ticketId=getNumber(request.getParameter("tk"));
             if(beanRT.getResult().equals("0")){
             
                 if(ticketId>0){
                 BTicket bo =new BTicket();
                    FTicket bean =new FTicket();
                    try {
                        bean=bo.getTicketByID(ticketId);
                        bean.getTable_name();
                        BTables boT =new BTables();
                        FTables beanT =new FTables();
                        beanT.setName(bean.getTable_name());
                        beanT=boT.getRecordByName(beanT);
                        
                        BSourceConnectBuffer boCon=new BSourceConnectBuffer();
                        FSourceConnectBuffer beanCon=new FSourceConnectBuffer();
                        beanCon.setSrc_Connect_Id(beanT.getDes_connect_id());
                        beanCon=boCon.getRecordByID(beanCon);
    
                        Connection conn = null;
                        Class.forName(beanCon.getConnection());
                        conn = DriverManager.getConnection(beanCon.getUrl());
                        try {
                            page=getTicketInfo(conn,ticketId,bean.getTable_name());
                        }catch (Exception e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                        }
    
                    } catch (Exception e) {
                        // TODO
                    }
                }
             }else{
                 beanR.setResult("2");
             }
          
          response.setContentType( "text/xml; charset=UTF-8");
          PrintWriter out = response.getWriter();
          
          out.println(page);

 
      }  
    public static String getTicketInfo(Connection con,long id,String nameTable)throws EException,SQLException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder;
        Document doc;
        Element results;
        ResultSet rs=null;
        ResultSetMetaData rsmd;
        int colCount;
        String page="";
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            results = doc.createElement(nameTable);
            doc.appendChild(results);

                try {
                    con = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
                    rs = con.createStatement().executeQuery("select * from "+nameTable+" where ticket_id="+id+"");
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
                    con.close();
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
