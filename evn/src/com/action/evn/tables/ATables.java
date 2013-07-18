package com.action.evn.tables;

import com.action.ACore;
import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.reportBuffer.FReportBuffer;
import com.form.evn.tables.FTables;

import com.inf.IFields;
import com.inf.ITables;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ATables extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
       
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FTables bean = (FTables)form;
        String anchor=bean.getValue(APP_ANCHOR,"");       
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
            target="_PREPARED";
        
        }else if(anchor.equals(_EDIT)){
        
            BTables apps = new BTables();
            if(apps.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.update.evn.error"));   
            }
           target = "_PREPARED";
               
        }else if(anchor.equals(_CREATE)){
                BTables apps = new BTables();
                if(apps.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.evn.error"));   
                }
            target = "_PREPARED";
        }else if(anchor.equals(_DELETE)){
                BTables apps = new BTables();
                if(apps.delete(bean)){
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
            FChangeData beant=new FChangeData();
            beant.setNameTable(bean.getNameTable());
            beant.setNameSQL("");
            request.setAttribute("BColumnsList",new BReportBuffer().getAllColumnsInTable(beant));
            request.setAttribute("BRecordList",new BReportBuffer().getAllRecord(beant));
            request.setAttribute("BPaging",new BReportBuffer().getPaging(beant));

            target = _SHOW;
        }else if(anchor.equals(_PREPARED_EDIT)){
            BTables apps = new BTables();
            String namet=bean.getNameTable();

            bean = apps.getRecordByID(bean);     
            bean.setNameTable(namet);
            target="_PREPARED";
        }else if(anchor.equals(_PREPARED_CREATE)){
                bean.reset();

                target="_PREPARED";

        }
        FChangeData beant=new FChangeData();
        beant.setId_sql(1);
        beant.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')");
        request.setAttribute("BTotalDate",new BReportBuffer().getPaging(beant));
        beant.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'mm/yyyy')=to_char(sysdate,'mm/yyyy')");
        request.setAttribute("BTotalMonth",new BReportBuffer().getPaging(beant));
        beant.setNameSQL("select TICKET_ID from " +ITables.TABLE_TICKET_BUFFER+" where to_char("+IFields.TICKET_START_TIME+",'yyyy')=to_char(sysdate,'yyyy')");
        request.setAttribute("BTotalYear",new BReportBuffer().getPaging(beant));
        request.setAttribute("BSourceConnectBuffers",new BSourceConnectBuffer().getAllRecord());
        request.setAttribute("BTables",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
            private String validate(FTables bean,String anchor,ActionErrors errors){
                   if(anchor.equals(_VIEW)){
                   
                   }else if(anchor.equals(_EDIT)){
                       if((bean.getName().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                       }else if( bean.getId()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }else if(anchor.equals(_CREATE)){
                       if((bean.getName().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                           }
                   }else if(anchor.equals(_DELETE)){
                       if(bean.getId()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }
               return anchor;
               }

            }