package com.action.admin.departments;

import com.action.ACore;
import com.bo.admin.departments.BDepartments;
import com.exp.EException;
import com.form.admin.departments.FDepartment;
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

public class ADepartments extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        if(!me.isRole(IRoles.RADMINISTRATOR))   return null;

        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDepartment bean = (FDepartment)form;
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_EDIT)){
                BDepartments departments = new BDepartments();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.departments.edit.circle"));   
                }else if(departments.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.departments.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDepartments departments = new BDepartments();
                int saveID = bean.getId();
                bean.setId(0);
                if(departments.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.departments.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDepartments departments = new BDepartments();
                if(departments.delete(bean)){
                    bean = new FDepartment();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.departments.delete.havechild"));   
                }
            target = anchor;
        }

        BDepartments departments = new BDepartments();
        request.setAttribute("BDepartments",departments.getAllRecord((int)me.getDepartmentID()));
        request.setAttribute("BDepartment",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDepartment bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.departments.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.departments.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.departments.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.departments.edit.idnull"));   
            }
        }
    return anchor;
    }
}
