package com.action.admin.log;

import com.action.ACore;

import com.bo.admin.log.BLog;

import com.exp.EException;
import com.form.admin.log.FLog;

import com.inf.IRoles;

import com.lib.AppConfigs;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

    public class ALog extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _ERROR;
        FLog bean = (FLog)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        
       if(!me.isRole(IRoles.RADMINISTRATOR)) return null;

       if(anchor.equals(_VIEW)){ 
            if(AppConfigs.ADMIN_AUTO_LOG){
                BLog boL = new BLog();
                request.setAttribute("BUsernames",boL.getbeansLogUsername());
                request.setAttribute("BHosts",boL.getbeansLogHost());
                request.setAttribute("BLogs",boL.getAllLog(bean));
            }
            target = anchor;
        }

        return mapping.findForward(target);
    }
}
