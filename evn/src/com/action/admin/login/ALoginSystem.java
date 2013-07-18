package com.action.admin.login;

import com.action.ACore;

import com.bo.admin.apps.BApps;
import com.bo.admin.users.BUsers;
import com.exp.EException;
import com.bo.admin.log.BLog;
import com.form.admin.apps.FApp;
import com.form.admin.log.FLog;
import com.form.admin.users.FUser;
import com.form.admin.login.FLoginSystem;
import com.inf.IRoles;
import com.inf.admin.IConstantsAdmin;
import com.lib.AppConfigs;
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

public class ALoginSystem extends  ACore {
        public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
        {
            final String LOCATION = this + "->executeAction()";
            String target = _LOGIN;
            FLoginSystem bean = (FLoginSystem)form;
            
            String anchor=bean.getValue(APP_ANCHOR,"");
            ActionErrors errors = new ActionErrors();
            FLog beanLog = new FLog(bean.getUsername(),request);
            if(anchor.equals(_LOGOUT)){   
                beanLog.setResult(IConstantsAdmin.LOG_RESULT[2]);
                me.logout(); 
                request.getSession().invalidate();
//                try{
//                Folder folder=(Folder)request.getSession().getAttribute("folderStore");
//                folder.getStore().close();
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
                target = _LOGIN;
            }else if(anchor.equals(_LOGIN)){
                beanLog.setResult(IConstantsAdmin.LOG_RESULT[0]);
                if(_BLANK.equals(anchor) || _BLANK.equals(bean.getPassword().trim()) || _BLANK.equals(bean.getUsername().trim())){
                    errors.add("loginError",new ActionError("errors.login.auth"));   
                }else{
                    BUsers bo = new BUsers();
                    if(bo.checkLogin(bean))
                    {
                       beanLog.setResult(IConstantsAdmin.LOG_RESULT[1]);
                       if(AppConfigs.ADMIN_AUTO_LOG){
                           BLog boDelete = new BLog();
                           boDelete.deleteOldLog();
                       }
                       if(bean.getActive()==0){
                           errors.add("loginError",new ActionError("errors.login.disable"));   
                       }else if(bean.getDaysLive()<0){
                           errors.add("loginError",new ActionError("errors.login.overday"));   
                       }else{
                           FApp app = new FApp();      
                           int appID =bean.getValue("appID")!=null?bean.getAppID():0;
                           String apps = bean.me.getAPP_ID();
                           if(appID<=0){
                           //Get ID for the first Application of User
                               int s = apps.indexOf(IConstantsAdmin.APP_SEPARATE_);
                               int e = apps.indexOf(IConstantsAdmin.APP_SEPARATE_,s+1);
                               if(e>s){
                                   bean.me.setAPP_ID(apps.substring(s+1,e));
                                   appID = Integer.parseInt(bean.me.getAPP_ID());
                               }
                           }
                           app.setId(appID);
                           bean.setAppID(appID);
                           if(apps.indexOf(IConstantsAdmin.APP_SEPARATE_ + appID + IConstantsAdmin.APP_SEPARATE_)<0){
                               errors.add("loginError",new ActionError("errors.login.deny"));   
                           }else{
                               app = (new BApps()).getRecordByID(app);
                               if(load(app.getClassName(),new Object[]{bean,errors}) && errors.isEmpty()){
                                   request.getSession().removeAttribute("ADMIN.LOGIN.RETRY");
                                   BUsers users = new BUsers();
                                   FUser user = new FUser();
                                   user.setId(bean.getId());
                                   user = users.getRecordByID(user);
                                   if(users.updateLastVisit(user)){
                                       if (me.isRole(com.inf.IRoles.NONE)){
                                           me.setRole(IRoles.rGUEST);
                                       }
                                       Users.saveUser(bean.me);
                                   }
                                   request.setAttribute("BApp",app);
                                   target = _SUCCESS;
                               }
                           }
                       }
                    } else{
                       if(AppConfigs.ADMIN_LOGIN_RETRY>0){ 
                           Integer retry = (Integer)request.getSession().getAttribute("ADMIN.LOGIN.RETRY");
                           if(retry==null){
                               retry = AppConfigs.ADMIN_LOGIN_RETRY;
                           }
                           retry--;                           
                           if(retry>0){
                               request.getSession().setAttribute("ADMIN.LOGIN.RETRY",retry);
                           }else{
                               BUsers users = new BUsers();
                               users.lock(bean.getUsername());
                           }
                       }
                       errors.add("loginError",new ActionError("errors.login.auth"));   
                    }
                }
            }else if(anchor.equals(_PREPARED_EDIT)){ 
                me.getPassword();
                FUser beantemp = new FUser();
                beantemp.setUsername(me.getUsername());               
                BUsers boCP = new BUsers();
                if(boCP.checkChangePas(beantemp)) {
                    request.setAttribute("login",beantemp);
                    target = _PREPARED_EDIT;
                }else{
                    errors.add("alert",new ActionError("users.edit.Cannotchangepass"));
                    target = _ERROR;
                }
            }else if(anchor.equals(_EDIT)){
                BUsers boU = new BUsers(); 
                beanLog.setResult(IConstantsAdmin.LOG_RESULT[3]);
                if (boU.updateNewpass(bean)){
                    beanLog.setResult(IConstantsAdmin.LOG_RESULT[4]);
                    errors.add("alert",new ActionError("errors.changepass.success"));   
                }else{
                    errors.add("alert",new ActionError("errors.changepass.false"));   
                }
                target =_PREPARED_EDIT;
            }
            if (AppConfigs.ADMIN_AUTO_LOG && beanLog.getResult()>=0 && beanLog.getUsername()!=null){
                BLog boLog = new BLog();
                boLog.addnew(beanLog);
            }
            
            
            
            if(!errors.isEmpty()) saveErrors(request,errors);
            if ( bean.me.getId()==0)  return mapping.findForward(_LOGIN);
            return mapping.findForward(target);
        }
}