package com.action.evn.reportNormBuffer;

import com.action.ACore;
import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.reportNormBuffer.BReportNormBuffer;
import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.reportBuffer.FReportBuffer;
import com.form.evn.reportNormBuffer.FReportNormBuffer;
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

public class AReportNormBuffer extends ACore {
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws EException, IOException, ServletException, SQLException {

		final String LOCATION = this + "->executeAction()";
		String target = _LOGIN;
		FReportNormBuffer bean = (FReportNormBuffer) form;
		String anchor = bean.getValue(APP_ANCHOR, "");
		ActionErrors errors = new ActionErrors();
		target = validate(bean, anchor, errors);

		if (!errors.isEmpty()) {
			target = "_PREPARED";

		} else if (anchor.equals(_EDIT)) {

			BReportNormBuffer apps = new BReportNormBuffer();
			if (apps.update(bean)) {
				errors.add("alert", new ActionError("alert.update.successfull"));
			} else {
				errors.add("alert", new ActionError("alert.update.evn.error"));
			}
			target = "_PREPARED";

		} else if (anchor.equals(_CREATE)) {
			BReportNormBuffer apps = new BReportNormBuffer();
			if (apps.insert(bean)) {
				errors.add("alert", new ActionError("alert.insert.successfull"));
			} else {
				errors.add("alert", new ActionError("alert.update.evn.error"));
			}
			target = "_PREPARED";
		} else if (anchor.equals(_DELETE)) {
			BReportNormBuffer apps = new BReportNormBuffer();
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
			BReportNormBuffer apps = new BReportNormBuffer();
			String namet = bean.getNameTable();

			bean = apps.getRecordByID(bean);
			bean.setNameTable(namet);
			target = "_PREPARED";
		} else if (anchor.equals(_PREPARED_CREATE)) {
			bean.reset();

			target = "_PREPARED";

		}
		request.setAttribute("BReportsBuffers",
				new BReportBuffer().getAllRecord());
		request.setAttribute("BTables", bean);
		request.setAttribute("BReportsNormBuffer", bean);

		if (!errors.isEmpty())
			saveErrors(request, errors);

		return mapping.findForward(target);
	}

	private String validate(FReportNormBuffer bean, String anchor,
			ActionErrors errors) {
		if (anchor.equals(_VIEW)) {

		} else if (anchor.equals(_EDIT)) {
			if ((bean.getNorm_Name().trim().equals(_BLANK) || bean
					.getNorm_Code().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			} else if (bean.getReport_Id() <= 0) {
				errors.add("alert", new ActionError("errors.apps.edit.idnull"));
			}
		} else if (anchor.equals(_CREATE)) {
			if ((bean.getNorm_Name().trim().equals(_BLANK) || bean
					.getNorm_Code().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			}
		} else if (anchor.equals(_DELETE)) {
			if (bean.getReport_Id() <= 0) {
				errors.add("alert", new ActionError("errors.apps.edit.idnull"));
			}
		}
		return anchor;
	}

}