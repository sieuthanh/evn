package com.ws;

import com.action.evn.AThread2;

import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.thread2.BThread2;
import com.bo.evn.ticket.BTicket;
import com.dao.connection.DBConnector;
import com.dao.evn.thread2.DThread2;

import com.exp.EException;
import com.form.FBeans;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import java.io.File;
import com.inf.IKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class pushdata extends HttpServlet { 
      

              public static String invoke (String aClass, String aMethod, Class[] params, Object[] args)
                 throws Exception {
                      
                Class c = Class.forName(aClass);
                Method m = c.getDeclaredMethod(aMethod, params);
                Object i = c.newInstance();
                Object r = m.invoke(i, args);
                return r.toString();
              }
     
    

      protected void doGet(HttpServletRequest request, 
          HttpServletResponse response) throws ServletException, IOException{
        // reading the user input
         String page="<?xml version=\"1.0\"?><pushdata>ERROR</pushdata>";
         AThread2 at=new AThread2();
         FTicket bean=new FTicket();
          getFmisdata ws =new getFmisdata();
         long ticketId=ws.getNumber(request.getParameter("ticketid"));
         String rs=at.pushdata(ticketId,bean);
          if(rs.equals("OK")){
              page="<?xml version=\"1.0\"?><pushdata>OK</pushdata>";
          }else{
              page="<?xml version=\"1.0\"?><pushdata>"+rs+"</pushdata>";
          }
          response.setContentType( "text/xml; charset=UTF-8");

          PrintWriter out = response.getWriter();
          out.println(page);
      }  
}