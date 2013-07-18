package com.ws;

import com.bo.evn.evnErrorBuffer.BEvnErrorBuffer;
import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;
import com.exp.EException;

import com.form.evn.evnErrorBuffer.ErrorConstant;
import com.form.evn.evnErrorBuffer.FEvnErrorBuffer;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getTicket extends HttpServlet {

	public static String invoke(String aClass, String aMethod, Class[] params,
			Object[] args) throws Exception {

		Class c = Class.forName(aClass);
		Method m = c.getDeclaredMethod(aMethod, params);
		Object i = c.newInstance();
		Object r = m.invoke(i, args);
		return r.toString();
	}

	private FRequestTicket invalid(FRequestTicket bean) {
		BSourceAccountBuffer bo = new BSourceAccountBuffer();
		BSourceConnectBuffer boCon = new BSourceConnectBuffer();
		FSourceConnectBuffer beanCon = new FSourceConnectBuffer();
		FSourceAccountBuffer beanAc = new FSourceAccountBuffer();
		BTables boTable = new BTables();
		FTables beanTable = new FTables();
		try {
			beanAc.setShortName(bean.getUserName());
			beanAc.setAccount_Pass(bean.getPassWord());
			beanAc = bo.getSrcConnectByAccount(beanAc);

			bean.setEvn_id(beanAc.getAccount_Id());
			bean.setScr_connect_id(beanAc.getSrc_Connect_Id());
			if (beanAc.getSrc_Connect_Id() == 0) {
				bean.setResult(ErrorConstant.RESULT_LOI_TAI_KHOAN_KET_NOI);
				return bean;
			}
			beanTable.setName(bean.getTableName());
			if (bean.getTableName().contains(",")) {
				bean.getTableName().replaceAll(" ", "");
				String[] table = bean.getTableName().split(",");
				beanTable.setName(table[0]);
			}

			beanTable.setSrc_connect_id(beanAc.getSrc_Connect_Id());
			beanTable = boTable.getRecordByName(beanTable);

			beanCon.setSrc_Connect_Id(beanTable.getSrc_connect_id());
			beanCon = boCon.getRecordByID(beanCon);
			System.out.println("Connection:" + beanCon.getConnection());
		} catch (SQLException ex) {
			bean.setResult(ErrorConstant.RESULT_LOI_MO_RONG);
		} catch (EException e) {
			bean.setResult(ErrorConstant.RESULT_LOI_MO_RONG);
		}
		if (beanAc.getSrc_Connect_Id() <= 0)
			bean.setResult(ErrorConstant.RESULT_LOI_KET_NOI);

		else if (beanTable.getSrc_connect_id() <= 0)
			bean.setResult(ErrorConstant.RESULT_LOI_TEN_BANG);
		else if (Integer.parseInt(bean.getTotalRecord()) < 0) {
			bean.setResult(ErrorConstant.RESULT_LOI_SO_BAN_GHI);
		} else {
			BTables btable = new BTables();
			String tables = bean.getTableName();
			tables.replaceAll(" ", "");
			String[] table = tables.split(",");
			for (int i = 0; i < table.length; i++) {
				try {
					if (!btable.isExistName(table[i])) {
						bean.setResult(ErrorConstant.RESULT_LOI_TEN_BANG);
						return bean;
					}
				} catch (SQLException e) {
					bean.setResult(ErrorConstant.RESULT_LOI_MO_RONG);
				} catch (EException e) {
					bean.setResult(ErrorConstant.RESULT_LOI_MO_RONG);
				}
			}

		}
		if ((Long.parseLong(DateProc.getYYYYMMDDHHMMString(DateProc
				.createTimestamp())) > Long.parseLong(bean.getFromDate()))
				|| (Long.parseLong(bean.getFromDate()) > Long.parseLong(bean
						.getToDate()))) {
			bean.setResult(ErrorConstant.RESULT_LOI_THOI_GIAN);
		}
		return bean;

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reading the user input
		// http://url/getticket?usr=v1&pas=v3&code=v3rs=v4&tb=v5&st=v6&ft=v7
		FEvnErrorBuffer beanErr = new FEvnErrorBuffer();
		FRequestTicket beanR = new FRequestTicket();
		beanR.setUserName(request.getParameter("usr"));
		beanR.setPassWord(request.getParameter("pas"));
		beanR.setReportCode(request.getParameter("code"));
		beanR.setTotalRecord(request.getParameter("rs"));
		beanR.setTableName(request.getParameter("tb"));
		beanR.setFromDate(request.getParameter("st"));
		beanR.setToDate(request.getParameter("ft"));
		beanR.setResult("0");

		beanR = invalid(beanR);
		BEvnErrorBuffer boError = new BEvnErrorBuffer();
		try {
			beanErr = boError.getRecordByCode(beanR.getResult());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		catch (EException | SQLException e1) {
//			e1.printStackTrace();
//		}
		FTicket bean = new FTicket();

		if (beanR.getResult().equals("0")) {
			BTicket boT = new BTicket();
			try {
				bean.setCode(beanR.getReportCode());
				bean.setTable_name(beanR.getTableName());
				bean.setStart_time(beanR.getFromDate());
				bean.setEnd_time(beanR.getToDate());
				bean.setTotal_records(Integer.parseInt(beanR.getTotalRecord()));
				bean.setSrc_connect_id(beanR.getScr_connect_id());
				bean.setEvn_id(beanR.getEvn_id());
				bean = boT.insert(bean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.setContentType("text/xml; charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<?xml version=\"1.0\"?>");
		out.println("<getTicket>");
		out.println("<ticket_id>");
		out.println(bean.getTicket_id());
		out.println("</ticket_id>");
		out.println("<src_connect_id>");
		out.println(bean.getSrc_connect_id());
		out.println("</src_connect_id>");
		out.println("<status>");
		out.println(bean.getStatus());
		out.println("</status>");
		out.println("<active>");
		out.println(bean.getActive());
		out.println("</active>");
		out.println("<evn_id>");
		out.println(bean.getEvn_id());
		out.println("</evn_id>");
		out.println("<evn_time>");
		out.println(bean.getEvn_time());
		out.println("</evn_time>");
		out.println("<total_records>");
		out.println(bean.getTotal_records());
		out.println("</total_records>");
		out.println("<code>");
		out.println(bean.getCode());
		out.println("</code>");
		out.println("<table_name>");
		out.println(bean.getTable_name());
		out.println("</table_name>");
		out.println("<start_time>");
		out.println(bean.getStart_time());
		out.println("</start_time>");
		out.println("<end_time>");
		out.println(bean.getEnd_time());
		out.println("</end_time>");
		out.println("<result>");
		out.println(beanR.getResult());
		out.println("</result>");
		if (!beanR.getResult().equals("0")) {
			out.println("<description>");
			out.println(beanErr.getError_Description());
			out.println("</description>");
		}
		out.println("</getTicket>");

	}

}
