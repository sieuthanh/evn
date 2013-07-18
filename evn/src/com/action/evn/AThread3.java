package com.action.evn;

import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.thread2.BThread2;
import com.exp.EException;
import com.form.FBeans;
import com.form.evn.alertBuffer.FAlertBuffer;
import com.form.evn.common.FCommon;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.inf.IKey;
import com.ws.getFmisdata;
import java.sql.SQLException;

public class AThread3 extends Thread {
	private BAlertBuffer bo = new BAlertBuffer();
	private FAlertBuffer bean = new FAlertBuffer();

	public static void main(String args[]) throws java.io.IOException {
		(new AThread3()).start();
	}

	public void run() {
		while (true) {
			if (IKey.getThread3() == 1) {
				FBeans beans = new FBeans();

				System.out
						.println("---------------runing thread3-----------------");
				try {
					beans = bo.getAllRecordBySentTimeIsNull();
					getFmisdata ws = new getFmisdata();
					for (int i = 0; i < beans.size(); i++) {
						bean = (FAlertBuffer) beans.get(i);
						bean.getTable_Name();
						FTables beanT = new FTables();
						BTables boT = new BTables();
						beanT.setName(bean.getTable_Name());
						beanT.setSrc_connect_id(bean.getSrc_Connect_Id());
						beanT = boT.getRecordByName(beanT);

						FSourceConnectBuffer beanSRC = new FSourceConnectBuffer();
						BSourceConnectBuffer boSrc = new BSourceConnectBuffer();
						beanSRC.setSrc_Connect_Id(beanT.getSrc_connect_id());
						beanSRC = boSrc.getRecordByID(beanSRC);

						FSourceConnectBuffer beanDesc = new FSourceConnectBuffer();
						BSourceConnectBuffer boDesc = new BSourceConnectBuffer();
						beanDesc.setSrc_Connect_Id(beanT.getDes_connect_id());
						beanDesc = boDesc.getRecordByID(beanSRC);

						FCommon beanCM = new FCommon();
						beanCM.setStatus(bean.getStatus());
						beanCM.setSrc_Record_Id(bean.getSrc_Record_Id());
						beanCM.setTicket_Id(bean.getTicket_Id());
						beanCM.setError_code(bean.getError_Code());
						beanCM.setError_description(bean.getError_Description());
						beanCM.setNameTable(bean.getTable_Name());
						if (beanSRC.getType().equals("D")) {
							try {
								new BThread2().updateCommon(beanCM,
										beanSRC.getConnection(),
										beanSRC.getUrl());
							} catch (ClassNotFoundException e) {
								// TODO
							}
						} else {
							ws.readWebsite(beanSRC.getUrl()
									+ String.format(
											IKey.WS_FUNCTION_PUSHDATA,
											bean.getTicket_Id() + "",
											beanCM.getSrc_Record_Id(),
											beanCM.getError_code()
													+ ":"
													+ beanCM.getError_description()));
						}
						if (beanDesc.getType().equals("D")) {
							try {
								new BThread2().updateCommon(beanCM,
										beanSRC.getConnection(),
										beanDesc.getUrl());
							} catch (ClassNotFoundException e) {
								// TODO
							}
						} else {
							ws.readWebsite(beanDesc.getUrl()
									+ String.format(
											IKey.WS_FUNCTION_PUSHDATA,
											bean.getTicket_Id() + "",
											beanCM.getSrc_Record_Id(),
											beanCM.getError_code()
													+ ":"
													+ beanCM.getError_description()));
						}
						bo.updateStatus(bean.getAlert_Id(), "F");
					}

				} catch (SQLException e) {
					// TODO
				} catch (EException e) {
					// TODO
				}

				try {
					sleep(IKey.getDelay3() * 60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// System.out.println("---------------stop thread3-----------------");
		}

	}
}
