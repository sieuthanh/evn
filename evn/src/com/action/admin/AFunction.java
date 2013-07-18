package com.action.admin;

import com.action.ACore;
import com.bo.admin.apps.BApps;
import com.bo.admin.departments.BDepartments;
import com.bo.admin.groups.BGroups;
import com.bo.admin.log.BLog;
import com.bo.admin.reportSystem.BReportSystem;
import com.bo.admin.users.BUsers;
import com.exp.EException;
import com.form.FBeans;
import com.form.admin.FFunction;
import com.form.admin.apps.FApp;
import com.form.admin.departments.FDepartment;
import com.form.admin.groups.FGroup;
import com.form.admin.reportSystem.FReportSystem;
import com.form.admin.users.FUser;
import com.inf.IRoles;
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

    public class AFunction extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()"; 
        String target = _LOGIN;
        ActionErrors errors = new ActionErrors();
        FFunction bean = (FFunction)form; 
        String anchor=bean.getValue(APP_ANCHOR,"");
        if(!me.isRole(IRoles.RADMINISTRATOR))   return null;
        if(anchor.equals(_LISTGROUPS)){ 
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(0));
            request.setAttribute("BGroup",new FGroup());
            BApps app = new BApps();
            request.setAttribute("BApps",app.getActiveRecord());
            //request.setAttribute("BMenus",new BMenu().getAllMenuGPermision(0,1));
//            request.setAttribute("BReportSystem",new BReportSystem().getAllMenuGPermision(0,1));
            target = anchor;
        }else if(anchor.equals("_SYSTEM_REPORT")){ 
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(0));

            request.setAttribute("BReportSystem",new BReportSystem().getAll());
            request.setAttribute("reportSystem",new FReportSystem());
            target = anchor;
        }else if(anchor.equals("_SYSTEM_REPORT_SUM")){ 
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(0));

            request.setAttribute("BReportSystem",new com.bo.admin.reportSystem.sum.BReportSystem().getAll());
            request.setAttribute("reportSystemSum",new com.form.admin.reportSystem.sum.FReportSystem());
            request.setAttribute("BServicesCode",new com.bo.admin.reportSystem.sum.BReportSystem().getAllServicesCode());
            target = anchor;
        }else if(anchor.equals(_LISTAPPS)){ 
            BApps app = new BApps();
            request.setAttribute("BApp",new FApp());
            request.setAttribute("BApps",app.getAllRecord());
            target = anchor;
        }else if(anchor.equals(_VIEWGROUPS)){
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(0));

            FGroup group = new FGroup();
            if(bean.getId()>0){
                group.setId(bean.getId());
                group = groups.getRecordByID(group);
            }
            request.setAttribute("BGroup",group);
            BApps app = new BApps();
            request.setAttribute("BApps",app.getActiveRecord());
            
//            request.setAttribute("BReportSystem",new BReportSystem().getAllMenuGPermision(bean.getId(),1));
            //request.setAttribute("BMenus",new BMenu().getAllMenuGPermision(bean.getId(),1));
            target = anchor;

        }else if(anchor.equals(_LISTUSERS)){
            FUser user = new FUser();
            user.setId((int)me.getId());            
            BUsers users = new BUsers();
            user = users.getRecordByID(user);
            bean.setId(user.getGroupID());
            bean.setPageIndex(user.getPageIndex());
            request.setAttribute("BUsers",users.getUserByGroupID_DepID(user,user.getGroupID(),(int)me.getDepartmentID(), user.getPageIndex()));
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(0));
            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
            request.setAttribute("BDepartment",new FUser());
            target = anchor;
        }else if(anchor.equals(_EDITUSERS)){
            FUser user = new FUser();            
            user.setId(bean.getId());
            BUsers users = new BUsers();
            if(user.getId()>0){
                user = users.getRecordByID(user);
            }
            request.setAttribute("BUser",user);

            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(user.getId()));
            //request.setAttribute("BGroups",groups.getMultiRecordsGroupTree(0,user.getId()));
            

            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));

            BApps app = new BApps();
            request.setAttribute("BApps",app.getActiveRecord());
           // request.setAttribute("BMenus",new BMenu().getAllMenuUPermision(bean.getId(),1));
//            request.setAttribute("BReportSystem",new BReportSystem().getAllMenuUPermision(bean.getId(),1));
            target = anchor;
        }else if(anchor.equals(_EDITGROUPS)){
            BGroups groups = new BGroups();
            request.setAttribute("BGroups",groups.getAllRecord(1));
            FGroup group = new FGroup();
            group.setId(bean.getId());
            group = groups.getRecordByID(group);
            request.setAttribute("BGroup",group);
            BApps app = new BApps();
            request.setAttribute("BApps",app.getActiveRecord());
        
            target = anchor;
        }else if(anchor.equals(_VIEWUSERS)){
            if(bean.getId()>0){
                FUser user = new FUser();
                user.setGroupID(bean.getId());
                user.setPageIndex(bean.getPageIndex());
                BUsers users = new BUsers();
                request.setAttribute("BUsers",users.getUserByGroupID_DepID(user,user.getGroupID(),(int)me.getDepartmentID(),user.getPageIndex()));
            }
            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
            request.setAttribute("BDepartment",new FUser());
            target = anchor;
        }else if(anchor.equals(_LISTDEPARTMENTS)){ 
            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
            request.setAttribute("BDepartment",new FDepartment());
            target = anchor;
        }else if(anchor.equals(_VIEWDEPARTMENTS)){
            if(bean.getId()>0){
                FDepartment department = new FDepartment();
                department.setId(bean.getId());
                BDepartments departments = new BDepartments();
                request.setAttribute("BDepartment",departments.getRecordByID(department));
                request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
            }
            int aaa=AppConfigs.APP_ROWS_VIEW;
            target = anchor;
        }else if(anchor.equals(_LISTLOGS)){ 
            if(AppConfigs.ADMIN_AUTO_LOG){
                BLog boL = new BLog();
                request.setAttribute("BUsernames",boL.getbeansLogUsername());
                request.setAttribute("BHosts",boL.getbeansLogHost());
                request.setAttribute("BLogs",boL.getAllLog(null));
            }
            //Lenh xoa bo nho
            request.getSession().removeAttribute("log");
            target = anchor;
        }else if(anchor.equals(_ACTIVE)){ //on/off Role
            if(bean.getId()==1){
                request.getSession().setAttribute("ADMIN.PANEL.ROLE","TRUE");
                errors.add("alert",new ActionError("option.panel.role.show"));
            }
            if(bean.getId()==2){
                request.getSession().setAttribute("ADMIN.PANEL.PRIVILEGE","TRUE");
                errors.add("alert",new ActionError("option.panel.privilege.show"));
            }
            if(bean.getId()==3){
                request.getSession().setAttribute("ADMIN.PANEL.DEPARTMENT","TRUE");
                errors.add("alert",new ActionError("option.panel.log.show"));
            }
            target = anchor;


        } else if (anchor.equals(_ADD_CONTACT)){
            //request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));       
             target = _ADD_CONTACT ;
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);

        return mapping.findForward(target);
    }
}
