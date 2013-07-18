//package com.action.evn;
//
//import com.action.evn.alertBuffer.AAlertBuffer;
//
//import com.bo.evn.alertBuffer.BAlertBuffer;
//import com.bo.evn.sourceConnectBuffer.BSourceConnectBuffer;
//import com.bo.evn.tables.BTables;
//import com.bo.evn.thread2.BThread2;
//import com.bo.evn.ticket.BTicket;
//
//import com.dao.connection.DBConnector;
//import com.dao.evn.reportBuffer.DReportBuffer;
//
//import com.dao.evn.thread2.DThread2;
//
//import com.exp.EException;
//import com.form.FBeans;
//import com.form.FSeed;
//import com.form.evn.FChangeData;
//import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
//import com.form.evn.tables.FTables;
//import com.form.evn.ticket.FTicket;
//
//import com.inf.DateProc;
//import com.inf.IKey;
//
//import com.inf.Queue;
//
//import com.lib.AppConfigs;
//
//import com.ws.FRowxml;
//
//import com.ws.getFmisdata;
//
//import huyenhoc.language;
//
//import java.io.File;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import java.util.ArrayList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//
//public class AThread2 extends  Thread {
//    private BTicket bo =new BTicket();
//    BTables boTable =new BTables();
//    FTables beanTable=new FTables();
//    getFmisdata ws =new getFmisdata();
//    private static Queue dataQueue=null;
//    
//    public AThread2(){
//        dataQueue=IKey.getDataQueue();
//    }
//    public void destroy()
//    {
//        IKey.removeThread(this);
//    }
//    public void run() 
//    {
//   
//        while(true) 
//        {
//                System.out.println("---------------runing thread2-----------------");
//            String nameTable="";
//            int idConn=0;
//            try {
//                    FTicket bean=(FTicket)IKey.getDataQueue().dequeue();
//                    if(bean.getTicket_id()>0){
//                        try {
//                            bean=bo.getTicketByID(bean.getTicket_id());
//                               if(bean.getDataFile().contains(".xml")){
//                                   File file = new File(IKey.SYSTEM_FILE_XML+bean.getDataFile());
//                                     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//                                     DocumentBuilder db = dbf.newDocumentBuilder();
//                                     Document doc = db.parse(file);
//                                     doc.getDocumentElement().normalize();
//                                     String tables =bean.getTable_name();
//                                     tables.replaceAll(" ","");
//                                     String[] table = tables.split(",");
//                                     if(table.length<1) return;
//                                   ArrayList parentList=new ArrayList();
//                                   nameTable=table[0].trim();
//                                   beanTable.setName(nameTable);
//                                   beanTable=boTable.getRecordByName(beanTable);
//
//                                          BSourceConnectBuffer boSource =new BSourceConnectBuffer();
//                                          FSourceConnectBuffer beanSource=new FSourceConnectBuffer();
//                                          beanTable.setName(nameTable);
//                                            BThread2 boT=new BThread2();
//                                          FBeans beans=new FBeans();
//                                            try {
//                                                beanTable=boTable.getRecordByName(beanTable);
//                                                beanSource.setSrc_Connect_Id(beanTable.getDes_connect_id());
//                                                idConn=beanTable.getDes_connect_id();
//                                                beanSource=boSource.getRecordByID(beanSource);
//                                                beanSource.setNameTable(nameTable);
//                                                beans=boT.getAllColumnsInTable(beanSource);
//                                            } catch (SQLException e) {
//                                                System.out.println("[AThread2]==>"+e.getMessage());
//                                                setInsert(idConn,bean.getTicket_id(),nameTable,e.getMessage(),e.getMessage());
//                                            } catch (EException e) {
//                                                System.out.println("[AThread2]==>"+e.getMessage());
//                                                setInsert(idConn,bean.getTicket_id(),nameTable,e.getMessage(),e.getMessage());
//
//                                            }
//                                                  Connection conn = null;
//                                                  boolean checkInsert=false;
//                                                  try {
//                                                       Class.forName(beanSource.getConnection());
//                                                       conn = DriverManager.getConnection(beanSource.getUrl());
//                                                       DBConnector.startTransaction(conn);
//                                                       if(printRow(idConn,conn,doc.getDocumentElement(),table,0,"",beanTable.getKey(), parentList,bean.getTicket_id(),bean.getTotal_records())){
//                                                           bo.updateStatusInsert(bean.getTicket_id(),"C",1,1);
//                                                           if(beanSource.getType().equals("D")){
//                                                               BAlertBuffer boA=new BAlertBuffer();
//                                                               boA.updateStatusByIDTicket(bean.getTicket_id(),"C");
//                                                           }else{
//                                                               ws.readWebsite(beanSource.getUrl()+String.format(IKey.WS_FUNCTION_PUSHDATA, bean.getTicket_id()+"",0,""));
//                                                           }
//                                                           DThread2 dao =new DThread2();
//                                                              String sqlT1="update "+table[0]+" set status='C' where ticket_id="+bean.getTicket_id();
//                                                           dao.insertXuly(conn,sqlT1,null);
//
//                                                           checkInsert=true;
//                                                       }else{
//                                                           DBConnector.rollBackTransaction(conn);
//                                                        }
//                                                      DBConnector.endTransaction(conn);            
//                                                   }
//                                                   catch (EException ex) {
//                                                      DBConnector.rollBackTransaction(conn);
//                                                       System.out.println("[AThread2]==>"+ex.getMessage());
//                                                       setInsert(idConn,bean.getTicket_id(),nameTable,ex.getMessage(),ex.getMessage());
//                                                   } catch (Exception e) {
//                                                      System.out.println("[AThread2]==>"+e.getMessage());
//                                                      setInsert(idConn,bean.getTicket_id(),nameTable,e.getMessage(),e.getMessage());
//                                                  } finally {
//                                                      DBConnector.closeConnection(conn);
//                                                  }
//                                                  
//                                                  //update
//                                                  if(table.length>1){
//                                                      try {
//                                                           Class.forName(beanSource.getConnection());
//                                                           conn = DriverManager.getConnection(beanSource.getUrl());
//                                                           DBConnector.startTransaction(conn);
//                                                           if(checkInsert){
//                                                               DThread2 dao =new DThread2();
//                                                                  String sqlUpdate="UPDATE "+table[1]+" table1 SET ststus='C', table1."+beanTable.getKey()+" = (SELECT table2.ar_transaction_id\n" + 
//                                                                  "                                  FROM "+table[0]+" table2 \n" + 
//                                                                  "                                  WHERE table2.src_record_id = table1."+beanTable.getKey()+")\n" + 
//                                                                  "WHERE table1.ticket_id="+bean.getTicket_id()+ 
//                                                                  "AND EXISTS (SELECT table2."+beanTable.getKey()+"\n" + 
//                                                                  "                                  FROM "+table[0]+" table2 \n" + 
//                                                                  "                                  WHERE table2.src_record_id = table1."+beanTable.getKey()+")";
//                                                               dao.insertXuly(conn,sqlUpdate,null);
//                                                            }
//                                                          DBConnector.endTransaction(conn);            
//                                                       }
//                                                       catch (EException ex) {
//                                                          DBConnector.rollBackTransaction(conn);
//                                                           System.out.println("[AThread2]==>"+ex.getMessage());
//                                                           setInsert(idConn,bean.getTicket_id(),nameTable,ex.getMessage(),ex.getMessage());
//                                                       } catch (Exception e) {
//                                                          System.out.println("[AThread2]==>"+e.getMessage());
//                                                          setInsert(idConn,bean.getTicket_id(),nameTable,e.getMessage(),e.getMessage());
//                                                      } finally {
//                                                          DBConnector.closeConnection(conn);
//                                                      }
//                                                  }
//
//                                                  
//                               }
//                        }catch (Exception e) {
//                             // TODO Auto-generated catch block
//                            System.out.println("[AThread2]==>"+e.getMessage());
//                                 setInsert(idConn,bean.getTicket_id(),nameTable,e.getMessage(),e.getMessage());
//                        }
//                    }
//                } catch (EException e) {
//                    System.out.println("[AThread2]==>"+e.getMessage());
//                    // TODO
//                } 
//                 if(IKey.getThread2()==0){
//                     this.interrupt();
//                 System.out.println("Stop thread2");
//                 }
//            try {
//                    sleep(5*1000);
//            } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//            }
//        }
//       
//    }
//   public static boolean printRow(int idConn,Connection conn,Element doc,String[] table, int level, String parentId,String parentKey,ArrayList parentList,long ticket_ID,int totalRecord){
//    boolean result=false;
//    int checkRecord=0;
//       NodeList nodeLst = doc.getElementsByTagName("ROW");
//       if(level>0)totalRecord=0;
//       if(nodeLst.getLength()>0&&(totalRecord==nodeLst.getLength()||totalRecord==0||table.length>1)){
//           for (int s = 0; s < nodeLst.getLength(); s++) {
//             Node fstNode = nodeLst.item(s);
//             if(fstNode.getParentNode().getNodeName().equals(table[level])){
//              System.out.println("Information of ROW " + (s+1)+ ":" );
//                 String sqlFields="";
//                         String sqlQuestion="";
//                         String nameTable=table[level].trim();
//                 BTables boTable =new BTables();
//                 FTables beanTable=new FTables();
//                             //////// The King adds codes
//                             FTables beanParentTable = new FTables();
//                    if(level>0){
//                                    beanParentTable.setName(table[level-1].trim());
//                        try {
//                            beanParentTable = boTable.getRecordByName(beanParentTable);
//                        } catch (SQLException e) {
//                            // TODO
//                             System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                        } catch (EException e) {
//                            // TODO
//                             System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                        }
//                    }
//                             //////// End codes
//                 BSourceConnectBuffer boSource =new BSourceConnectBuffer();
//                 FSourceConnectBuffer beanSource=new FSourceConnectBuffer();
//                 beanTable.setName(nameTable);
//                   BThread2 boT=new BThread2();
//                 FBeans beans=new FBeans();
//                   try {
//                       beanTable=boTable.getRecordByName(beanTable);
//                       beanSource.setSrc_Connect_Id(beanTable.getDes_connect_id());
//                       beanSource=boSource.getRecordByID(beanSource);
//                       beanSource.setNameTable(nameTable);
//                       beans=boT.getAllColumnsInTable(beanSource);
//                   } catch (SQLException e) {
//                       // TODO
//                        System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                   } catch (EException e) {
//                       // TODO
//                        System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                   }
//                 ArrayList listR =new ArrayList();
//                             //////////// The King adds codes
//                             try{
//                                 listR=printAttributes(idConn,conn,fstNode,table,level,parentId,parentKey,parentList,ticket_ID,totalRecord);
//                                 if (listR==null)return false;
//                             } catch (Exception e) {
//                                 setInsert(idConn,ticket_ID,table[level],e.getMessage(),e.getMessage());
//                                 return false;
//                             }
//                 
//                             /////////// End codes
//                 FRowxml beanR=new FRowxml();
//                 ArrayList list =new ArrayList();
//                 long ticketID_Temp=0;
//                 int checkInsert=0;
//                    for (int r=0;r<listR.size() ;r++ ) {
//                        beanR=(FRowxml)listR.get(r);
//        //                    if(level==0){
//                            if(beanTable.getKey().equals(beanR.getFiledName())){
//                            }else{
//                                sqlFields+=beanR.getFiledName()+",";
//                                System.out.println(beanR.getFiledName());
//                            }
//        //                    }
//                        for (int t=0;t<beans.size() ;t++ ) {
//                            FChangeData beanC=new FChangeData();
//                            beanC=(FChangeData)beans.get(t);
//                            if(beanC.getColumnName().equals(beanR.getFiledName())){
//                                if(beanC.getColumnTypeName().equals("1")){
//
//                                    if(beanC.getColumnName().equals("TICKET_ID")){
//                                        ticketID_Temp=Long.parseLong(beanR.getFiledData());
//                                        if(ticket_ID!=ticketID_Temp){
//                                            setInsert(beanTable.getDes_connect_id(),ticket_ID,nameTable,"record has wrong ticket id","record has wrong ticket id");
//                                            return false;
//                                        }
//                                    }
//                                    if(beanR.getFiledData()==null){
//                                        list.add(null);
//                                        System.out.println("null");
//                                    }else{
//                                        if(beanR.getFiledData().contains(".")){
//                                            list.add(Float.parseFloat(beanR.getFiledData().trim()));
//                                            System.out.println(beanR.getFiledData().trim());
//                                            
//                                        }else{
//                                            list.add(Long.parseLong(beanR.getFiledData().trim()));
//                                            System.out.println(beanR.getFiledData().trim());
//                                        }
//
//                                    }
//
//                                    
//
//                                }else if(beanC.getColumnTypeName().equals("4")){
//                                    if(beanR.getFiledData()==null||beanR.getFiledData().equals("")||beanR.getFiledData().length()<8){
//                                        list.add(null);
//                                        System.out.println("null");
//                                    }else{
//                                        list.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(beanR.getFiledData().trim()));
//// list.add(null);
//                                        System.out.println(DateProc.StringYYYYMMDDHH24MI2Timestamp(beanR.getFiledData().trim()));
//                                    }
//                                }else{
//                                    if(beanC.getColumnName().equals("STATUS")){
//                                        if(!beanR.getFiledData().equals("O")){
//                                            checkInsert=1;
//                                        }
//                                    }
//                                    if(beanR.getFiledData()==null){
//                                        list.add(null);
//                                        System.out.println("null");
//                                    }else{
//                                        list.add(beanR.getFiledData().trim());
//                                        System.out.println(beanR.getFiledData().trim());
//                                    }
//
//                                }
//                                break;
//                            }
//                        }
//                    }
//                    if(sqlFields.startsWith("C0")){
//                    sqlFields=sqlFields.substring(3,sqlFields.length()-1);
//                 }else{
//                     sqlFields=sqlFields.substring(0,sqlFields.length()-1);
//                 }
//                 String[] sqlField = sqlFields.split(",");
//                       for (int i=0;i<sqlField.length ;i++ ) {
//                           sqlQuestion+="?,";
//                       }
//                       sqlQuestion=sqlQuestion.substring(0,sqlQuestion.length()-1);
//                 String sql = "INSERT INTO " +nameTable + "("+sqlFields+") VALUES ("+sqlQuestion+")";
//                 DThread2 dao =new DThread2();
//                    try {
//                    if(checkInsert==0){
//                    System.out.println(sql);
//                        result=dao.insertXuly(conn,sql,list);
//                        if(level==0){
//                            checkRecord++;
//                        }
//                        if(!result) return false;
//                    }
//                    } catch (SQLException e) {
//                        System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                        setInsert(beanTable.getDes_connect_id(),ticket_ID,nameTable,e.getMessage(),e.getMessage());
//                        return false;
//    
//                    } catch (EException e) {
//                        // TODO
//                         System.out.println("[AThread2][printRow]==>"+e.getMessage());
//                        setInsert(beanTable.getDes_connect_id(),ticket_ID,nameTable,e.getMessage(),e.getMessage());
//                        return false;
//                    }
//             }
//           }
//           if(level==0&&table.length>1&&checkRecord!=totalRecord){
//               String error_code="E1:check total records";
//               String error_description= "Please check total records of ticket";
//               setInsert(idConn,ticket_ID,table[level].trim(),error_code,error_description);
//               return false;
//           }
//    }else{
//        String error_code="E1:check total records";
//        String error_description= "Please check total records of ticket";
//        setInsert(idConn,ticket_ID,table[level].trim(),error_code,error_description);
//        return false;
//    }
//       return result;
//    }
//
//    static ArrayList printAttributes(int idConn,Connection conn,Node e, String[] table,int level,String parentId,String parentKey,ArrayList parentList,long ticket_ID,int totalRecord)
//       {
//           ArrayList list=new ArrayList();
//          NodeList nnm;
//          String attrname;
//          String attrval;
//          String recordId=null;
//           ArrayList parentListT =new ArrayList();
//          nnm = e.getChildNodes();
//             if (nnm != null)
//             {
//                         ////////////// The King add codes
//                            if(level>0){
//                                for (int k=0 ;k<parentList.size() ;k++ )  {
//                                    FRowxml beanT=new FRowxml();
//                                    beanT=(FRowxml)parentList.get(k);
//                                    if(isCommonField(beanT)){
//                                        list.add(beanT);
//                                    }
//                                }
////                                list=parentList;
//                                
//                                        FRowxml beanR=new FRowxml();
//                                        beanR.setFiledName(parentKey);
//                                        beanR.setFiledData(parentId);
//                                        list.add(beanR);
//                            }
//                        /////////////////// End codes
//                for (int i=0; i<nnm.getLength(); i++)
//                {
//
//                   Node n = nnm.item(i);    
//                   if(n.getNodeType() == Node.ELEMENT_NODE){
//                         attrname = n.getNodeName();
//                         if(attrname.equals("SRC_RECORD_ID")){
//                           recordId = n.getChildNodes().item(0).getNodeValue();                       
//                         }
//                     if(table.length>level+1 && attrname.equals(table[level+1].trim())){
//                         try{
//                            printRow(idConn,conn,(Element)n,table,level+1,recordId,parentKey,parentListT,ticket_ID,totalRecord);
//                         } catch (Exception e1) {
//                             setInsert(idConn,ticket_ID,table[level+1],e1.getMessage(),e1.getMessage());
//                             return null;
//                         }
//                     }else{
//                     try  {
//                         attrval =language.toOnSign(n.getChildNodes().item(0).getNodeValue());                  
//                     } catch (Exception ex)  {
//                         attrval = null;                  
//                     } finally  {
//                     }
//                     
//                      System.out.println("Parent ID " + parentId + ", my ID:" + recordId + ", Level " + level + " : " + attrname + " = " + attrval.trim());
//                      FRowxml beanR=new FRowxml();
//                         beanR.setFiledName(attrname);
//                         Element note=(Element)n;
//                         if(attrval.trim().equals("")&&note.getAttribute("null")!=null){
//                             beanR.setFiledData(null);
//                         }else{
//                             beanR.setFiledData(attrval.trim());
//                         } 
//                         
//                         if(attrname.equals(parentKey)||attrname.equals("C0")){
//                         }else{
//                             list.add(beanR);  
//                             if(level==0){
//                                 if(isCommonField(beanR)) parentListT.add(beanR);
//                             }
//                         }
//                     }
//                   }
//                }
//
//           }
//           return list;
//       }
//    public static boolean isCommonField(FRowxml bean){
//      String[] commonFields = {"TICKET_ID","SRC_CONNECT_ID","STATUS","CREATE_BY","CREATION_DATE","UPDATED_BY","UPDATE_DATE","ACTION","ERROR_CODE","ERROR_DESCRIPTION"};
//       for(int i=0;i<commonFields.length;i++){
//        if(bean.getFiledName().equals(commonFields[i])){
//         return true;
//        }
//       }
//       return false;
//      }
//    public static boolean setInsert(long idConn,long idTicket,String nameTable,String errorCode,String error_description){   
//     BAlertBuffer bo= new BAlertBuffer();
//     String sql="insert into evn_alert_buffer(src_connect_id,ticket_id,table_name,status, detect_time,error_code,error_description) " +
//     "values (?,?,?,?,?,?,?)";
//     ArrayList params = new ArrayList();
//     params.add(idConn);
//     params.add(idTicket);
//     params.add(nameTable);
//     params.add("E1");
//     params.add(DateProc.createTimestamp());
//     params.add(errorCode);
//     params.add(error_description);
//     boolean rerult=false;
//     try {
//         rerult=bo.insertAlert(params,sql);
//     } catch (Exception f) {
//         // TODO
//     }
//     return rerult;
//    }
//}
