package com.action.admin.language;

import com.action.ACore;
import com.exp.EException;
import com.form.admin.language.FLanguage;
import com.lib.AppConfigs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ALanguage extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException 
    {
      final String LOCATION = this + "->executeAction()";
      FLanguage bean = (FLanguage)form;   
      me.setLanguage(bean.getLanguage());
      me.setLocation(bean.getLocation());
      Users.saveUser(me);
      return mapping.findForward(AppConfigs.APP_FORWARD_DEFAULT);
    }

}
