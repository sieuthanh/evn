package com.action.evn.ticket;

import com.action.ACore;
import com.action.evn.AThread1;
import com.action.evn.AThread2;

import com.action.evn.AThread2AddQueue;

import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;

import com.bo.evn.ticket.BTicket;

import com.exp.EException;

import com.form.admin.FFunction;
import com.form.evn.FChangeData;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

import com.form.evn.ticket.FTicket;

import com.inf.DateProc;
import com.inf.IFields;
import com.inf.IKey;
import com.inf.ITables;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URLEncoder;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ATicket extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
       
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FTicket bean = (FTicket)form;
        String anchor=bean.getValue(APP_ANCHOR,"");       
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        if(bean.getSearch_time()==null){
            bean.setSearch_time(bean.dateToString(bean.getCurrentSqlDate(),"dd/MM/yyyy"));
        }
        if(!errors.isEmpty()){
        
            target="_PREPARED";
        }else if(anchor.equals(_EDIT)){
        
            BTicket apps = new BTicket();
            if(apps.updateAll(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.update.evn.error"));   
            }
            request.setAttribute("BTables",bean);
           target = "_PREPARED";
               
        }else if(anchor.equals(_CREATE)){
                BTicket apps = new BTicket();

            try {
                bean=apps.insert(bean);
                if(bean!=null){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.evn.error"));   
                }
            } catch (Exception e) {
                // TODO
            }
                 request.setAttribute("BTables",bean);

            target = "_PREPARED";
        }else if(anchor.equals(_DELETE)){
                BTicket apps = new BTicket();
                if(apps.updateStatus(bean.getTicket_id(),"D")){
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
            FChangeData beant=new FChangeData();
            beant.setNameTable(bean.getNameTable());
            beant.setId_sql(1);
            beant.setNameSQL("SELECT a.ticket_id, a.ticket_id_err, b.fullname, a.status,\n" + 
            "       a.active, a.evn_id, a.evn_time, a.buffer_id, a.buffer_time,\n" + 
            "       a.ebs_id, a.ebs_time, a.start_time, a.end_time, a.total_records,\n" + 
            "       a.code, a.table_name, a.where_data, a.error_description\n" + 
            "  FROM evn_ticket_buffer a,evn_source_connect_to_buffer b\n" + 
            "  where a.src_connect_id=b.src_connect_id order by a.ticket_id desc");

            request.setAttribute("BColumnsList",new BReportBuffer().getAllColumnsInTable(beant));
            request.setAttribute("BRecordList",new BReportBuffer().getAllRecord(beant));
            request.setAttribute("BPaging",new BReportBuffer().getPaging(beant));
            request.setAttribute("BTables",bean);

            anchor = "_VIEW_TICKET";
        }else if(anchor.equals(_PREPARED_EDIT)){
            BTicket apps = new BTicket();
            String namet=bean.getNameTable();

            try {
                bean = apps.getTicketByID(bean.getTicket_id());
            } catch (Exception e) {
                // TODO
            }
            bean.setNameTable(namet);
            request.setAttribute("BTables",bean);

            target="_PREPARED";
        }else if(anchor.equals(_PREPARED_CREATE)){
               request.setAttribute("BTables",bean);
                bean.reset();
                bean.setStart_time(DateProc.getYYYYMMDDHHMMString(DateProc.createTimestamp()));
                bean.setEnd_time(DateProc.getYYYYMMDDHHMMString(DateProc.createTimestamp()));
                target="_PREPARED";
        }else if(anchor.equals("_SEARCH_TICKET")){
                FChangeData beanImport1=new FChangeData();
                beanImport1.setNameTable(ITables.TABLE_TICKET_BUFFER);
                beanImport1.setPageIndex(bean.getPageIndex());
                beanImport1.setColumnName(bean.getColumnName());
                if(bean.getSqlWhere()==null){
                bean.setSqlWhere("");
                }
                beanImport1.setId_sql(1);
                beanImport1.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(evn_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME," +
                      "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from " +ITables.TABLE_TICKET_BUFFER +
                      " where STATUS in ('O','E1') and ACTIVE= -1 and START_TIME<= sysdate and END_TIME+ numtodsinterval("+IKey.getDelay()+", 'MINUTE')>=sysdate order by START_TIME desc");
//                beanImport1.setNameSQL("select TICKET_ID,EVN_TIME,START_TIME,END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME from " +ITables.TABLE_TICKET_BUFFER +
//                " where STATUS='C' and ACTIVE= -1 order by END_TIME desc");
                request.setAttribute("BRecordList1",new BReportBuffer().getAllRecord(beanImport1));
                request.setAttribute("BPaging1",new BReportBuffer().getPaging(beanImport1));

//                beanImport1.setNameSQL("select TICKET_ID,EVN_TIME,START_TIME,END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME from " +ITables.TABLE_TICKET_BUFFER +
//                " where ACTIVE= 0 order by END_TIME desc");
 beanImport1.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(evn_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME," +
 "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from " +ITables.TABLE_TICKET_BUFFER +
 " where STATUS in ('O','E1') and active=0 and START_TIME<= sysdate and END_TIME+ numtodsinterval("+IKey.getDelay()+", 'MINUTE')>=sysdate order by END_TIME desc");

                request.setAttribute("BRecordList2",new BReportBuffer().getAllRecord(beanImport1));
                request.setAttribute("BPaging2",new BReportBuffer().getPaging(beanImport1));
                    String sql="";
                String sqlN=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'dd/mm/yyyy')='"+bean.getSearch_time()+"'";
                String sqlT=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'mm/yyyy')='"+bean.getSearch_time().substring(bean.getSearch_time().indexOf("/")+1)+"'";
                String sqlNa=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'yyyy')='"+bean.getSearch_time().substring(bean.getSearch_time().length()-4)+"'";
                      if(bean.getSearch_type_time()==0){
                        sql=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'dd/mm/yyyy')='"+bean.getSearch_time()+"'";
                      }else if(bean.getSearch_type_time()==1){
                          sql=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'mm/yyyy')='"+bean.getSearch_time().substring(bean.getSearch_time().indexOf("/")+1)+"'";
                      }else{
                          sql=" where STATUS='"+ bean.getSearch_type()+"' and to_char("+IFields.TICKET_START_TIME+",'yyyy')='"+bean.getSearch_time().substring(bean.getSearch_time().length()-4)+"'";
                      }
                      
                beanImport1.setNameSQL("select * from " +ITables.TABLE_TICKET_BUFFER+sql);
                request.setAttribute("BColumnsList",new BReportBuffer().getAllColumnsInTable(beanImport1));
                request.setAttribute("BRecordList",new BReportBuffer().getAllRecord(beanImport1));
                request.setAttribute("BPaging",new BReportBuffer().getPaging(beanImport1));

                      beanImport1.setNameSQL("select * from " +ITables.TABLE_TICKET_BUFFER+sqlN);
                      request.setAttribute("BTotalDate",new BReportBuffer().getPaging(beanImport1));
                      beanImport1.setNameSQL("select * from " +ITables.TABLE_TICKET_BUFFER+sqlT);
                      request.setAttribute("BTotalMonth",new BReportBuffer().getPaging(beanImport1));
                      beanImport1.setNameSQL("select * from " +ITables.TABLE_TICKET_BUFFER+sqlNa);
                      request.setAttribute("BTotalYear",new BReportBuffer().getPaging(beanImport1));

                request.setAttribute("BTables",beanImport1);
                    target = anchor;
            }
        if(anchor.equals("_VIEW_TICKET")){
            FChangeData beanImport=new FChangeData();
            beanImport.setNameTable(ITables.TABLE_TICKET_BUFFER);
            beanImport.setPageIndex(bean.getPageIndex());
            beanImport.setColumnName(bean.getColumnName());
            if(bean.getSqlWhere()==null){
            bean.setSqlWhere("");
            }
                  beanImport.setId_sql(1);
            beanImport.setNameSQL("SELECT a.ticket_id, a.ticket_id_err, b.fullname, a.status,\n" + 
            "       a.active, a.evn_id, a.evn_time, a.buffer_id, a.buffer_time,\n" + 
            "       a.ebs_id, a.ebs_time, a.start_time, a.end_time, a.total_records,\n" + 
            "       a.code, a.table_name, a.where_data, a.error_description\n" + 
            "  FROM evn_ticket_buffer a,evn_source_connect_to_buffer b\n" + 
            "  where a.src_connect_id=b.src_connect_id order by a.ticket_id desc");
            request.setAttribute("BColumnsList",new BReportBuffer().getAllColumnsInTable(beanImport));
            request.setAttribute("BRecordList",new BReportBuffer().getAllRecord(beanImport));
            request.setAttribute("BPaging",new BReportBuffer().getPaging(beanImport));
            
            beanImport.setId_sql(1);
            beanImport.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(evn_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME," +
                  "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from " +ITables.TABLE_TICKET_BUFFER +
                  " where STATUS in ('O','E1') and ACTIVE= -1 and START_TIME<= sysdate and END_TIME+ numtodsinterval("+IKey.getDelay()+", 'MINUTE')>=sysdate order by START_TIME desc");
            
            request.setAttribute("BRecordList1",new BReportBuffer().getAllRecord(beanImport));
                  request.setAttribute("BPaging1",new BReportBuffer().getPaging(beanImport));
            beanImport.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(evn_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME," +
             "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from " +ITables.TABLE_TICKET_BUFFER +
             " where STATUS in ('O','E1') and active=0 and START_TIME<= sysdate and END_TIME+ numtodsinterval("+IKey.getDelay()+", 'MINUTE')>=sysdate order by END_TIME desc");

            request.setAttribute("BRecordList2",new BReportBuffer().getAllRecord(beanImport));
                  request.setAttribute("BPaging2",new BReportBuffer().getPaging(beanImport));
                   beanImport.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')");
                  request.setAttribute("BTotalDate",new BReportBuffer().getPaging(beanImport));
                  beanImport.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'mm/yyyy')=to_char(sysdate,'mm/yyyy')");
                  request.setAttribute("BTotalMonth",new BReportBuffer().getPaging(beanImport));
                  beanImport.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'yyyy')=to_char(sysdate,'yyyy')");
                  request.setAttribute("BTotalYear",new BReportBuffer().getPaging(beanImport));

            if(bean.getValue("thread1")==null){
                beanImport.setThread1(IKey.getThread1());
            }else{
                IKey.setThread1(bean.getThread1());
                beanImport.setThread1(IKey.getThread1());
            }
                  if(bean.getValue("amountT")!=null){
                      bean.setAmount(Integer.parseInt(bean.getValue("amountT")));
                      IKey.setAmountT(bean.getAmountT());
                  }
          if(bean.getThread2()==1&&IKey.getThread2()==0){
                for(int i=0;i<IKey.getAmountT();i++){
                  (new AThread2()).start();
                }
          } 

            if(bean.getValue("thread2")==null){

                beanImport.setThread2(IKey.getThread2());
            }else{
                IKey.setThread2(bean.getThread2());
                beanImport.setThread2(IKey.getThread2());
            } 
            if(bean.getValue("delay")!=null){
                IKey.setDelay(bean.getDelay());
            }

            if(!IKey.isThread1Runing()){
                (new AThread1()).start();
                (new AThread2AddQueue()).start();
                IKey.setThread1Runing(true);
            }
 

            request.setAttribute("BTables",beanImport);
                target = anchor;

              }
//        bean.setTable_name("EVN_TICKET_BUFFER");
        request.setAttribute("BTicket",bean);
        request.setAttribute("BSourceConnectBuffers",new BSourceConnectBuffer().getAllRecord());

        request.setAttribute("BSourceAccountBuffer",new BSourceAccountBuffer().getAllRecord());

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
            private String validate(FTicket bean,String anchor,ActionErrors errors){
                   if(anchor.equals(_VIEW)){
                   
                   }else if(anchor.equals(_EDIT)){
                       if((bean.getCode().trim().equals(_BLANK) || 
                           bean.getStart_time().trim().equals(_BLANK)||
                           bean.getEnd_time().trim().equals(_BLANK)||
                           bean.getTable_name().trim().equals(_BLANK))
                           ){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                       }else if( bean.getTicket_id()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }else if(anchor.equals(_CREATE)){
                       if((bean.getCode().trim().equals(_BLANK) || 
                           bean.getStart_time().trim().equals(_BLANK)||
                           bean.getEnd_time().trim().equals(_BLANK)||
                           bean.getTable_name().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                           }
                   }else if(anchor.equals(_DELETE)){
                       if(bean.getTicket_id()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }
                   return anchor;
                   }

            }