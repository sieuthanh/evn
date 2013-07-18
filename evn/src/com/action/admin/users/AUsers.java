package com.action.admin.users;

import com.action.ACore;

import com.bo.admin.apps.BApps;
import com.bo.admin.departments.BDepartments;
import com.bo.admin.groups.BGroups;
import com.bo.admin.reportSystem.BReportSystem;
import com.bo.admin.users.BUsers;
import com.exp.EException;


import com.form.admin.apps.FApp;
import com.form.admin.groups.FGroup;
import com.form.admin.users.FUser;
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

public class AUsers extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {

        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FUser bean = (FUser)form;
        if(bean.getValue("roles")==null) bean.setroles(null);
        if(bean.getValue("apps")==null) bean.setApps(null);
        if(bean.getValue("privileges")==null) bean.setPrivileges(null);
        if(bean.getValue("menus")==null) bean.setMenus(null);
        
        
        String anchor=bean.getValue(APP_ANCHOR,"");
        if(!me.isRole(IRoles.RADMINISTRATOR))  return null;
        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
         
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_ACTIVE)){
//            BUsers users = new BUsers();
//            int pageIndex = bean.getPageIndex();
//            int depId=bean.getDepartmentID();
             if(bean.getId()>0 && new BUsers().toggleActive(bean)){
                 bean = new BUsers().getRecordByID(bean);
             }
               request.setAttribute("beanusers",bean);
//            bean.setPageIndex(pageIndex);
//            if(bean.getId()>0 && users.toggleActive(bean)){
//                bean.setActive(bean.getActive()==0?1:0);
//                request.setAttribute("BUsers",users.getUserByGroupID_DepID(bean,bean.getGroupID(),depId,bean.getPageIndex()));
//            }
//            BDepartments departments = new BDepartments();
//            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
//            bean.setDepartmentID(depId);
//            request.setAttribute("BDepartment",bean);
            target = anchor;
        }else if(anchor.equals(_SEARCH)){
            request.setAttribute("BUsers",new BUsers().getUserByGroupID_DepID(bean,0,bean.getDepartmentID(),0));
            request.setAttribute("BDepartment",bean);
            target = anchor;
        }else if(anchor.equals(_EDIT)){
                if(bean.getValue("sms")==null) bean.setSms(0);
                BUsers users = new BUsers();
                FUser user = users.getRecordByID(bean);
                user.setFullName(bean.getFullName());
                if(!bean.getPassword().equals(_BLANK)){
                    user.setPassword(bean.getPassword());
                    user.setDatePassword(bean.dateToString(bean.getCurrentDate()));
                }
                user.setEmail(bean.getEmail());
                user.setPhone(bean.getPhone());
                user.setAddress(bean.getAddress());
                user.setSex(bean.getSex());
                user.setDescription(bean.getDescription());
                user.setGroupID(bean.getGroupID());
                user.setGroupsID(bean.getGroupsID());
                if(me.isRole(IRoles.RADMINISTRATOR)){
                    user.setUsername(bean.getUsername());
                    user.setPicture(bean.getPicture());

                    user.setDepartmentID(bean.getDepartmentID());
                    user.setRole(bean.getRole());
                    user.setPrivilege(bean.getPrivilege());
                    user.setActive(bean.getActive());
                    user.setPeriod(bean.getPeriod());
                    user.setGroupID(bean.getGroupID());    
                    user.setMenus(bean.getMenus());    
                    user.setApp(bean.getApp());
                    user.setChangePassword(bean.getChangePassword());
                    user.setSms(bean.getSms());
                    user.setArea(bean.getArea());
                }
                //if (user.getGroupsID()!=null){
                    if(users.update(user)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("alert.update.username.error"));   
                    }
//                }else{
//                        errors.add("alert",new ActionError("alert.select.group.error"));   
//                }
                     
            target = anchor;
        }else if(anchor.equals(_CREATE)){
               BUsers users = new BUsers();
                int saveID = bean.getId();
                bean.setId(0);
                if(bean.getValue("sms")==null) bean.setSms(0);
                int messager=users.insert(bean);
                    if(messager==0){
                        errors.add("alert",new ActionError("alert.insert.successfull"));   
                    }else if(messager==1){
                        errors.add("alert",new ActionError("alert.insert.username.error"));   
                    }else if(messager==2){
                        errors.add("alert",new ActionError("alert.insert.username.error.overMax"));   
                    }
                    bean.setId(saveID);                          
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BUsers users = new BUsers();
                if(users.delete(bean)){
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
            request.setAttribute("BUsers",users.getUserByGroupID_DepID(bean,bean.getGroupID(),bean.getDepartmentID(),bean.getPageIndex()));
            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
            request.setAttribute("BDepartment",bean);
            target = anchor;
        }else if(anchor.equals(_VIEW)){
            FGroup group = new FGroup();
            group.setId(bean.getGroupID());
            BGroups groups = new BGroups();
            group = groups.getRecordByID(group);
            request.setAttribute("BGroup",group);
            BApps app = new BApps();
            request.setAttribute("BApps",app.getAllRecord());
            request.setAttribute("BApp",new FApp());
            target = anchor;
        }else if(anchor.equals("_MENU_OPTION")){
//            request.setAttribute("BReportSystem",new BReportSystem().getAllMenuGPermision(bean.getGroupID(),1));
//            request.setAttribute("BMenus",new BMenu().getAllMenuGPermision(bean.getGroupID(),1));
            target = anchor;
        }

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FUser bean,String anchor,ActionErrors errors){
        if(anchor.equals(_ACTIVE)){
        
        }else if(anchor.equals(_EDIT)){
            if(bean.getFullName().trim().equals(_BLANK) || (me.isRole(IRoles.RADMINISTRATOR) && bean.getUsername().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.users.edit.short"));   
            }else if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.users.edit.idnull"));  
            }else if(me.getId()!=bean.getId() && !me.isRole(IRoles.RADMINISTRATOR)){
                errors.add("alert",new ActionError("errors.deny.privilege"));  
            }
        }else if(anchor.equals(_CREATE)){
            if(bean.getFullName().trim().equals(_BLANK) || bean.getUsername().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.users.edit.short"));   
            }else if(bean.getPassword().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.users.edit.password.isnull"));  
            }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.users.edit.idnull"));   
            }
        }
    return anchor;
    }

}
