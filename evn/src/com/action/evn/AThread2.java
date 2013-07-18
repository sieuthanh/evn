package com.action.evn;

import com.bo.evn.alertBuffer.BAlertBuffer;
import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.thread2.BThread2;
import com.bo.evn.ticket.BTicket;
import com.dao.connection.DBConnector;
import com.dao.evn.thread2.DThread2;
import com.exp.EException;
import com.form.FBeans;
import com.form.evn.FChangeData;
import com.form.evn.FXml;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import com.inf.IKey;
import com.inf.Queue;
import com.ws.FRowxml;
import com.ws.getFmisdata;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AThread2 extends Thread {
	private BTicket bo = new BTicket();
	BTables boTable = new BTables();
	FTables beanTable = new FTables();
	getFmisdata ws = new getFmisdata();
	private static Queue dataQueue = null;
	private static int checkRecord = 0;

	public AThread2() {
		dataQueue = IKey.getDataQueue();
	}

	public void destroy() {
		IKey.removeThread(this);
	}

	public void run() {

		while (true) {
			System.out
					.println("---------------runing thread2-----------------");

			FTicket bean = (FTicket) IKey.getDataQueue().dequeue();
			if (bean.getTicket_id() > 0) {
				pushdata(bean.getTicket_id(), bean);
			}

			if (IKey.getThread2() == 0) {
				this.interrupt();
				System.out.println("Stop thread2");
			}
			try {
				sleep(5 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static FBeans printRow(FBeans beansField[], FBeans beansXML,
			int idConn, Connection conn, Element doc, String[] table,
			int level, String parentId, String parentKey, ArrayList parentList,
			long ticket_ID, int totalRecord, String[] key) {
		// int checkRecord=0;
		NodeList nodeLst = doc.getElementsByTagName("ROW");
		if (level > 0)
			totalRecord = 0;
		if (nodeLst.getLength() > 0) {
			// if(nodeLst.getLength()>0&&(totalRecord==nodeLst.getLength()||totalRecord==0||table.length>1)){
			for (int s = 0; s < nodeLst.getLength(); s++) {
				Node fstNode = nodeLst.item(s);
				if (fstNode.getParentNode().getNodeName().equals(table[level])) {
					System.out.println("Information of ROW " + (s + 1) + ":");
					String sqlFields = "";
					String sqlQuestion = "";
					String nameTable = table[level].trim();
					BTables boTable = new BTables();
					FTables beanTable = new FTables();
					ArrayList listR = new ArrayList();
					FXml beanXML = new FXml();
					// ////////// The King adds codes
					try {
						listR = printAttributes(beansField, beansXML, idConn,
								conn, fstNode, table, level, parentId,
								parentKey, parentList, ticket_ID, totalRecord,
								key);
						if (listR == null)
							return null;
					} catch (Exception e) {
						setInsert(idConn, ticket_ID, table[level],
								e.getMessage(), e.getMessage());
						return null;
					}

					// ///////// End codes
					FRowxml beanR = new FRowxml();
					ArrayList list = new ArrayList();
					long ticketID_Temp = 0;
					int checkInsert = 0;
					for (int r = 0; r < listR.size(); r++) {
						beanR = (FRowxml) listR.get(r);
						if (key[level].equals(beanR.getFiledName())) {
						} else {
							sqlFields += beanR.getFiledName() + ",";
							System.out.println(beanR.getFiledName());
						}
						for (int t = 0; t < beansField[level].size(); t++) {
							FChangeData beanC = new FChangeData();
							beanC = (FChangeData) beansField[level].get(t);
							if (beanC.getColumnName().equals(
									beanR.getFiledName())) {
								if (beanC.getColumnTypeName().equals("1")) {

									if (beanC.getColumnName().equals(
											"TICKET_ID")) {
										ticketID_Temp = Long.parseLong(beanR
												.getFiledData());
										if (ticket_ID != ticketID_Temp) {
											setInsert(
													beanTable
															.getDes_connect_id(),
													ticket_ID,
													nameTable,
													"record has wrong ticket id",
													"record has wrong ticket id");
											return null;
										}
									}
									if (beanR.getFiledData() == null) {
										list.add(null);
										System.out.println("null");
									} else {
										if (beanR.getFiledData().contains(".")) {
											try {
												Float.parseFloat(beanR
														.getFiledData().trim());
											} catch (Exception e) {
												setInsert(
														beanTable
																.getDes_connect_id(),
														ticket_ID, nameTable, e
																.getMessage(),
														e.getMessage());
												return null;

											}
											list.add(beanR.getFiledData()
													.trim());
											System.out.println(beanR
													.getFiledData().trim());

										} else {
											try {
												Long.parseLong(beanR
														.getFiledData().trim());
											} catch (Exception e) {
												setInsert(
														beanTable
																.getDes_connect_id(),
														ticket_ID, nameTable, e
																.getMessage(),
														e.getMessage());
												return null;

											}
											list.add(beanR.getFiledData()
													.trim());
											System.out.println(beanR
													.getFiledData().trim());
										}

									}

								} else if (beanC.getColumnTypeName()
										.equals("4")) {
									if (beanR.getFiledData() == null
											|| beanR.getFiledData().equals("")
											|| beanR.getFiledData().length() < 8) {
										list.add(null);
										System.out.println("null");
									} else {
										list.add(DateProc
												.StringYYYYMMDDHH24MI2Timestamp(beanR
														.getFiledData().trim()));
										System.out
												.println(DateProc
														.StringYYYYMMDDHH24MI2Timestamp(beanR
																.getFiledData()
																.trim()));
									}
								} else {
									if (beanC.getColumnName().equals("STATUS")) {
										if (!beanR.getFiledData().equals("O")) {
											checkInsert = 1;
										}
									}
									if (beanR.getFiledData() == null) {
										list.add(null);
										System.out.println("null");
									} else if (beanR.getFiledData().equals("")) {
										list.add(" ");
										System.out.println(" ");
									} else {
										list.add(beanR.getFiledData().trim());
										System.out.println(beanR.getFiledData()
												.trim());
									}

								}
								break;
							}
						}
					}
					if (sqlFields.startsWith("C0")) {
						sqlFields = sqlFields.substring(3,
								sqlFields.length() - 1);
					} else {
						sqlFields = sqlFields.substring(0,
								sqlFields.length() - 1);
					}
					String[] sqlField = sqlFields.split(",");
					for (int i = 0; i < sqlField.length; i++) {
						sqlQuestion += "?,";
					}
					sqlQuestion = sqlQuestion.substring(0,
							sqlQuestion.length() - 1);
					String sql = "INSERT INTO " + nameTable + "(" + sqlFields
							+ ") VALUES (" + sqlQuestion + ")";
					beanXML.setSql(sql);
					beanXML.setParams(list);
					beanXML.setLevel(level);
					beansXML.add(beanXML);
					if (level == 0) {
						checkRecord++;
					}

				}
			}
			// if(level==0&&table.length>1&&checkRecord!=totalRecord){
			// String error_code="E1:check total records";
			// String error_description= "Please check total records of ticket";
			// setInsert(idConn,ticket_ID,table[level].trim(),error_code,error_description);
			// return null;
			// }
			// }else{
			// String error_code="E1:check total records";
			// String error_description= "Please check total records of ticket";
			// setInsert(idConn,ticket_ID,table[level].trim(),error_code,error_description);
			// return null;
		}
		return beansXML;
	}

	static ArrayList printAttributes(FBeans beansField[], FBeans beansXML,
			int idConn, Connection conn, Node e, String[] table, int level,
			String parentId, String parentKey, ArrayList parentList,
			long ticket_ID, int totalRecord, String[] key) {
		ArrayList list = new ArrayList();
		NodeList nnm;
		String attrname;
		String attrval;
		String recordId = null;
		ArrayList parentListT = new ArrayList();
		nnm = e.getChildNodes();
		if (nnm != null) {
			// //////////// The King add codes
			if (level > 0) {
				for (int k = 0; k < parentList.size(); k++) {
					FRowxml beanT = new FRowxml();
					beanT = (FRowxml) parentList.get(k);
					if (isCommonField(beanT)) {
						list.add(beanT);
					}
				}
				// list=parentList;

				FRowxml beanR = new FRowxml();
				beanR.setFiledName(key[level - 1]);
				beanR.setFiledData(parentId);
				list.add(beanR);
			}
			// ///////////////// End codes
			for (int i = 0; i < nnm.getLength(); i++) {

				Node n = nnm.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					attrname = n.getNodeName();
					if (attrname.equals("SRC_RECORD_ID")) {

						try {
							recordId = n.getChildNodes().item(0).getNodeValue();
							recordId = recordId.trim();
						} catch (Exception ex) {
							recordId = null;
						} finally {
						}
					}
					if (table.length > level + 1
							&& attrname.equals(table[level + 1].trim())) {
						try {
							beansXML = printRow(beansField, beansXML, idConn,
									conn, (Element) n, table, level + 1,
									recordId, parentKey, parentListT,
									ticket_ID, totalRecord, key);
						} catch (Exception e1) {
							setInsert(idConn, ticket_ID, table[level + 1],
									e1.getMessage(), e1.getMessage());
							return null;
						}
					} else {
						try {
							attrval = n.getChildNodes().item(0).getNodeValue();
							attrval = attrval.trim();
						} catch (Exception ex) {
							attrval = null;
						} finally {
						}

						System.out.println("Parent ID " + parentId + ", my ID:"
								+ recordId + ", Level " + level + " : "
								+ attrname + " = " + attrval);
						FRowxml beanR = new FRowxml();
						beanR.setFiledName(attrname);
						Element note = (Element) n;

						if (attrval == null) {
							if (note.getAttribute("null").equals("true")) {
								beanR.setFiledData(null);
							} else {
								beanR.setFiledData("");
							}
						} else {
							beanR.setFiledData(attrval);
						}

						if (attrname.equals(key[level])
								|| attrname.equals("C0")) {
						} else {
							list.add(beanR);
							if (level == 0) {
								if (isCommonField(beanR))
									parentListT.add(beanR);
							}
						}
					}
				}
			}

		}
		return list;
	}

	public static boolean isCommonField(FRowxml bean) {
		String[] commonFields = { "TICKET_ID", "SRC_CONNECT_ID", "STATUS",
				"CREATE_BY", "CREATION_DATE", "UPDATED_BY", "UPDATE_DATE",
				"ACTION", "ERROR_CODE", "ERROR_DESCRIPTION" };
		for (int i = 0; i < commonFields.length; i++) {
			if (bean.getFiledName().equals(commonFields[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean setInsert(long idConn, long idTicket,
			String nameTable, String errorCode, String error_description) {
		// insert to alert table
		BAlertBuffer bo = new BAlertBuffer();
		String sql = "insert into evn_alert_buffer(src_connect_id,ticket_id,table_name,status, detect_time,error_code,error_description) "
				+ "values (?,?,?,?,?,?,?)";
		ArrayList params = new ArrayList();
		params.add(idConn);
		params.add(idTicket);
		params.add(nameTable);
		params.add("E1");
		params.add(DateProc.createTimestamp());
		params.add(errorCode);
		params.add(error_description);
		boolean rerult = false;
		try {
			rerult = bo.insertAlert(params, sql);
		} catch (Exception f) {
			f.printStackTrace();
		}
		// update ticket to error status
		BTicket bTicket = new BTicket();
		try {
			rerult = bTicket.updateStatus(idTicket, "E1");
		} catch (Exception f) {
			f.printStackTrace();
		}
		return rerult;
	}

	public static Vector getAllFiles(String folder, String fileExt) {
		Vector v = new Vector();

		File[] list = new File(folder).listFiles();
		if (list == null) {
			return v;
		}

		for (int i = 0; i < list.length; i++) {
			if (list[i].toString().contains(fileExt)) {
				v.addElement(list[i]);
			}
		}
		return v;
	}

	public static String pushdata(long ticketId, FTicket bean) {
		String rerult = "";
		BTicket bo = new BTicket();
		FTables beanTable = new FTables();
		BTables boTable = new BTables();
		getFmisdata ws = new getFmisdata();
		Vector vFiles = new Vector();
		int totalRecord = 0;
		if (ticketId > 0) {
			try {
				bean = bo.getTicketByID(ticketId);
				if (bean.getDataFile() != null
						&& bean.getDataFile().contains(ticketId + "")) {
					vFiles = getAllFiles(
							IKey.SYSTEM_FILE_XML
									+ bean.getDataFile().substring(
											0,
											bean.getDataFile().indexOf(
													ticketId + ".")),
							bean.getDataFile().substring(
									bean.getDataFile().indexOf(ticketId + ".")));
					BSourceConnectBuffer boSource = new BSourceConnectBuffer();
					FSourceConnectBuffer beanSource = new FSourceConnectBuffer();
					BSourceConnectBuffer boDes = new BSourceConnectBuffer();
					FSourceConnectBuffer beanDes = new FSourceConnectBuffer();
					Connection connDes = null;
					Connection connSrc = null;
					DThread2 dao = new DThread2();

					if (vFiles.size() > 0) {
						checkRecord = 0;
						for (int f = 0; f < vFiles.size(); f++) {
							File file = new File(vFiles.get(f).toString());
							DocumentBuilderFactory dbf = DocumentBuilderFactory
									.newInstance();
							DocumentBuilder db = dbf.newDocumentBuilder();
							Document doc = db.parse(file);
							doc.getDocumentElement().normalize();
							String tables = bean.getTable_name();
							tables.replaceAll(" ", "");
							String[] table = tables.split(",");
							if (table.length < 1)
								return rerult;
							ArrayList parentList = new ArrayList();
							String nameTable = table[0].trim();
							beanTable.setName(nameTable);
							beanTable.setSrc_connect_id(bean
									.getSrc_connect_id());
							try {
								beanTable = boTable.getRecordByName(beanTable);
							} catch (SQLException e) {
								// TODO
								System.out.println("[pushdata]==>"
										+ e.getMessage());
							} catch (EException e) {
								// TODO
								System.out.println("[pushdata]==>"
										+ e.getMessage());
							}
							beanTable.setName(nameTable);
							BThread2 boT = new BThread2();

							if (table.length > 0) {
								FBeans beansField[] = new FBeans[table.length];
								String key[] = new String[table.length];
								for (int i = 0; i < table.length; i++) {
									try {
										beanTable.setName(table[i]);
										beanTable.setSrc_connect_id(bean
												.getSrc_connect_id());
										beanTable = boTable
												.getRecordByName(beanTable);
										key[i] = beanTable.getKey();

										beanSource.setSrc_Connect_Id(beanTable
												.getSrc_connect_id());
										beanSource = boSource
												.getRecordByID(beanSource);

										// beanDes.setSrc_Connect_Id(beanTable
										// .getDes_connect_id());
//										beanDes.setSrc_Connect_Id(beanTable
//												.getSrc_connect_id());
										beanDes.setSrc_Connect_Id(beanTable
												.getDes_connect_id());
										beanDes = boDes.getRecordByID(beanDes);
										beanDes.setNameTable(table[i]);
										beansField[i] = boT
												.getAllColumnsInTable(beanDes);
									} catch (SQLException e) {
										// TODO
										System.out
												.println("[AThread2][printRow]==>"
														+ e.getMessage());
									} catch (EException e) {
										// TODO
										System.out
												.println("[AThread2][printRow]==>"
														+ e.getMessage());
									}
								}

								boolean checkInsert = false;
								try {
									Class.forName(beanDes.getConnection());
									connDes = DriverManager
											.getConnection(beanDes.getUrl());
									DBConnector.startTransaction(connDes);
									FBeans beansXML = new FBeans();
									beansXML = printRow(beansField, beansXML,
											beanDes.getSrc_Connect_Id(),
											connDes, doc.getDocumentElement(),
											table, 0, "", "", parentList,
											ticketId, bean.getTotal_records(),
											key);
									totalRecord = totalRecord + beansXML.size();
									if (beansXML != null && beansXML.size() > 0) {

										if (beansXML.size() <= IKey.RECORDS_COMMIT_TOTAL) {
											if (!dao.insertXuly(connDes,
													beansXML)) {
												for (int i = 0; i < table.length; i++) {
													String sqlT1 = "delete "
															+ table[i]
															+ " where ticket_id=?";
													ArrayList param = new ArrayList();
													param.add(bean
															.getTicket_id());
													dao.insertXuly(connDes,
															sqlT1, param);
												}

											}
										} else {
											if (!dao.insertXuly(connDes,
													beansXML,
													IKey.RECORDS_COMMIT_TOTAL)) {
												for (int i = 0; i < table.length; i++) {
													String sqlT1 = "delete "
															+ table[i]
															+ " where ticket_id=?";
													ArrayList param = new ArrayList();
													param.add(bean
															.getTicket_id());
													dao.insertXuly(connDes,
															sqlT1, param);
												}
											}
										}
										bo.updateStatusInsert(
												bean.getTicket_id(), "C", 1, 1);
										if (beanDes.getType().equals("D")) {
											BAlertBuffer boA = new BAlertBuffer();
											boA.updateStatusByIDTicket(
													bean.getTicket_id(), "C");
										} else {
											ws.readWebsite(beanDes.getUrl()
													+ String.format(
															IKey.WS_FUNCTION_PUSHDATA,
															bean.getTicket_id()
																	+ "", 0, ""));
										}

										String sqlT1 = "update "
												+ table[0]
												+ " set status=? where ticket_id=?";
										ArrayList param = new ArrayList();
										param.add("C");
										param.add(bean.getTicket_id());
										dao.insertXuly(connDes, sqlT1, param);

										try {
											Class.forName(beanSource
													.getConnection());
											connSrc = DriverManager
													.getConnection(beanSource
															.getUrl());
											DBConnector
													.startTransaction(connSrc);
											String sqlSrc = "update "
													+ table[0]
													+ " set status=? where ticket_id=?";
											ArrayList paramSrc = new ArrayList();
											paramSrc.add("C");
											paramSrc.add(bean.getTicket_id());
											dao.insertXuly(connSrc, sqlSrc,
													paramSrc);
											DBConnector.endTransaction(connSrc);
											rerult = "OK";
										} catch (EException ex) {
											System.out.println("[pushdata]==>"
													+ ex.getMessage());
											new AThread2()
													.setInsert(
															beanDes.getSrc_Connect_Id(),
															ticketId,
															bean.getTable_name(),
															ex.getMessage(),
															ex.getMessage());
										} catch (Exception e) {
											// TODO
											System.out.println("[pushdata]==>"
													+ e.getMessage());
											new AThread2()
													.setInsert(
															beanDes.getSrc_Connect_Id(),
															ticketId,
															bean.getTable_name(),
															e.getMessage(),
															e.getMessage());
										} finally {
											DBConnector
													.closeConnection(connSrc);
										}
										checkInsert = true;
									} else {
										DBConnector
												.rollBackTransaction(connDes);
									}
									DBConnector.endTransaction(connDes);
								} catch (EException ex) {
									DBConnector.rollBackTransaction(connDes);
									new AThread2().setInsert(
											beanDes.getSrc_Connect_Id(),
											ticketId, bean.getTable_name(),
											ex.getMessage(), ex.getMessage());
									rerult = ex.getMessage();
									return rerult;
								} catch (Exception e) {
									// TODO
									new AThread2().setInsert(
											beanDes.getSrc_Connect_Id(),
											ticketId, bean.getTable_name(),
											e.getMessage(), e.getMessage());
									rerult = e.getMessage();
									return rerult;
								} finally {
									DBConnector.closeConnection(connDes);
								}
								// update
								if (table.length > 1) {
									try {
										Class.forName(beanDes.getConnection());
										connDes = DriverManager
												.getConnection(beanDes.getUrl());
										DBConnector.startTransaction(connDes);
										if (checkInsert) {
											String sqlUpdate = "UPDATE "
													+ table[1]
													+ " table1 SET status=?, table1."
													+ key[0]
													+ " = (SELECT table2."
													+ key[0]
													+ "                                  FROM "
													+ table[0]
													+ " table2 \n"
													+ "                                  WHERE table2.src_record_id = table1."
													+ key[0]
													+ ")\n"
													+ "WHERE table1.ticket_id=? "
													+ "AND EXISTS (SELECT table2."
													+ key[0]
													+ "\n"
													+ "                                  FROM "
													+ table[0]
													+ " table2 \n"
													+ "                                  WHERE table2.src_record_id = table1."
													+ key[0] + ")";
											ArrayList param = new ArrayList();
											param.add("C");
											param.add(bean.getTicket_id());
											dao.insertXuly(connDes, sqlUpdate,
													param);
										}
										DBConnector.endTransaction(connDes);
										rerult = "OK";
									} catch (EException ex) {
										DBConnector
												.rollBackTransaction(connDes);
										System.out.println("[pushdata]==>"
												+ ex.getMessage());
										new AThread2().setInsert(
												beanDes.getSrc_Connect_Id(),
												ticketId, bean.getTable_name(),
												ex.getMessage(),
												ex.getMessage());
										rerult = ex.getMessage();
										return rerult;
									} catch (Exception e) {
										// TODO
										System.out.println("[pushdata]==>"
												+ e.getMessage());
										new AThread2().setInsert(
												beanDes.getSrc_Connect_Id(),
												ticketId, bean.getTable_name(),
												e.getMessage(), e.getMessage());
										rerult = e.getMessage();
										return rerult;
									} finally {
										DBConnector.closeConnection(connDes);
									}
								}

							}
						}

						// check tong so record
						if (totalRecord != checkRecord) {
							String error_code = "E1:check total records";
							String error_description = "Please check total records of ticket";
							setInsert(beanDes.getSrc_Connect_Id(), ticketId,
									bean.getTable_name(), error_code,
									error_description);
							String sqlT1 = "delete " + bean.getTable_name()
									+ " where ticket_id=?";
							ArrayList param = new ArrayList();
							param.add(bean.getTicket_id());
							dao.insertXuly(connDes, sqlT1, param);

						}

					} else {
						System.out.println("kong co file");
						rerult = "ERROR: File does not exist!";
						new AThread2().setInsert(bean.getSrc_connect_id(),
								ticketId, bean.getTable_name(), rerult, rerult);
					}

				} else {
					rerult = "ERROR: File does not exist!";
					new AThread2().setInsert(bean.getSrc_connect_id(),
							ticketId, bean.getTable_name(), rerult, rerult);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[pushdata]==>" + e.getMessage());
				new AThread2().setInsert(bean.getSrc_connect_id(), ticketId,
						bean.getTable_name(), e.getMessage(), e.getMessage());
				rerult = e.getMessage();
			}

		}
		return rerult;
	}
}
