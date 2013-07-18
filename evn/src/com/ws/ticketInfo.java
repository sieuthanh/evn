package com.ws;

import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.ticket.BTicket;
import com.exp.EException;

import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ticketInfo extends HttpServlet { 
      

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
    
    private int getNumber(String value){
    int result=0;
    try  {
        result=Integer.parseInt(value);
    } catch (Exception ex)  {
        result=0;
    } finally  {
    }
    
        return result;
    }
      protected void doGet(HttpServletRequest request, 
          HttpServletResponse response) throws ServletException, IOException{
        // reading the user input

          FRequestTicket beanR=new FRequestTicket();
          beanR.setUserName(request.getParameter("usr"));
          beanR.setPassWord(request.getParameter("pas"));
          beanR.setResult("0");
          FRequestTicket beanRT=new FRequestTicket();
          beanRT=invalid(beanR);
          
             BTicket boT =new BTicket();
             FTicket bean=new FTicket();
          int ticketId=getNumber(request.getParameter("tk"));
             if(beanRT.getResult().equals("0")){
                 try {
                     bean=boT.getTicketByID(ticketId);
                 }catch (Exception e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                 }
             }else{
                 beanR.setResult("2");
             }
          
          response.setContentType( "text/xml; charset=UTF-8");

          PrintWriter out = response.getWriter();
          
          out.println("<?xml version=\"1.0\"?>");
          out.println("<getTicket>");
              out.println("<ticket_id>");
              out.println(bean.getTicket_id());
              out.println("</ticket_id>");
          out.println("<ticket_id_err>");
          out.println(bean.getTicket_id_err());
          out.println("</ticket_id_err>");
              out.println("<src_connect_id>");
              out.println(bean.getSrc_connect_id());
              out.println("</src_connect_id>");
              out.println("<status>");
              out.println(bean.getStatus());
              out.println("</status>");
              out.println("<active>");
              out.println(bean.getActive());
              out.println("</active>");
              out.println("<evn_id>");
              out.println(bean.getEvn_id());
              out.println("</evn_id>");
              out.println("<evn_time>");
              out.println(bean.getEvn_time());
              out.println("</evn_time>");
          out.println("<buffer_id>");
          out.println(bean.getBuffer_id());
          out.println("</buffer_id>");
          out.println("<buffer_time>");
          out.println(bean.getBuffer_time());
          out.println("</buffer_time>");
          out.println("<ebs_id>");
          out.println(bean.getEbs_id());
          out.println("</ebs_id>");
          out.println("<ebs_time>");
          out.println(bean.getEbs_time());
          out.println("</ebs_time>");
          out.println("<start_time>");
          out.println(bean.getStart_time());
          out.println("</start_time>");
          out.println("<end_time>");
          out.println(bean.getEnd_time());
          out.println("</end_time>");
              out.println("<total_records>");
              out.println(bean.getTotal_records());
              out.println("</total_records>");
              out.println("<code>");
              out.println(bean.getCode());
              out.println("</code>");
              out.println("<table_name>");
              out.println(bean.getTable_name());
              out.println("</table_name>");
          out.println("<where_data>");
          out.println(bean.getWheres());
          out.println("</where_data>");
          out.println("<error_description>");
          out.println(bean.getDescription());
          out.println("</error_description>");
              out.println("<result>");
              out.println(beanR.getResult());
              out.println("</result>");
          out.println("</getTicket>");
 
      }  

}
