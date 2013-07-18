package com.ws;

import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;

import com.exp.EException;

import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;

import com.inf.DateProc;

import java.io.File;
import com.inf.IKey;

import huyenhoc.language;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.net.URL;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getData extends HttpServlet {

	public static String invoke(String aClass, String aMethod, Class[] params,
			Object[] args) throws Exception {

		Class c = Class.forName(aClass);
		Method m = c.getDeclaredMethod(aMethod, params);
		Object i = c.newInstance();
		Object r = m.invoke(i, args);
		return r.toString();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reading the user input
		String page = "";
		BTicket boT = new BTicket();
		FTicket bean = new FTicket();
		BTables boTable = new BTables();
		FTables beanTable = new FTables();
		BSourceConnectBuffer boSource = new BSourceConnectBuffer();
		FSourceConnectBuffer beanSource = new FSourceConnectBuffer();
		getFmisdata ws = new getFmisdata();
		getCmisdata cmis = new getCmisdata();
		int ticketId = ws.getNumber(request.getParameter("ticketid"));
		page = "<?xml version=\"1.0\"?><getdata>ERROR</getdata>";
		if (ticketId > 0) {
			try {
				bean = boT.getTicketByID(ticketId);
				String tables = bean.getTable_name();
				beanTable.setName(tables);
				if (tables.contains(",")) {
					tables.replaceAll(" ", "");
					String[] table = tables.split(",");
					beanTable.setName(table[0]);
				}
				beanTable.setSrc_connect_id(bean.getSrc_connect_id());
				beanTable = boTable.getRecordByName(beanTable);

				beanSource.setSrc_Connect_Id(beanTable.getSrc_connect_id());
				beanSource = boSource.getRecordByID(beanSource);

				BSourceAccountBuffer boAc = new BSourceAccountBuffer();
				FSourceAccountBuffer beanAc = new FSourceAccountBuffer();
				beanAc.setSrc_Connect_Id(beanTable.getSrc_connect_id());
				beanAc = boAc.getRecordBySrc(beanAc);
				if (beanSource.getType().equals("W")) {
					// log file
					Long curTimeInMinute = new Long(System.currentTimeMillis());
					String folder = DateProc.TimestampYYYYMM(
							DateProc.StringYYYYMMDDHH24MI2Timestamp(bean
									.getEvn_time()), IKey.SYSTEM_FILE_SCHIP)
							+ IKey.SYSTEM_FILE_SCHIP + ticketId;
					String mt_log_folder = IKey.SYSTEM_FILE_XML + folder;
					String nameFile = ticketId + "." + curTimeInMinute + ".xml";
					URL website = new URL(beanSource.getUrl()
							+ String.format(IKey.WS_FUNCTION_GETDATA, ticketId
									+ ""));
					cmis.copyFile(website, mt_log_folder
							+ IKey.SYSTEM_FILE_SCHIP + nameFile);
					boT.update_ticket_rasoat(bean.getTicket_id(), folder
							+ IKey.SYSTEM_FILE_SCHIP + nameFile);
					page = "<?xml version=\"1.0\"?><getdata>OK</getdata>";
				} else if (beanSource.getType().equals("D")) {
					// thuc hien read db
					// log file
					Long curTimeInMinute = new Long(System.currentTimeMillis());
					String folder = DateProc.TimestampYYYYMM(
							DateProc.StringYYYYMMDDHH24MI2Timestamp(bean
									.getEvn_time()), IKey.SYSTEM_FILE_SCHIP)
							+ IKey.SYSTEM_FILE_SCHIP + ticketId;
					String nameFile = ticketId + "." + curTimeInMinute;
					page = ws.readDB2Xml(beanTable.getSrc_connect_id(),
							beanSource.getConnection(), beanSource.getUrl(),
							bean.getTable_name(), ticketId, curTimeInMinute,
							bean.getEvn_time());
					if (page.equals("1")) {
						boT.update_ticket_rasoat(bean.getTicket_id(), folder
								+ IKey.SYSTEM_FILE_SCHIP + nameFile);
						page = "<?xml version=\"1.0\"?><getdata>OK</getdata>";
					} else {
						boT.updateWhere(bean.getTicket_id(), -1);
						page = "<?xml version=\"1.0\"?><getdata>" + page
								+ "</getdata>";
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					boT.updateWhere(bean.getTicket_id(), -1);
				} catch (SQLException f) {
					// TODO
				} catch (EException f) {
					// TODO
				}
			}
		} else {
			page = "<?xml version=\"1.0\"?><getdata>ERROR</getdata>";
		}

		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println(page);

	}

}
