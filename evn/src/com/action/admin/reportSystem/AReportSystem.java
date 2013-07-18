package com.action.admin.reportSystem;

import com.action.ACore;
import com.bo.admin.reportSystem.BReportSystem;

import com.exp.EException;
import com.form.admin.reportSystem.FReportSystem;

import com.inf.IKey;
import com.inf.IRoles;
import java.io.IOException;

import java.net.URLEncoder;


import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

    public class AReportSystem extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        ActionErrors errors = new ActionErrors();
        FReportSystem bean = (FReportSystem)form;
        BReportSystem bo=new BReportSystem();
        String anchor=bean.getValue(APP_ANCHOR,"");
        if(bean.getValue("total")==null) bean.setTotal(0);
        if(bean.getValue("enableTitle")==null) bean.setEnableTitle(0);
//        if(!me.isRole(IRoles.RADMINISTRATOR))   return null;
        if(anchor.equals(_VIEW)){ 
            try{
                if(bean.getStylePrint()==0){
                    request.setAttribute("BDatas",bo.getMutiData(bean));
                }else{
                    request.setAttribute("BDatas",bo.getOneData(bean));
                }   
            }catch (Exception ex){
                request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                ex.printStackTrace();
            }
            request.setAttribute("reportSystem",bean);
            target = anchor;
        }else if(anchor.equals(_PREPARED_EDIT)){
            bean=bo.getById(bean);
            Date date = new Date();  
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
            if(bean.getToDate()!=null && !bean.getToDate().equals("")){
                date.setDate(date.getDate()-1);
                date.setHours(23);
                date.setMinutes(59);
                date.setSeconds(59);
                bean.setToDate(df.format(date));
            }
            if(bean.getFromDate()!=null && !bean.getFromDate().equals("")){
                date.setDate(date.getDate()-6);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                bean.setFromDate(df.format(date));
            }
            request.setAttribute("reportSystem",bean);
            target = anchor;
        }else if(anchor.equals(_REPORT)){
            try  {
                String pathFile = "";
                if(bean.getStylePrint()==0){
                    pathFile=bo.exportExcellVertical(bo.getMutiData(bean),bean,bean.getNameFile());
                }else{
                    pathFile=bo.exportExcellHorizontal(bo.getOneData(bean),bean,bean.getNameFile());
                }
                bean.download(pathFile,URLEncoder.encode(bean.getNameOfFileVn()+".xls", "UTF-8"),null);
                bean.deleteFile(pathFile);
                target=null;
            } catch (Exception ex)  {
                request.setAttribute("reportSystem",bean);
                request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                target=_ERROR;
            }
            
        }else if(anchor.equals(_CREATE)){
            if(bo.addNew(bean)){
                errors.add("alert",new ActionError("alert.insert.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.insert.error"));
            }
            target="_LEFT";
        }else if(anchor.equals(_EDIT)){
            if(bo.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.update.error"));
            }
            target="_LEFT";
        }else if(anchor.equals(_DELETE)){
            if(bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.delete.error"));
            }
            target="_LEFT";
        }
//        request.setAttribute("BReportSystem",new BReportSystem().getAllMenuUPermision(bean.getId(),1));

      //  request.setAttribute("BReportSystem",bo.getAll());
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}