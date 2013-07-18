package com.startup;

import com.bo.evn.reportBuffer.BReportBuffer;

import com.bo.evn.tables.BTables;

import com.exp.EException;

import com.form.admin.FFunction;
import com.form.admin.login.FLoginSystem;
import com.form.admin.reportSystem.FReportSystem;
import com.form.evn.FChangeData;

import com.inf.ITables;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public class SManagerTables{
    public boolean load(ActionForm form,ActionErrors errors) throws EException {
        boolean result = false;
         FLoginSystem bean = (FLoginSystem)form;
        HttpServletRequest request = bean.getRequest();
        try  {
             FChangeData beanImport=new FChangeData();
            FFunction beant =new FFunction();
             beanImport.setNameTable(ITables.TABLE_REPORTS_BUFFER);
            beanImport.setColumnName("");
            beanImport.setNameSQL("");
             request.setAttribute("BColumnsList",new BReportBuffer().getAllColumnsInTable(beanImport));
            request.setAttribute("BRecordList",new BReportBuffer().getAllRecord(beanImport));
            request.setAttribute("BPaging",new BReportBuffer().getPaging(beanImport));
            beant.setNameTable(beanImport.getNameTable());
            request.setAttribute("BTables",beant);
            FFunction beanF =new FFunction();
            request.setAttribute("BFunction",beanF);
            request.setAttribute("BTabless",new BTables().getAllRecord());
            result = true;
        } catch (Exception ex) {
        }
        
        return result;
    }
}
