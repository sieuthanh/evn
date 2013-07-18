package com.ws;

import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;

import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;

import com.inf.DateProc;

import java.io.File;
import com.inf.IKey;

import huyenhoc.language;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class getCmisdata extends HttpServlet { 
      

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
         String page="<?xml version=\"1.0\"?><getcmisdata>ERROR</getcmisdata>";
         BTicket boT =new BTicket();
         FTicket bean=new FTicket();
          BTables boTable =new BTables();
          FTables beanTable=new FTables();
          BSourceConnectBuffer boSource =new BSourceConnectBuffer();
          FSourceConnectBuffer beanSource=new FSourceConnectBuffer();
          getFmisdata ws =new getFmisdata();
         int ticketId=ws.getNumber(request.getParameter("ticketid"));
         if(ticketId>0){
             try {
                 bean=boT.getTicketByID(ticketId);
                 String tables =bean.getTable_name();
                 beanTable.setName(tables);
                 if(tables.contains(",")){
                     tables.replaceAll(" ","");
                     String[] table = tables.split(",");
                     beanTable.setName(table[0]);
                 }
                 beanTable.setSrc_connect_id(bean.getSrc_connect_id());
                 beanTable=boTable.getRecordByName(beanTable);

                 beanSource.setSrc_Connect_Id(beanTable.getSrc_connect_id());
                 beanSource=boSource.getRecordByID(beanSource);
                 BSourceAccountBuffer boAc =new BSourceAccountBuffer();
                 FSourceAccountBuffer beanAc=new FSourceAccountBuffer();
                 beanAc.setSrc_Connect_Id(beanTable.getSrc_connect_id());
                 beanAc=boAc.getRecordBySrc(beanAc);

                 if(beanSource.getType().equals("W")){
                    // page=ws.readWebsite(beanSource.getUrl()+ String.format( IKey.WS_FUNCTION_GETDATA,beanAc.getShortName(),beanAc.getAccount_Pass(), ticketId+""));
//                     language.toOnSign(page);
                     Long curTimeInMinute = new Long( System.currentTimeMillis());
                     //log file
                      String folder=DateProc.TimestampYYYYMM(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEvn_time()),IKey.SYSTEM_FILE_SCHIP);
                      String mt_log_folder = IKey.SYSTEM_FILE_XML+folder;
                        File dir = new File(mt_log_folder);
                          if (!dir.exists()) {
                              dir.mkdirs();
                          }
                    String nameFile=ticketId+"."+curTimeInMinute+".xml";
                    
                    // ws.saveToFile(page,mt_log_folder+IKey.SYSTEM_FILE_SCHIP+nameFile);
                     
                     
                     
                     URL website = new URL(beanSource.getUrl()+ String.format( IKey.WS_FUNCTION_GETDATA, ticketId+""));
                     System.out.println("save is:"+copyFile(website,mt_log_folder+IKey.SYSTEM_FILE_SCHIP+nameFile));
                     
                     boT.update_ticket_rasoat(bean.getTicket_id(),folder+IKey.SYSTEM_FILE_SCHIP+nameFile);
                     page="<?xml version=\"1.0\"?><getcmisdata>OK</getcmisdata>";
                 }
             }catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
             }
         }

          response.setContentType( "text/xml; charset=UTF-8");

          PrintWriter out = response.getWriter();
          
          out.println(page);

 
      }  
    public static boolean copyFile(URL from, String to) {
    System.out.println("url:"+from);
          System.out.println("save to file:"+to);
          BufferedInputStream urlin = null;
          BufferedOutputStream fout = null;
          try {
              int bufSize = 8 * 1024;
              urlin = new BufferedInputStream(
                      from.openConnection().getInputStream(),
                      bufSize);
              fout = new BufferedOutputStream(new FileOutputStream(to), bufSize);
              copyPipe(urlin, fout, bufSize);
          }
          catch (IOException ioex) {
              return false;
          }
          catch (SecurityException sx) {
              return false;
          }
          finally {
              if (urlin != null) {
                  try {
                      urlin.close();
                  }
                  catch (IOException cioex) {
                  }
              }
              if (fout != null) {
                  try {
                      fout.close();
                  }
                  catch (IOException cioex) {
                  }
              }
          }
          return true;
      }
    public static void copyPipe(InputStream in, OutputStream out, int bufSizeHint)
            throws IOException {
        int read = -1;
        byte[] buf = new byte[bufSizeHint];
        while ((read = in.read(buf, 0, bufSizeHint)) >= 0) {
            out.write(buf, 0, read);
        }
        out.flush();
    }
}
