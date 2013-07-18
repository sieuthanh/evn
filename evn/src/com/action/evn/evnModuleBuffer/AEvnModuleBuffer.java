package com.action.evn.evnModuleBuffer;

import com.action.ACore;
import com.bo.evn.evnErrorBuffer.BEvnErrorBuffer;
import com.bo.evn.evnModuleBuffer.BEvnModuleBuffer;
import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;

import com.bo.evn.thread2.BThread2;

import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.evnErrorBuffer.FEvnErrorBuffer;
import com.form.evn.evnModuleBuffer.FEvnModuleBuffer;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

import com.inf.IKey;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AEvnModuleBuffer extends ACore {
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws EException, IOException, ServletException, SQLException {

		final String LOCATION = this + "->executeAction()";
		String target = _LOGIN;
		FEvnModuleBuffer bean = (FEvnModuleBuffer) form;
		String anchor = bean.getValue(APP_ANCHOR, "");
		ActionErrors errors = new ActionErrors();
		target = validate(bean, anchor, errors);

		if (!errors.isEmpty()) {

			target = "_PREPARED";
		} else if (anchor.equals(_EDIT)) {

			BEvnModuleBuffer apps = new BEvnModuleBuffer();
			if (apps.update(bean)) {
				errors.add("alert", new ActionError("alert.update.successfull"));
			} else {
				errors.add("alert", new ActionError("alert.update.evn.error"));
			}
			target = "_PREPARED";

		} else if (anchor.equals(_CREATE)) {

			BEvnModuleBuffer apps = new BEvnModuleBuffer();
			if (apps.insert(bean)) {
				errors.add("alert", new ActionError("alert.insert.successfull"));
			} else {
				errors.add("alert", new ActionError("alert.update.evn.error"));
			}
			target = "_PREPARED";
		} else if (anchor.equals(_DELETE)) {
			BEvnModuleBuffer apps = new BEvnModuleBuffer();
			if (apps.delete(bean)) {
				errors.add("alert", new ActionError("alert.delete.successfull"));
			} else {
				errors.add("alert", new ActionError("alert.delete.error"));
			}
			FChangeData beant = new FChangeData();
			beant.setNameTable(bean.getNameTable());
			beant.setNameSQL("");
			request.setAttribute("BColumnsList",
					new BReportBuffer().getAllColumnsInTable(beant));
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beant));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beant));

			target = _SHOW;
		} else if (anchor.equals(_PREPARED_EDIT)) {
			BEvnModuleBuffer apps = new BEvnModuleBuffer();
			String namet = bean.getNameTable();

			bean = apps.getRecordByID(bean);
			bean.setNameTable(namet);
			target = "_PREPARED";
		} else if (anchor.equals(_PREPARED_CREATE)) {
			bean.reset();
			target = "_PREPARED";

		}

		request.setAttribute("BTables", bean);
		request.setAttribute("BEvnModuleBuffer", bean);

		if (!errors.isEmpty())
			saveErrors(request, errors);

		return mapping.findForward(target);
	}

	private String validate(FEvnModuleBuffer bean, String anchor,
			ActionErrors errors) {
		if (anchor.equals(_VIEW)) {

		} else if (anchor.equals(_EDIT)) {
			if ((bean.getModuleCode().trim().equals(_BLANK) || bean
					.getModuleDescription().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			}
		} else if (anchor.equals(_CREATE)) {
			if ((bean.getModuleCode().trim().equals(_BLANK) || bean
					.getModuleDescription().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			}
		} else if (anchor.equals(_DELETE)) {
			if (bean.getModuleId() <= 0) {
				errors.add("alert", new ActionError("errors.apps.edit.idnull"));
			}
		}
		return anchor;
	}

}