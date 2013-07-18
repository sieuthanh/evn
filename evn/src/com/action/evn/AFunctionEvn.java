package com.action.evn;

import com.action.ACore;

import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.reportBuffer.BReportBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.thread2.BThread2;
import com.bo.evn.ticket.BTicket;

import com.exp.EException;
import com.form.admin.FFunction;
import com.form.evn.FChangeData;

import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;

import com.inf.IFields;
import com.inf.IKey;
import com.inf.ITables;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AFunctionEvn extends ACore {
	private int thread1;

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws EException, IOException, ServletException, SQLException {
		final String LOCATION = this + "->executeAction()";
		String target = _LOGIN;
		ActionErrors errors = new ActionErrors();
		FFunction bean = (FFunction) form;
		String anchor = bean.getValue(APP_ANCHOR, "");
		if (anchor.equals(_VIEW)) {
			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(bean.getNameTable());
			beanImport.setPageIndex(bean.getPageIndex());
			beanImport.setColumnName(bean.getColumnName());
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			beanImport.setNameSQL(bean.getSqlWhere());
			if (bean.getNameTable().equals(ITables.TABLE_TABLES)) {
				beanImport.setOrderBy(" order by src_connect_id, name");
			}
			request.setAttribute("BSourceConnectBuffers",
					new BSourceConnectBuffer().getAllRecordByExitsTable(bean
							.getNameTable()));

			FSourceConnectBuffer beanSrc = new FSourceConnectBuffer();
			if (bean.getType() > 0) {
				beanSrc.setSrc_Connect_Id(bean.getType());
				beanSrc = new BSourceConnectBuffer().getRecordByID(beanSrc);
				beanSrc.setNameTable(bean.getNameTable());
				beanSrc.setPageIndex(bean.getPageIndex());
				if (bean.getSqlWhere() == null) {
					bean.setSqlWhere("");
				}
				beanSrc.setNameSQL(bean.getSqlWhere());
				request.setAttribute("BColumnsList",
						new BThread2().getAllColumnsInTable(beanSrc));
				request.setAttribute("BRecordList",
						new BThread2().getAllRecord(beanSrc, beanImport));
				request.setAttribute("BPaging",
						new BThread2().getPaging(beanSrc, beanImport));
			} else {
				request.setAttribute("BColumnsList",
						new BReportBuffer().getAllColumnsInTable(beanImport));
				request.setAttribute("BRecordList",
						new BReportBuffer().getAllRecord(beanImport));
				request.setAttribute("BPaging",
						new BReportBuffer().getPaging(beanImport));
			}

			request.setAttribute("BTables", bean);

			target = anchor;
		} else if (anchor.equals("_VIEW_NV")) {

			FTables beanT = new FTables();
			if (bean.getId() == 0) {
				beanT = new BTables().getRecordByTop1();
				bean.setNameTable(beanT.getName());
				bean.setId(beanT.getId());
				request.setAttribute("BSourceConnectBuffers",
						new BSourceConnectBuffer()
								.getAllRecordByExitsTable(beanT.getName()));
			} else {
				beanT.setId(bean.getId());
				beanT = new BTables().getRecordByID(beanT);
				request.setAttribute("BSourceConnectBuffers",
						new BSourceConnectBuffer()
								.getAllRecordByExitsTable(beanT.getName()));

			}

			FSourceConnectBuffer beanSrc = new FSourceConnectBuffer();
			beanSrc.setSrc_Connect_Id(beanT.getDes_connect_id());
			if (bean.getType() > 0) {
				beanSrc.setSrc_Connect_Id(bean.getType());
			}

			beanSrc = new BSourceConnectBuffer().getRecordByID(beanSrc);
			beanSrc.setNameTable(beanT.getName());
			beanSrc.setPageIndex(bean.getPageIndex());
			try {
				request.setAttribute("BColumnsList",
						new BThread2().getAllColumnsInTable(beanSrc));
				request.setAttribute("BRecordList",
						new BThread2().getAllRecord(beanSrc));
				request.setAttribute("BPaging",
						new BThread2().getPaging(beanSrc));
			} catch (Exception ex) {
				ex.printStackTrace();
				request.setAttribute("errorValue", ex.getMessage());
			} finally {
			}
			request.setAttribute("BTables", bean);
			target = _VIEW;
		} else if (anchor.equals("_VIEW_FKEY")) {
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(bean.getNameTable());
			beanImport.setNameSQL(bean.getSqlWhere());
			beanImport.setPageIndex(bean.getPageIndex());
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beanImport));
			request.setAttribute("BTables", beanImport);
			target = anchor;
		} else if (anchor.equals("_VIEW_TICKET")) {
			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(ITables.TABLE_TICKET_BUFFER);
			beanImport.setPageIndex(bean.getPageIndex());
			beanImport.setColumnName(bean.getColumnName());
			beanImport
					.setNameSQL("SELECT a.ticket_id, a.ticket_id_err, b.fullname, a.status,\n"
							+ "       a.active, a.evn_id, a.evn_time, a.buffer_id, a.buffer_time,\n"
							+ "       a.ebs_id, a.ebs_time, a.start_time, a.end_time, a.total_records,\n"
							+ "       a.code, a.table_name, a.where_data, a.error_description\n"
							+ "  FROM evn_ticket_buffer a,evn_source_connect_to_buffer b\n"
							+ "  where a.src_connect_id=b.src_connect_id");
			if (bean.getColumnName() == null)
				bean.setColumnName("FULLNAME");

			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			beanImport.setNameSQL(beanImport.getNameSQL() + " and UPPER("
					+ bean.getColumnName() + ") like '%"
					+ bean.getSqlWhere().toUpperCase()
					+ "%' order by a.ticket_id desc");
			beanImport.setId_sql(1);

			request.setAttribute("BColumnsList",
					new BReportBuffer().getAllColumnsInTable(beanImport));
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beanImport));

			FChangeData beanImport1 = new FChangeData();
			beanImport1.setNameTable(ITables.TABLE_TICKET_BUFFER);
			// beanImport1.setPageIndex(bean.getPageIndex());
			// beanImport1.setColumnName(bean.getColumnName());
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			beanImport1.setId_sql(1);
			beanImport1
					.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME,"
							+ "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from "
							+ ITables.TABLE_TICKET_BUFFER
							+ " where STATUS in ('O','E1') and ACTIVE= -1 and START_TIME<= sysdate and END_TIME+ numtodsinterval("
							+ IKey.getDelay()
							+ ", 'MINUTE')>=sysdate order by START_TIME desc");
			request.setAttribute("BRecordList1",
					new BReportBuffer().getAllRecord(beanImport1));
			request.setAttribute("BPaging1",
					new BReportBuffer().getPaging(beanImport1));
			beanImport1
					.setNameSQL("select TICKET_ID,decode(trunc(evn_time-sysdate),0,to_char(evn_time,'HH24:mi:ss'),trunc(evn_time-sysdate)) as EVN_TIME,"
							+ "decode(trunc(start_time-sysdate),0,to_char(start_time,'HH24:mi:ss'),trunc(start_time-sysdate)) as START_TIME,ROUND((end_time-start_time)*24)||' hours' as END_TIME,TOTAL_RECORDS,CODE,TABLE_NAME,STATUS,EVN_TIME as t1,START_TIME as t2,END_TIME as t3 from "
							+ ITables.TABLE_TICKET_BUFFER
							+ " where STATUS in ('O','E1') and active=0 and START_TIME<= sysdate and END_TIME+ numtodsinterval("
							+ IKey.getDelay()
							+ ", 'MINUTE')>=sysdate order by END_TIME desc");
			request.setAttribute("BRecordList2",
					new BReportBuffer().getAllRecord(beanImport1));
			request.setAttribute("BPaging2",
					new BReportBuffer().getPaging(beanImport1));
			FChangeData beant = new FChangeData();
			beant.setId_sql(1);
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_TICKET_BUFFER + " where to_char("
					+ IFields.TICKET_START_TIME
					+ ",'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')");
			request.setAttribute("BTotalDate",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_TICKET_BUFFER + " where to_char("
					+ IFields.TICKET_START_TIME
					+ ",'mm/yyyy')=to_char(sysdate,'mm/yyyy')");
			request.setAttribute("BTotalMonth",
					new BReportBuffer().getPaging(beant));
			beant.setNameSQL("select TICKET_ID from "
					+ ITables.TABLE_TICKET_BUFFER + " where to_char("
					+ IFields.TICKET_START_TIME
					+ ",'yyyy')=to_char(sysdate,'yyyy')");
			request.setAttribute("BTotalYear",
					new BReportBuffer().getPaging(beant));

			if (bean.getValue("thread1") == null) {
				beanImport.setThread1(IKey.getThread1());
			} else {
				IKey.setThread1(bean.getThread1());
				beanImport.setThread1(IKey.getThread1());
			}

			if (bean.getValue("thread2") == null) {

				beanImport.setThread2(IKey.getThread2());
			} else {
				IKey.setThread2(bean.getThread2());
				beanImport.setThread2(IKey.getThread2());
			}

			request.setAttribute("BTables", beanImport);
			// request.setAttribute("BTicket",bean);
			FTicket beanT = new FTicket();
			if (bean.getValue("amountT") == null) {
				beanT.setAmount(IKey.getAmountT());
			} else {
				IKey.setAmountT(beanT.getAmountT());
				beanT.setAmount(IKey.getAmountT());
			}
			if (bean.getValue("delay") == null) {
				beanT.setDelay(IKey.getDelay());
			} else {
				IKey.setDelay(beanT.getDelay());
				beanT.setDelay(IKey.getDelay());
			}
			beanT.setSearch_time(bean.dateToString(bean.getCurrentSqlDate(),
					"dd/MM/yyyy"));
			request.setAttribute("BTicket", beanT);
			target = anchor;
		} else if (anchor.equals("_VIEW_ALERT")) {
			FChangeData beanImport = new FChangeData();
			beanImport.setNameTable(ITables.TABLE_ALERT_BUFFER);
			beanImport.setPageIndex(bean.getPageIndex());
			beanImport.setColumnName(bean.getColumnName());
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			beanImport.setNameSQL(bean.getSqlWhere());
			// beanImport.setId_sql(1);

			// beanImport.setNameSQL("select * from evn_alert_buffer order by detect_time desc");

			request.setAttribute("BColumnsList",
					new BReportBuffer().getAllColumnsInTable(beanImport));
			request.setAttribute("BRecordList",
					new BReportBuffer().getAllRecord(beanImport));
			request.setAttribute("BPaging",
					new BReportBuffer().getPaging(beanImport));
			request.setAttribute("BTables", beanImport);
			FAlertBuffer beanT = new FAlertBuffer();
			try {
				beanT = new BAlertBuffer().getAlertBufferTop(1);

				FSourceConnectBuffer beanS = new FSourceConnectBuffer();
				BSourceConnectBuffer boS = new BSourceConnectBuffer();
				beanS.setSrc_Connect_Id(beanT.getSrc_Connect_Id());
				beanS = boS.getRecordByID(beanS);
				beanT.setSrc_name(beanS.getFullName());
			} catch (Exception e) {
				// TODO
			}
			if (bean.getValue("thread3") == null) {

				beanT.setThread3(IKey.getThread3());
			} else {
				IKey.setThread3(beanT.getThread3());
				beanT.setThread3(IKey.getThread3());
			}
			if (bean.getValue("delay") == null) {

				beanT.setDelay(IKey.getDelay3());
			} else {
				IKey.setDelay3(beanT.getDelay());
				beanT.setDelay(IKey.getDelay3());
			}

			FChangeData beanImport1 = new FChangeData();
			beanImport1.setNameTable(ITables.TABLE_TICKET_BUFFER);
			beanImport1.setPageIndex(bean.getPageIndex());
			beanImport1.setColumnName(bean.getColumnName());
			if (bean.getSqlWhere() == null) {
				bean.setSqlWhere("");
			}
			beanImport1.setId_sql(1);

			beanImport1
					.setNameSQL("select * from (select ticket_id, src_record_id, error_description, detect_time, table_name from evn_alert_buffer where sent_time is null and STATUS in ('E1','E2') order by detect_time desc) where rownum <=10");
			request.setAttribute("BRecordList1",
					new BReportBuffer().getAllRecord(beanImport1));
			beanImport1
					.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, table_name from evn_alert_buffer where sent_time is null and STATUS in ('E1','E2') order by detect_time desc");
			request.setAttribute("BPaging1",
					new BReportBuffer().getPaging(beanImport1));
			beanImport1
//					.setNameSQL("select * from (select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and STATUS in ('E1','E2') order by detect_time desc) where rownum <=10");
			.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and trim(status) in ('E1','E2') and trim(COMPLETE_STATUS) ='F' and detect_time <= sysdate and detect_time >= sysdate-"+IKey.getValue("NUMBER.OF.SYNC.ERROR.DAYS").trim()+" order by detect_time desc");

			request.setAttribute("BRecordList2",
					new BReportBuffer().getAllRecord(beanImport1));
			beanImport1
					.setNameSQL("select ticket_id, src_record_id, error_description, detect_time, sent_time,table_name from evn_alert_buffer where sent_time is not null and trim(status) in ('E1','E2') and trim(COMPLETE_STATUS) ='F' and detect_time <= sysdate and detect_time >= sysdate-"+IKey.getValue("NUMBER.OF.SYNC.ERROR.DAYS").trim()+" order by detect_time desc");
			request.setAttribute("BPaging2",
					new BReportBuffer().getPaging(beanImport1));
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
			beanT.setSearch_time(bean.dateToString(bean.getCurrentSqlDate(),
					"dd/MM/yyyy"));
			request.setAttribute("BAlertBuffer", beanT);
			target = anchor;
		} else if (anchor.equals("_GET_XML")) {
			FTicket beanA = new FTicket();
			beanA.setTicket_id(bean.getId());
			beanA.setNameTable(ITables.TABLE_TICKET_BUFFER);
			BTicket bo1 = new BTicket();
			String pathFile = "";
			String sessionID = me.getSessionID();
			pathFile = bo1.exportXml(sessionID, beanA);
			bean.download(pathFile, bean.getId() + ".xml", null);
			bean.deleteFile(pathFile);

			target = null;
		} else if (anchor.equals("_GET_ALERT_XML")) {
			FAlertBuffer beanA = new FAlertBuffer();
			beanA.setAlert_Id(bean.getId());
			beanA.setNameTable(ITables.TABLE_ALERT_BUFFER);
			BAlertBuffer bo1 = new BAlertBuffer();
			String pathFile = "";
			String sessionID = me.getSessionID();
			pathFile = bo1.exportXml(sessionID, beanA);
			bean.download(pathFile, beanA.getAlert_Id() + ".xml", null);
			bean.deleteFile(pathFile);

			target = null;
		}

		request.setAttribute("BTabless", new BTables().getAllRecord());

		if (!errors.isEmpty())
			saveErrors(request, errors);

		return mapping.findForward(target);
	}

	public int getThread1() {
		return thread1;
	}

	public void setThread1(int thread1) {
		this.thread1 = thread1;
	}
}
