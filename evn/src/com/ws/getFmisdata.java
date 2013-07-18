package com.ws;

import com.action.evn.AThread2;

import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
import com.bo.evn.tables.BTables;
import com.bo.evn.ticket.BTicket;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.form.evn.tables.FTables;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import com.inf.IKey;
import huyenhoc.language;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.io.Writer;

import java.lang.reflect.Method;

import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

public class getFmisdata extends HttpServlet {

	public static String invoke(String aClass, String aMethod, Class[] params,
			Object[] args) throws Exception {

		Class c = Class.forName(aClass);
		Method m = c.getDeclaredMethod(aMethod, params);
		Object i = c.newInstance();
		Object r = m.invoke(i, args);
		return r.toString();
	}

	public static int getNumber(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (Exception ex) {
			result = 0;
		} finally {
		}

		return result;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reading the user input
		String page = "<?xml version=\"1.0\"?><getfmisdata>ERROR</getfmisdata>";
		BTicket boT = new BTicket();
		FTicket bean = new FTicket();
		BTables boTable = new BTables();
		FTables beanTable = new FTables();
		BSourceConnectBuffer boSource = new BSourceConnectBuffer();
		FSourceConnectBuffer beanSource = new FSourceConnectBuffer();
		long ticketId = getNumber(request.getParameter("ticketid"));
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
				if (beanSource.getType().equals("D")) {

					// thuc hien read db
					Long curTimeInMinute = new Long(System.currentTimeMillis());
					page = readDB2Xml(beanTable.getSrc_connect_id(),
							beanSource.getConnection(), beanSource.getUrl(),
							bean.getTable_name(), ticketId, curTimeInMinute,
							bean.getEvn_time(),bean.getTotal_records());
					String folder = DateProc.TimestampYYYYMM(
							DateProc.StringYYYYMMDDHH24MI2Timestamp(bean
									.getEvn_time()), IKey.SYSTEM_FILE_SCHIP)
							+ IKey.SYSTEM_FILE_SCHIP + ticketId;
					String nameFile = ticketId + "." + curTimeInMinute;
					boT.update_ticket_rasoat(bean.getTicket_id(), folder
							+ IKey.SYSTEM_FILE_SCHIP + nameFile);
				}
			} catch (Exception e) {
				new AThread2().setInsert(beanTable.getSrc_connect_id(),
						ticketId, null, e.getMessage(), e.getMessage());
			}
		}

		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println(page);

	}

	public static String readDB2Xml(int idConn, String nameDriver,
			String urlConnection, String nameTable, long id,
			Long curTimeInMinute, String datetime, int totalRecords) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc;
		Element results;
		Connection con;
		ResultSet rs = null;
		ResultSetMetaData rsmd;
		PreparedStatement prstm = null;
		int recordCount;
		int colCount;
		String page = IKey.getValue("ERROR.TOTAL.RECORD.ERROR");
		int fileCount = 0;
		int count = 0;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			results = doc.createElement(nameTable);
			doc.appendChild(results);
			try {
				Class.forName(nameDriver);
				con = DriverManager.getConnection(urlConnection);
				// check total record
				rs = con.createStatement().executeQuery(
						"select count(*) from " + nameTable
								+ " where ticket_id=" + id + "AND status ='O'");
				while (rs.next()) {
					recordCount = rs.getInt(1);
					if (recordCount != totalRecords) {
						page = IKey.getValue("ERROR.WRONG.NUMBER.OF.RECORD");
						new AThread2().setInsert(idConn, id, nameTable, page,
								page);
						return page;
					}
				}
				rs = con.createStatement().executeQuery(
						"select * from " + nameTable + " where ticket_id=" + id
								+ " AND status='O'");
				rs.setFetchSize(IKey.FETCH_SIZE);
				// rs.setFetchDirection(rs.FETCH_FORWARD);
				rsmd = rs.getMetaData();
				colCount = rsmd.getColumnCount();
				// log file
				String folder = DateProc.TimestampYYYYMM(
						DateProc.StringYYYYMMDDHH24MI2Timestamp(datetime),
						IKey.SYSTEM_FILE_SCHIP);
				String mt_log_folder = IKey.SYSTEM_FILE_XML + folder
						+ IKey.SYSTEM_FILE_SCHIP + id;
				File dir = new File(mt_log_folder);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				while (rs.next()) {
					Element row = doc.createElement("ROW");
					results.appendChild(row);
					for (int i = 1; i <= colCount; i++) {
						String columnName = rsmd.getColumnName(i);
						Object value = rs.getObject(i);

						if ((rsmd.getColumnTypeName(i).toLowerCase()
								.indexOf("time") == 0)
								|| (rsmd.getColumnTypeName(i).toLowerCase()
										.indexOf("date") == 0)) {
							value = DateProc.getYYYYMMDDHHMMSSString(rs
									.getTimestamp(i));
						}
						Element node = doc.createElement(columnName);
						if (value == null) {
							node.setAttribute("null", "true");
							value = "";
						}
						node.appendChild(doc.createTextNode(language
								.toOnSign(value.toString())));
						row.appendChild(node);
					}
					count++;
					if (count == IKey.RECORDS_COMMIT_TOTAL) {
						++fileCount;
						String nameFile = id + "." + curTimeInMinute + "."
								+ fileCount + ".xml";
						getDocumentAsXml(doc, mt_log_folder
								+ IKey.SYSTEM_FILE_SCHIP + nameFile);
						doc = builder.newDocument();
						results = doc.createElement(nameTable);
						doc.appendChild(results);
						count = 0;
						page = "1";
					}
				}

				if (count > 0) {
					++fileCount;
					String nameFile = id + "." + curTimeInMinute + "."
							+ fileCount + ".xml";
					getDocumentAsXml(doc, mt_log_folder
							+ IKey.SYSTEM_FILE_SCHIP + nameFile);
					page = "1";
				}
				if (rs == null) {
					page = IKey.getValue("ERROR.TOTAL.RECORD.ERROR");
					new AThread2().setInsert(idConn, id, nameTable, page, page);
				}
				con.close();
				rs.close();
			} catch (ClassNotFoundException e) {
				new AThread2().setInsert(idConn, id, nameTable, e.getMessage(),
						e.getMessage());
				page = e.getMessage();
			} catch (SQLException e) {
				new AThread2().setInsert(idConn, id, nameTable, e.getMessage(),
						e.getMessage());
				page = e.getMessage();
			} finally {
			}

		} catch (ParserConfigurationException e) {
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e) {
			// TODO
		}
		if (page.equals(IKey.getValue("ERROR.TOTAL.RECORD.ERROR"))) {
			new AThread2().setInsert(idConn, id, nameTable, page, page);

		}
		return page;
	}

	public static void getDocumentAsXml(Document doc, String nameFile)
			throws TransformerConfigurationException, TransformerException {
		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
		// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		// we want to pretty format the XML output
		// note : this is broken in jdk1.5 beta!
		// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
		// "4");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		//
		java.io.StringWriter sw = new java.io.StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);
		saveToFile(sw.toString(), nameFile);
	}

	public static String readWebsite(String url) {
		String page = "";
		try {
			url = url.replaceAll(" ", "%20");
			URL website = new URL(url);
			System.out.println("Connect to web:" + url);
			URLConnection ws = website.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ws.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				page += inputLine + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Khong truy cap duoc url:" + url);
		}
		return page;
	}

	public static String readFilexml(String url) {
		String page = "";
		try {
			FileInputStream fstream = new FileInputStream(url);
			DataInputStream ws = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(ws));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				page += inputLine + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Khong truy cap duoc xml:" + url);
		}
		return page;
	}

	public static void saveToFile(String output, String filename) {

		try {
			FileOutputStream fos;
			fos = new FileOutputStream(filename);
			OutputStreamWriter osw;
			osw = new OutputStreamWriter(fos, "UTF-8");
			Writer out = new BufferedWriter(osw);
			out.write(output);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO
		} catch (UnsupportedEncodingException e) {
			// TODO
		} catch (IOException e) {
			// TODO
		}

	}
}
