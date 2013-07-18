package com.action.admin.groups;

import com.action.ACore;

import com.bo.admin.apps.BApps;
import com.bo.admin.groups.BGroups;
import com.bo.admin.reportSystem.BReportSystem;
import com.exp.EException;
import com.form.admin.groups.FGroup;
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

public class AGroups extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        if(!me.isRole(IRoles.RADMINISTRATOR))   return null;

        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FGroup bean = (FGroup)form;
        if(bean.getValue("roles")==null) bean.setroles(null);
        if(bean.getValue("apps")==null) bean.setApps(null);
        if(bean.getValue("privileges")==null) bean.setPrivileges(null);
        if(bean.getValue("menus")==null) bean.setMenus(null);
        String anchor=bean.getValue(APP_ANCHOR,"");
        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_EDIT)){
                BGroups groups = new BGroups();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("alert.update.circle.error"));   
                }else if(groups.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.groupcode.error"));   
                }
                
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BGroups groups = new BGroups();
                int saveID = bean.getId();
                bean.setId(0);
                if(groups.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.insert.groupcode.error"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BGroups groups = new BGroups();
                if(groups.delete(bean)){
                    bean = new FGroup();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.havechild.error"));   
                }
            target = anchor;
        }

        BGroups groups = new BGroups();
        request.setAttribute("BGroups",groups.getAllRecord(0));
        request.setAttribute("BGroup",bean);
        BApps apps = new BApps();
        request.setAttribute("BApps",apps.getAllRecord());     
//        request.setAttribute("BReportSystem",new BReportSystem().getAllMenuGPermision(bean.getId(),1));
       // request.setAttribute("BMenus",new BMenu().getAllMenuGPermision(bean.getId(),1));

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FGroup bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) ||
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.groups.edit.short"));   
            }else if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.groups.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) ||
                bean.getCode().trim().equals(_BLANK))){
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
