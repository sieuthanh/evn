package com.action.admin.reportSystem.sum;

import com.action.ACore;
import com.bo.admin.reportSystem.sum.BReportSystem;
import com.exp.EException;
import com.form.admin.reportSystem.sum.FReportSystem;
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
        if(bean.getValue("enableChart")==null) bean.setEnableTitle(0);
        if(anchor.equals(_VIEW)){ 
        String sqlTemp=bean.getSelectSql();
            String tempSQL="";
            if(bean.getTelco()!=null && !bean.getTelco().equals("")){
             tempSQL="and telco='"+bean.getTelco()+"'";
            }
            if(bean.getService_number()!=null && !bean.getService_number().equals("")){
                tempSQL+=" and service_number='"+bean.getService_number()+"'";
            }
            if(bean.getService_code()!=null && !bean.getService_code().equals("")){
                tempSQL+=" and service_code='"+bean.getService_code()+"'";
            }
            if(bean.getPhone_Number()!=null && !bean.getPhone_Number().equals("")){
                tempSQL+=" and service_code='"+bean.getPhone_Number()+"'";
            }
                bean.setSelectSql(bean.getSelectSql().replace("#",tempSQL));

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
            bean.setSelectSql(sqlTemp);
            if(bean.getValue("enableChart")==null) bean.setEnableTitle(0);
            request.setAttribute("reportSystemSum",bean);
            target = _PREPARED_EDIT;
        }else if(anchor.equals(_PREPARED_EDIT)){
            
            bean=bo.getById(bean);
            String sqlTemp=bean.getSelectSql();
            bean.setSelectSql(bean.getSelectSql().replace("#",""));
            if(bean.getTimeRefresh()<=1000){
            bean.setTimeRefresh(10*1000);
            }
            Date date = new Date();  
            Date todate = new Date(); 
            DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
//            if(bean.getSelectSql().contains("?")){
//                date.setDate(date.getDate());
//                bean.setFromDate(df.format(date));
//            }
            if(bean.getFromDate()!=null && !bean.getFromDate().equals("")){
            
                date.setDate(date.getDate()-bean.getDateStart());
                bean.setFromDate(df.format(date));
            }
            if(bean.getToDate()!=null && !bean.getToDate().equals("")){
                todate.setDate(todate.getDate());
                bean.setToDate(df.format(todate));
            }
           
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
            bean.setSelectSql(sqlTemp);
            request.setAttribute("reportSystemSum",bean);
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
        int id=(int)me.getId();
//        request.setAttribute("BReportSystem",new BReportSystem().getAllMenuUPermision(id,1));
        request.setAttribute("BServicesCode",new BReportSystem().getAllServicesCode());

      //  request.setAttribute("BReportSystem",bo.getAll());
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}