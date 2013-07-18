package com.action.admin.apps;

import com.action.ACore;
import com.bo.admin.apps.BApps;
import com.exp.EException;
import com.form.admin.apps.FApp;
import com.inf.IRoles;
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

public class AApps extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        if(!me.isRole(IRoles.RADMINISTRATOR))   return null;
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FApp bean = (FApp)form;
        bean.setBlock(bean.getValue("block")==null?1:0);
        String anchor=bean.getValue(APP_ANCHOR,"");       
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_VIEW)){
        
            BApps apps = new BApps();
            bean = apps.getRecordByID(bean);            
            target = anchor;
            
        }else if(anchor.equals(_EDIT)){
        
            BApps apps = new BApps();
            if(apps.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.update.groupcode.error"));   
            }
           target = anchor;
               
        }else if(anchor.equals(_CREATE)){
                BApps apps = new BApps();
                int saveID = bean.getId();
                bean.setId(0);
                if(apps.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.insert.groupcode.error"));   
                }
                bean.setId(saveID);
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BApps apps = new BApps();
                if(apps.delete(bean)){
                    bean = new FApp();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
                target = anchor;
        }else if(anchor.equals(_ACTIVE)){//Next
            BApps apps = new BApps();
            apps.swap(bean);
            target = _VIEW;
        }

        BApps apps = new BApps();
        request.setAttribute("BApps",apps.getAllRecord());
        request.setAttribute("BApp",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
            private String validate(FApp bean,String anchor,ActionErrors errors){
                   if(anchor.equals(_VIEW)){
                   
                   }else if(anchor.equals(_EDIT)){
                       if((bean.getName().trim().equals(_BLANK) || 
                           bean.getName().trim().equals(_BLANK) || 
                           bean.getLink().trim().equals(_BLANK))){
                           errors.add("alert",new ActionError("errors.apps.edit.short"));   
                       }else if( bean.getId()<=0){
                           errors.add("alert",new ActionError("errors.apps.edit.idnull"));   
                       }
                   }else if(anchor.equals(_CREATE)){
                       if((bean.getName().trim().equals(_BLANK) || 
                           bean.getName().trim().equals(_BLANK) || 
                           bean.getLink().trim().equals(_BLANK))){
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