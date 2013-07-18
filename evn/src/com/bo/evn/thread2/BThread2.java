package com.bo.evn.thread2;

import com.exp.EException;
import com.lib.AppConfigs;
import com.dao.connection.DBConnector;
import com.dao.evn.thread2.DThread2;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.FChangeData;
import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;
import com.inf.DateProc;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BThread2
{
    public FBeans getAllColumnsInTable(FSeed seed)throws EException,SQLException{
         final String LOCATION = this + "->getAllColumnsInTable()";
         FBeans result = null;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;
         try {
                  Class.forName(bean.getConnection());
                  conn = DriverManager.getConnection(bean.getUrl());
              DThread2 dao = new DThread2();    
              FChangeData beanImport=new FChangeData();
              beanImport.setNameTable(bean.getNameTable());

             result = dao.getAllColumnsInTable(conn,beanImport);
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (ClassNotFoundException e) {
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     } 
     
    public String[][] getAllRecord(FSeed seed)throws EException,SQLException{
         final String LOCATION = this + "->getAllRecord()";
         String[][] result = null;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;
         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());
              DThread2 dao = new DThread2();    
              FChangeData beanImport=new FChangeData();
              beanImport.setNameTable(bean.getNameTable());
              beanImport.setPageIndex(bean.getPageIndex());
              beanImport.setNameSQL(bean.getNameSQL());

             result = dao.getAllRecord(conn,beanImport);
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (ClassNotFoundException e) {
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     } 
    public String[][] getAllRecord(FSeed seed,FSeed seed1)throws EException,SQLException{
         final String LOCATION = this + "->getAllRecord()";
         String[][] result = null;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;
         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());
              DThread2 dao = new DThread2();    
              FChangeData beanImport =(FChangeData)seed1;
//              FChangeData beanImport=new FChangeData();
              beanImport.setNameTable(bean.getNameTable());
              beanImport.setPageIndex(bean.getPageIndex());
              beanImport.setNameSQL(bean.getNameSQL());

             result = dao.getAllRecord(conn,beanImport);
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (ClassNotFoundException e) {
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     } 

    public boolean insert(FSeed seed,FBeans columns, String[][] mang)throws EException,SQLException, 
                                             ClassNotFoundException {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;
         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());
            
             DBConnector.startTransaction(conn);
             DThread2 dao = new DThread2();          
                result = dao.insert(conn,seed,columns,mang);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }

    public boolean insertXuly(FSeed seed,String nameFile,String nameTable)throws EException,SQLException, 
                                             ClassNotFoundException {
         final String LOCATION = this + "->insertXuly()";
         boolean result = false;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;
         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());
            
             DBConnector.startTransaction(conn);
              File file = new File(nameFile);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                String tables = nameTable;
                tables.replaceAll(" ","");
                String[] table = tables.split(",");
                if(table.length<1) result = false;
                printRow(conn,doc.getDocumentElement(),table,0,"");
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (Exception e) {
          System.out.println(e.getMessage());
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
   public static void printRow(Connection cnn,Element doc,String[] table, int level, String parentId)throws EException,SQLException{
        NodeList nodeLst = doc.getElementsByTagName("ROW");
        DThread2 dao=new DThread2();
        for (int s = 0; s < nodeLst.getLength(); s++) {
          ArrayList listT=new ArrayList();
          Node fstNode = nodeLst.item(s);
          if(fstNode.getParentNode().getNodeName().equals(table[level])){
           System.out.println("Information of ROW " + (s+1)+ ":" );
              String sqlFields="";
              String sqlQuestion="";
              String nameTable="";
              nameTable=table[level];   
              FChangeData beanT=new FChangeData();
             beanT.setNameTable(table[level]);
             FBeans beans=new FBeans();
             beans=dao.getAllColumnsInTable(cnn,beanT);
              listT=printAttributes(cnn,fstNode,table,level,parentId,beans);
              sqlFields=sqlFields.substring(3,sqlFields.length()-1);
                    for (int i=0;i<beans.size() ;i++ ) {
                        beanT=new FChangeData();
                        beanT=(FChangeData)beans.get(i);
                        sqlQuestion+="?,";
                        sqlFields+=beanT.getColumnName()+",";
                    }
                    sqlQuestion=sqlQuestion.substring(0,sqlQuestion.length()-1);
                    sqlFields=sqlFields.substring(0,sqlFields.length()-1);
              String sql= "INSERT INTO " +nameTable + "("+sqlFields+") VALUES ("+sqlQuestion+")";
              System.out.println(sql);
              dao.insertXuly(cnn,sql,listT);
          }
        }
    }
   public static ArrayList printAttributes(Connection cnn, Node e, String[] table,int level,String parentId,FBeans beans)throws EException,SQLException
       {
           ArrayList params = new ArrayList();
            NodeList nnm;
            String attrname;
            String attrval;
            String recordId=null;
           String sqlFields="";
            nnm = e.getChildNodes();
               if (nnm != null)
               {
                  for (int i=0; i<nnm.getLength(); i++)
                  {
                     Node n = nnm.item(i);                 
                     if(n.getNodeType() == Node.ELEMENT_NODE){
                           attrname = n.getNodeName();
                           if(attrname.equals("SRC_RECORD_ID")){
                               try{
                                   recordId =n.getChildNodes().item(0).getNodeValue(); 
                               } catch (Exception e1) {
                                   recordId="";
                               }
                           }
                       if(table.length>level+1 && attrname.equals(table[level+1])){
                        printRow(cnn,(Element)n,table,level+1,recordId);
                       }else{
                       try{
                           attrval =n.getChildNodes().item(0).getNodeValue(); 
                       } catch (Exception e1) {
                           attrval="";
                       }
                              System.out.println("Parent ID " + parentId + ", my ID:" + recordId + ", Level " + level + " : " + attrname + " = " + attrval);
                            sqlFields+=attrname+",";

                             for (int j=0;j<beans.size();j++ ) {
                                 FChangeData beanT=new FChangeData();
                                 beanT=(FChangeData)beans.get(j);
                                 if(beanT.getColumnName().equals(attrname)){
                                     if(beanT.getColumnTypeName().equals("1")){
                                        params.add(Integer.parseInt(attrval));
                                     }else if(beanT.getColumnTypeName().equals("4")){
                                        params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(attrval));
                                     }else{
                                        params.add(attrval);
                                     }
                                     break;
                                 }
                             }
                       }
                     }
                  }
                  System.out.println(sqlFields);
        }
            return params;
       }
   
    public boolean insertCommon(FSeed seed,String cnn,String url)throws EException,SQLException, ClassNotFoundException {
         final String LOCATION = this + "->insertCommon()";
         boolean result = false;
         Connection conn = null;
         try {
              Class.forName(cnn);
              conn = DriverManager.getConnection(url);
            
             DBConnector.startTransaction(conn);
             DThread2 dao = new DThread2();          
                result = dao.insertCommon(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
     
    public boolean updateCommon(FSeed seed,String cnn,String url)throws EException,SQLException, ClassNotFoundException {
         final String LOCATION = this + "->insertCommon()";
         boolean result = false;
         Connection conn = null;
         try {
              Class.forName(cnn);
              conn = DriverManager.getConnection(url);
              DBConnector.startTransaction(conn);
              DThread2 dao = new DThread2();          
                result = dao.updateCommon(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public FBeans getPaging(FSeed seed)throws EException,SQLException{
         final String LOCATION = this + "->getPaging()";
         FBeans result = null;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;

         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());


               DBConnector.startTransaction(conn);
             DThread2 dao = new DThread2();   
              FChangeData beanImport=new FChangeData();
              beanImport.setNameTable(bean.getNameTable());
              beanImport.setPageIndex(bean.getPageIndex());
             result = dao.getPaging(conn,beanImport);
                 DBConnector.endTransaction(conn);
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (ClassNotFoundException e) {
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     } 
    public FBeans getPaging(FSeed seed,FSeed seed1)throws EException,SQLException{
         final String LOCATION = this + "->getPaging()";
         FBeans result = null;
         Connection conn = null;
         FSourceConnectBuffer bean =(FSourceConnectBuffer)seed;

         try {
              Class.forName(bean.getConnection());
              conn = DriverManager.getConnection(bean.getUrl());


               DBConnector.startTransaction(conn);
             DThread2 dao = new DThread2();   
              FChangeData beanImport=(FChangeData)seed1;
              beanImport.setNameTable(bean.getNameTable());
              beanImport.setPageIndex(bean.getPageIndex());
             result = dao.getPaging(conn,beanImport);
                 DBConnector.endTransaction(conn);
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } catch (ClassNotFoundException e) {
            // TODO
        } finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }   
}
