package com.action.evn;

import com.bo.evn.sourceAccountBuffer.BSourceAccountBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;
import com.exp.EException;
import com.form.FBeans;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import com.inf.IKey;
import com.ws.getCmisdata;
import com.ws.getFmisdata;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;

public class AThread1 extends Thread {
	private BTicket bo = new BTicket();
	private FTicket bean = new FTicket();

	public void run() {
		while (true) {
			if (IKey.getThread1() == 1) {
				boolean result = false;

				System.out
						.println("---------------runing thread1-----------------");

				try {
					FBeans beans = new FBeans();
					getCmisdata cmis = new getCmisdata();
					beans = bo.getAllRecordRaSoat(-1);
					for (int i = 0; i < beans.size(); i++) {
						bean = new FTicket();
						bean = (FTicket) beans.get(i);
						BTables boTable = new BTables();
						FTables beanTable = new FTables();

						String tables = bean.getTable_name();
						beanTable.setName(tables);
						if (tables.contains(",")) {
							tables.replaceAll(" ", "");
							String[] table = tables.split(",");
							beanTable.setName(table[0]);
						}
						beanTable.setSrc_connect_id(bean.getSrc_connect_id());
						beanTable = boTable.getRecordByName(beanTable);

						BSourceConnectBuffer boSource = new BSourceConnectBuffer();
						FSourceConnectBuffer beanSource = new FSourceConnectBuffer();

						beanSource.setSrc_Connect_Id(beanTable
								.getSrc_connect_id());
						beanSource = boSource.getRecordByID(beanSource);
						BSourceAccountBuffer boAc = new BSourceAccountBuffer();
						FSourceAccountBuffer beanAc = new FSourceAccountBuffer();
						beanAc.setSrc_Connect_Id(beanTable.getSrc_connect_id());
						beanAc = boAc.getRecordBySrc(beanAc);

						String page = "";
						int utf = 0;
						getFmisdata ws = new getFmisdata();
						if (beanSource.getType().equals("D")) {
							// thuc hien read db
							Long curTimeInMinute = new Long(
									System.currentTimeMillis());
							page = ws
									.readDB2Xml(beanTable.getSrc_connect_id(),
											beanSource.getConnection(),
											beanSource.getUrl(),
											bean.getTable_name(),
											bean.getTicket_id(),
											curTimeInMinute,
											bean.getEvn_time(),
											bean.getTotal_records());
							// if (page != "0") {
							// log file
							if (page.equals("1")) {
								String folder = DateProc
										.TimestampYYYYMM(
												DateProc.StringYYYYMMDDHH24MI2Timestamp(bean
														.getEvn_time()),
												IKey.SYSTEM_FILE_SCHIP)
										+ IKey.SYSTEM_FILE_SCHIP
										+ bean.getTicket_id();
								String mt_log_folder = IKey.SYSTEM_FILE_XML
										+ folder;
								String nameFile = folder
										+ IKey.SYSTEM_FILE_SCHIP
										+ bean.getTicket_id() + "."
										+ curTimeInMinute;
								bo.update_ticket_rasoat(bean.getTicket_id(),
										nameFile);

							} else {
								bo.updateWhere(bean.getTicket_id(), -1);
							}
						} else if (beanSource.getType().equals("W")) {
							page = ws.readWebsite(beanSource.getUrl()
									+ String.format(IKey.WS_FUNCTION_GETDATA,
											beanAc.getShortName(),
											beanAc.getAccount_Pass(),
											bean.getTicket_id() + ""));
							// log file
							String folder = DateProc.TimestampYYYYMM(DateProc
									.StringYYYYMMDDHH24MI2Timestamp(bean
											.getEvn_time()),
									IKey.SYSTEM_FILE_SCHIP);
							String mt_log_folder = IKey.SYSTEM_FILE_XML
									+ folder + IKey.SYSTEM_FILE_SCHIP
									+ bean.getTicket_id();
							String nameFile = mt_log_folder
									+ IKey.SYSTEM_FILE_SCHIP
									+ bean.getTicket_id();

							URL website = new URL(beanSource.getUrl()
									+ String.format(IKey.WS_FUNCTION_GETDATA,
											bean.getTicket_id() + ""));

							System.out.println("save is:"
									+ cmis.copyFile(website, mt_log_folder
											+ IKey.SYSTEM_FILE_SCHIP + nameFile
											+ ".1.xml"));
							bo.update_ticket_rasoat(bean.getTicket_id(),
									nameFile);

						}

					}
				} catch (SQLException e) {
					// TODO
				} catch (EException e) {
					// TODO
				} catch (Exception e) {
				}

				try {
					sleep(IKey.getDelay() * 60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// System.out.println("---------------stop thread1-----------------");
		}

	}
}
