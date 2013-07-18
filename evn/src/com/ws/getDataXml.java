//package com.ws;
//
//import com.action.evn.AThread2;
//
//import com.inf.DateProc;
//import com.inf.IKey;
//
//import huyenhoc.language;
//
//import java.io.File;
//
//import java.io.FileNotFoundException;
//
//import java.sql.PreparedStatement;
//
//
//
//    import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.StringWriter;
//
//import java.io.UnsupportedEncodingException;
//
//import java.sql.Connection;
//    import java.sql.DriverManager;
//    import java.sql.PreparedStatement;
//    import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//    import java.sql.Statement;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//
//public class getDataXml{
//
//        
//            public static void main(String[] args) {
//                    // TODO Auto-generated method stub
//                   
//            }
//
//
//    public boolean getListCdr(){
//             java.sql.Connection conn = null;
//            PreparedStatement preStmt = null;
//            Statement stmt = null;
//            ResultSet rs = null;
//            String strSQL = null;
//            try {
//                    conn = makeDBConnection();
//                            strSQL="select a.datetimecdr, a.a_number, a.b_number, a.eventid, a.cpid,a.contentid, a.status, a.cost, a.channeltype, a.information FROM cdr_vc a where a.datetimecdr>=20120112120109 and  a.datetimecdr<20120208000938  order by datetimecdr asc";
//                    stmt = conn.createStatement();
//                    rs = stmt.executeQuery(strSQL);
//                    while((rs != null) && (rs.next())){
//
//                            String datetimecdr=rs.getString("datetimecdr");
//                            String a_number=rs.getString("a_number");
//                            String b_number=rs.getString("b_number");
//                            String eventid=rs.getString("eventid");
//                            String cpid=rs.getString("cpid");
//                            String contentid=rs.getString("contentid");
//                            String status=rs.getString("status");
//                            String cost=rs.getString("cost");
//                            String channeltype=rs.getString("channeltype");
//                            String information=rs.getString("information");
//                            String text = datetimecdr+":"+a_number+":"+b_number+":"+eventid+":"+
//                            cpid+":"+contentid+":"+status+":"+cost+":"+channeltype+":"+information+"\r\n";
//                            saveToFile(text,"vccwap_20120208_2.cdr");
//                    }
//
//            } catch (SQLException e) {
//                    System.out.println(e.toString());
//
//            } finally {
//                    
//            }
//            return true;
//    }
//    
//        public static String readDB2Xml(int idConn,String nameDriver,String urlConnection, String nameTable,long id) throws FileNotFoundException, 
//                                                    UnsupportedEncodingException, 
//                                                    IOException {
//          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//          DocumentBuilder builder;
//            Document doc;
//            Element results;
//            Connection con;
//            ResultSet rs=null;
//            ResultSetMetaData rsmd;
//            int colCount;
//            String page="";
//            getFmisdata ws =new getFmisdata();
//            int fileCount=1;
//            try {
//                builder = factory.newDocumentBuilder();
//                doc = builder.newDocument();
//                results = doc.createElement(nameTable);
//                doc.appendChild(results);
//                    try {
//                        Class.forName(nameDriver);
//                        con = DriverManager.getConnection(urlConnection);
//                        rs = con.createStatement().executeQuery("select * from "+nameTable+" where ticket_id="+id+" AND status='O'");
//                        rs.setFetchSize(IKey.FETCH_SIZE);
//                        rs.setFetchDirection(rs.FETCH_REVERSE);
//                        rsmd = rs.getMetaData();
//                        colCount = rsmd.getColumnCount();
//                        int count=0;
//                        while (rs.next()) {
//                            Element row = doc.createElement("ROW");
//                            results.appendChild(row);
//                            for (int i = 1; i <= colCount; i++) {
//                            String columnName = rsmd.getColumnName(i);
//                            Object value = rs.getObject(i);
//                
//                            if((rsmd.getColumnTypeName(i).toLowerCase().indexOf("time")==0)||(rsmd.getColumnTypeName(i).toLowerCase().indexOf("date")==0)){
//                                value = DateProc.getYYYYMMDDHHMMSSString(rs.getTimestamp(i));
//                            }
//                            Element node = doc.createElement(columnName);
//                              if(value==null){
//                                         node.setAttribute("null", "true");
//                                value="";
//                              }
//                                      node.appendChild(doc.createTextNode(language.toOnSign(value.toString())));
//                                      row.appendChild(node);
//                            }
//                            count++;
//                            if(count==10){
//                                getDocumentAsXml(doc);
//                                doc = builder.newDocument();
//                                results = doc.createElement(nameTable);
//                                doc.appendChild(results);
//                                count=0;
//
//                                //log file
//                                 String folder=DateProc.TimestampYYYYMM(DateProc.StringYYYYMMDDHH24MI2Timestamp("201303221111"),IKey.SYSTEM_FILE_SCHIP);
//                                 String mt_log_folder = IKey.SYSTEM_FILE_XML+folder;
//                                   File dir = new File(mt_log_folder);
//                                     if (!dir.exists()) {
//                                         dir.mkdirs();
//                                     }
//                                 String nameFile=id+"."+++fileCount+".xml";
//                                ws.saveToFile(page,mt_log_folder+IKey.SYSTEM_FILE_SCHIP+nameFile);
//                            }
//                        }
//
//                        if(count>0){
//                            page=getDocumentAsXml(doc);
//                            //log file
//                             String folder=DateProc.TimestampYYYYMM(DateProc.StringYYYYMMDDHH24MI2Timestamp("201303221111"),IKey.SYSTEM_FILE_SCHIP);
//                             String mt_log_folder = IKey.SYSTEM_FILE_XML+folder;
//                               File dir = new File(mt_log_folder);
//                                 if (!dir.exists()) {
//                                     dir.mkdirs();
//                                 }
//                             String nameFile=id+"."+++fileCount+".xml";
//                            ws.saveToFile(page,mt_log_folder+IKey.SYSTEM_FILE_SCHIP+nameFile);
//
//                        }
//                        con.close();
//                        rs.close();
//                    } catch (ClassNotFoundException e) {
//                        new AThread2().setInsert(idConn,id,nameTable,e.getMessage(),e.getMessage());
//                    } catch (SQLException e)  {
//                           new AThread2().setInsert(idConn,id,nameTable,e.getMessage(),e.getMessage());
//                    } finally  {
//                    }
//          
//        
//
//            } catch (ParserConfigurationException e) {
//            } catch (TransformerConfigurationException e) {
//            } catch (TransformerException e) {
//                // TODO
//            }
//
//            return page;
//        }
//
//        public static String getDocumentAsXml(Document doc)
//             throws TransformerConfigurationException, TransformerException {
//           DOMSource domSource = new DOMSource(doc);
//           TransformerFactory tf = TransformerFactory.newInstance();
//           Transformer transformer = tf.newTransformer();
//           //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
//           transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//           transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
//           // we want to pretty format the XML output
//           // note : this is broken in jdk1.5 beta!
//           transformer.setOutputProperty
//              ("{http://xml.apache.org/xslt}indent-amount", "4");
//           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//           //
//           java.io.StringWriter sw = new java.io.StringWriter();
//           StreamResult sr = new StreamResult(sw);
//           transformer.transform(domSource, sr);
//           return sw.toString();
//        }
//        }
//    public static void saveToFile(String output, String filename) {
//        try {
//            java.io.DataOutputStream fout = new java.io.DataOutputStream(new
//                    FileOutputStream(filename, true)); // append = true
//            fout.writeBytes(output);
//            fout.flush();
//            fout.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    public static Connection makeDBConnection() throws SQLException
//    {
//            Connection conn = null;
//            try
//            {
//                    Class.forName("oracle.jdbc.driver.OracleDriver");
//                    conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.71:1521:sms", "smsgate", "SMS123Platform321");
//            }
//            catch(ClassNotFoundException ex)
//            {
//                    throw new SQLException(ex.getMessage());
//            }
//            return conn;
//    }
//
//    }