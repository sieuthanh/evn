package com.action.evn.sourceConnectBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.action.ACore;
import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

public class ASourceConnectBuffer extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
       
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FSourceConnectBuffer bean = (FSourceConnectBuffer)form;
        if(bean.getType()==null){
        	bean.setType("");
        }
        String anchor=bean.getValue(APP_ANCHOR,"");       
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
        
            target="_PREPARED";
        }else if(anchor.equals(_EDIT)){
        
            BSourceConnectBuffer apps = new BSourceConnectBuffer();
            if(apps.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.update.evn.error"));   
            }
           target = "_PREPARED";
               
        }else if(anchor.equals("_CHECKCON")){
            if(bean.getType().equals("D")){
                Connection conn = null;
                try {
                   Class.forName(bean.getConnection());
                   conn = DriverManager.getConnection(bean.getUrl());
                   errors.add("alert",new ActionError("alert.conn.success"));   
                   conn.close();
                } catch (ClassNotFoundException e) {
                    errors.add("alert",new ActionError("alert.conn.error"));   
                    request.setAttribute("errorValue",e.getMessage());
                }catch (Exception ex){
                    errors.add("alert",new ActionError("alert.conn.error"));   
                    request.setAttribute("errorValue",ex.getMessage());
                }
            }else{
                String page="";
                try {
                        URL website = new URL(bean.getUrl());
                        URLConnection ws = website.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(ws.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null){ 
                            page+=inputLine+"\n";
                        }
                        in.close();
                    errors.add("alert",new ActionError("alert.conn.success"));   
                } catch (Exception e) {
                    errors.add("alert",new ActionError("alert.conn.error"));   
                    request.setAttribute("errorValue",e.getMessage());
                }
            }
            
            target = "_PREPARED";
        }else if(anchor.equals(_CREATE)){
                BSourceConnectBuffer apps = new BSourceConnectBuffer();
                if(apps.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.evn.error"));   
                }
            target = "_PREPARED";
        }else if(anchor.equals(_DELETE)){
                BSourceConnectBuffer apps = new BSourceConnectBuffer();
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
            BSourceConnectBuffer apps = new BSourceConnectBuffer();
            String namet=bean.getNameTable();

            bean = apps.getRecordByID(bean);     
            bean.setNameTable(namet);
            target="_PREPARED";
        }else if(anchor.equals(_PREPARED_CREATE)){
                bean.reset();

                target="_PREPARED";

        }
        request.setAttribute("BTables",bean);
        request.setAttribute("BSourceConnectBuffer",bean);
        BSourceConnectBuffer bo = new BSourceConnectBuffer();
        request.setAttribute("BSourceConnectBuffers",bo.getAllRecord());

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
            private String validate(FSourceConnectBuffer bean,String anchor,ActionErrors errors){
                   if(anchor.equals(_VIEW)){
                   
                   }else if(anchor.equals(_EDIT)){
                       if((bean.getShortName().trim().equals(_BLANK) || 
                           bean.getFullName().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                       }else if( bean.getSrc_Connect_Id()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }else if(anchor.equals(_CREATE)){
                       if((bean.getFullName().trim().equals(_BLANK) || 
                           bean.getSrc_Code().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                           }
                   }else if(anchor.equals(_DELETE)){
                       if(bean.getSrc_Connect_Id()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }
               return anchor;
               }

            }