package com.action.evn.alertBuffer;

import com.action.ACore;
import com.action.evn.AThread1;
import com.action.evn.AThread2;
import com.action.evn.AThread2AddQueue;
import com.action.evn.AThread3;

import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.ticket.BTicket;

import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.ticket.FTicket;

import com.inf.IFields;
import com.inf.IKey;
import com.inf.ITables;
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

public class AAlertBuffer extends ACore {
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws EException, IOException, ServletException, SQLException {

		final String LOCATION = this + "->executeAction()";
		String target = _LOGIN;
		FAlertBuffer bean = (FAlertBuffer) form;
		String anchor = bean.getValue(APP_ANCHOR, "");
		ActionErrors errors = new ActionErrors();
		target = validate(bean, anchor, errors);

		if (anchor.equals("_VIEW")) {
			bean.setSearch_time(bean.dateToString(bean.getCurrentSqlDate(),
					"dd/MM/yyyy"));

			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(ITables.TABLE_ALERT_BUFFER);
			beanImport.setNameSQL("");
			beanImport.setId_sql(1);

			beanImport
					.setNameSQL("select * from evn_alert_buffer order by detect_time desc");
			beanImport.setPageIndex(bean.getPageIndex());
			request.setAttribute("BColumnsList",
					new BReportBuffer().getAllColumnsInTable(beanImport));
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beanImport));
			beanImport.setId_sql(1);

			beanImport
					.setNameSQL("select * from (select ticket_id, src_record_id, error_description, detect_time, table_name from evn_alert_buffer where sent_time is null and STATUS in ('E1','E2') order by detect_time desc) where rownum <=10");
			request.setAttribute("BRecordList1",
					new BReportBuffer().getAllRecord(beanImport));
			beanImport
					.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, table_name from evn_alert_buffer where sent_time is null and STATUS in ('E1','E2') order by detect_time desc");
			request.setAttribute("BPaging1",
					new BReportBuffer().getPaging(beanImport));
			beanImport
					.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and trim(status) in ('E1','E2') and trim(COMPLETE_STATUS) ='F' and detect_time <= sysdate and detect_time >= sysdate-"
							+ IKey.getValue("NUMBER.OF.SYNC.ERROR.DAYS").trim()
							+ " order by detect_time desc");
			request.setAttribute("BRecordList2",
					new BReportBuffer().getAllRecord(beanImport));
			beanImport
					.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and trim(status) in ('E1','E2') and trim(COMPLETE_STATUS) ='F' and detect_time <= sysdate and detect_time >= sysdate-"
							+ IKey.getValue("NUMBER.OF.SYNC.ERROR.DAYS").trim()
							+ " order by detect_time desc");
			request.setAttribute("BPaging2",
					new BReportBuffer().getPaging(beanImport));
			FChangeData beant = new FChangeData();
			beant.setId_sql(1);
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')");
			request.setAttribute("BTotalDate",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'mm/yyyy')=to_char(sysdate,'mm/yyyy')");
			request.setAttribute("BTotalMonth",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'yyyy')=to_char(sysdate,'yyyy')");
			request.setAttribute("BTotalYear",
					new BReportBuffer().getPaging(beant));
			if (bean.getValue("thread3") == null) {

				beanImport.setThread3(IKey.getThread3());
			} else {
				beanImport.setThread3(IKey.getThread3());
				IKey.setThread3(bean.getThread3());
			}
			if (bean.getValue("delay") != null) {
				IKey.setDelay3(bean.getDelay());
			}

			if (!IKey.isThread3Runing()) {
				(new AThread3()).start();
				IKey.setThread3Runing(true);
			}
			request.setAttribute("BTables", beanImport);
			target = anchor;
		} else if (anchor.equals("_VIEW_ALERT")) {
			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(ITables.TABLE_ALERT_BUFFER);
			beanImport.setPageIndex(bean.getPageIndex());
			beanImport.setColumnName(bean.getColumnName());
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			if (beanImport.getNameSQL() == null) {
				beanImport.setNameSQL("");
			}
			request.setAttribute("BColumnsList",
					new BReportBuffer().getAllColumnsInTable(beanImport));
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beanImport));

			beanImport.setId_sql(1);

			beanImport
					.setNameSQL("select * from (select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is null and STATUS in ('E1','E2') order by detect_time desc) where rownum <=10");
			request.setAttribute("BRecordList1",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging1",
					new BReportBuffer().getPaging(beanImport));
			beanImport
					.setNameSQL("select * from (select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and STATUS in ('E1','E2') order by detect_time desc) where rownum <=10");
			request.setAttribute("BRecordList2",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging2",
					new BReportBuffer().getPaging(beanImport));
			FChangeData beant = new FChangeData();
			beant.setId_sql(1);
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')");
			request.setAttribute("BTotalDate",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'mm/yyyy')=to_char(sysdate,'mm/yyyy')");
			request.setAttribute("BTotalMonth",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_ALERT_BUFFER + " where to_char("
					+ IFields.ALERT_BUFFER_DETECT_TIME
					+ ",'yyyy')=to_char(sysdate,'yyyy')");
			request.setAttribute("BTotalYear",
					new BReportBuffer().getPaging(beant));

			if (bean.getValue("delay") != null) {
				IKey.setDelay(bean.getDelay());
			}

			request.setAttribute("BTables", beanImport);
			target = anchor;
		}
		request.setAttribute("BAlertBuffer", bean);

		if (!errors.isEmpty())
			saveErrors(request, errors);

		return mapping.findForward(target);
	}

	private String validate(FAlertBuffer bean, String anchor,
			ActionErrors errors) {
		if (anchor.equals(_VIEW)) {

		} else if (anchor.equals(_EDIT)) {
			if ((bean.getDescription().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			} else if (bean.getAlert_Id() <= 0) {
				errors.add("alert", new ActionError("errors.apps.edit.idnull"));
			}
		} else if (anchor.equals(_CREATE)) {
			if ((bean.getDescription().trim().equals(_BLANK))) {
				errors.add("alert", new ActionError("errors.apps.edit.short"));
			}
		} else if (anchor.equals(_DELETE)) {
			if (bean.getAlert_Id() <= 0) {
				errors.add("alert", new ActionError("errors.apps.edit.idnull"));
			}
		}
		return anchor;
	}

}