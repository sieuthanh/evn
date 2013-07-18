package com.dao.admin.departments;


import com.dao.DSqlAdmin;


import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.departments.FDepartment;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DDepartments  extends DSqlAdmin
{
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FDepartment bean = (FDepartment)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_DEPARTMENTS_INFORMATION);         
          pstmt.setString(PARAM_01,bean.getCode());
          pstmt.setInt(PARAM_02,bean.getId());
          rs = pstmt.executeQuery();
          result = rs!=null && rs.next();
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
  
    public FDepartment getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FDepartment bean = new FDepartment();
      bean = (FDepartment)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_DEPARTMENTS_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if(rs!= null && rs.next()){
            bean = getInformation(rs);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
   
    
    
    public String getUserByDeparmentInGroup(Connection cnn,String deparmentsMember,int ruleId,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getUserByDeparmentInGroup()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      String result ="";
      ResultSet rs = null;
      try
      {
        String departments = "";        
        if (deparmentsMember!=null && !deparmentsMember.equals("")){
            String[] temp = deparmentsMember.split(",");            
            for (int i=0;i<temp.length;i++){
                String[] temp1 = temp[i].split("#"); 
                if (departments!="") departments +=",";
                    departments +=temp1[1];
            }
        }
        if (departments.equals("")) departments = "0";
//        prstm = cnn.prepareStatement(SQL_SELECT_DEPARTMENTS_ALL_MEMBER.replaceAll("#",departments));
        prstm.setLong(PARAM_01,userId);
        prstm.setInt(PARAM_02,ruleId);
        prstm.setLong(PARAM_03,userId);
        prstm.setInt(PARAM_04,ruleId);
        rs = prstm.executeQuery();              
        int id= 0;
        while(rs != null && rs.next())
        {
            id = rs.getInt(USERS_DEPARTMENT_ID);            
            if (!result.equals("")) result +=",";            
            if (deparmentsMember!=null && !deparmentsMember.equals("")){
                String[] temp = deparmentsMember.split(",");            
                for (int i=0;i<temp.length;i++){
                    String[] temp1 = temp[i].split("#"); 
                    if (id == Integer.parseInt(temp1[1])){
                        result += temp1[0] + "#" + rs.getInt(USERS_USER_ID);  
                        break;
                    }
                }
            }
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return result;        
    }
    
    
    public String getUserMemberGroup(Connection cnn,String groupMember,int docId,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getUserMemberGroup()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      String result ="";
      ResultSet rs = null;
      try
      {
        String groups = "";        
        if (groupMember!=null && !groupMember.equals("")){
            String[] temp = groupMember.split(",");            
            for (int i=0;i<temp.length;i++){
                String[] temp1 = temp[i].split("#"); 
                if (groups!="") groups +=",";
                    groups +=temp1[1];
            }
        }
        if (groups.equals("")) groups = "0";
//        prstm = cnn.prepareStatement(SQL_SELECT_GRAOUP_ALL_MEMBER.replaceAll("#",groups));
          prstm.setLong(PARAM_01,userId);
          prstm.setInt(PARAM_02,docId);
          prstm.setLong(PARAM_03,userId);
          prstm.setInt(PARAM_04,docId);
        rs = prstm.executeQuery();              
        int id= 0;
        while(rs != null && rs.next())
        {
            id = rs.getInt(USERS_GROUP_ID);            
            if (!result.equals("")) result +=",";            
            if (groupMember!=null && !groupMember.equals("")){
                String[] temp = groupMember.split(",");            
                for (int i=0;i<temp.length;i++){
                    String[] temp1 = temp[i].split("#"); 
                    if (id == Integer.parseInt(temp1[1])){
                        result += temp1[0] + "#" + rs.getInt(USERS_USER_ID);  
                        break;
                    }
                }
            }
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return result;        
    }
    
    
    
//    public FBeans getAllRecordByRule(Connection cnn,FDocAssign bean,long userId) throws EException
//    {
//      final String LOCATION = this.toString() + "getAllRecordByRule()";
//      FBeans beans = null;
//      PreparedStatement prstm = null;
//      ResultSet rs = null;
//     
//      try
//      {
//           FBeans beansTranfer = new FBeans();                                   
//           beansTranfer = new DTransfer().getAllTransfer(cnn);
//           //.println(SQL_DEPARTMENT_BY_DOC_RULE_RECV);
//            prstm = cnn.prepareStatement(SQL_DEPARTMENT_BY_DOC_RULE_RECV);
//            prstm.setLong(PARAM_01,userId);
//            prstm.setInt(PARAM_02,bean.getRuleId());
//            prstm.setInt(PARAM_03,bean.getWorkflowId());
//            prstm.setLong(PARAM_04,userId);
//            prstm.setInt(PARAM_05,bean.getRuleId());         
//             prstm.setInt(PARAM_06,bean.getWorkflowId());
//            rs = prstm.executeQuery();  
//            FDepartment beantemp = null;
//            beans = new FBeans();
//            while((rs != null) && rs.next()) {
//                beantemp = new FDepartment();
//                beantemp.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
//                beantemp.setName(rs.getString(DEPARTMENTS_NAME));
//                beantemp.setTranfers(new FBeans());                            
//                beantemp.getTranfers().add(getTransferDeparment(cnn,beantemp,beansTranfer)); 
//                beans.add(beantemp);
//              
//            }
//      }
//      catch(SQLException sqle)
//      {
//        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
//      }
//      finally
//      {
//        closeResultSet(rs);
//        closePreparedStatement(prstm);
//      }
//      return beans;        
//    }
    
//    public FDepartment getTransferDeparment(Connection cnn,FDepartment bean,FBeans beans) {     
//     
//        FTransfer beanTemp = null;
//        bean.setTranfers(new FBeans());                      
//        if (beans!=null){
//            for (int i=0;i<beans.size();i++){
//                beanTemp =new FTransfer();
//                beanTemp = (FTransfer)beans.get(i);                
//                bean.getTranfers().add(beanTemp); 
//            }
//        }    
//      return bean;        
//    }
    
    public boolean haveChild(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FDepartment bean = (FDepartment)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_DEPARTMENTS_HAVECHILD);         
          pstmt.setInt(PARAM_01,bean.getId());
          rs = pstmt.executeQuery();
          result = rs!=null && rs.next();
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
   
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_DEPARTMENTS_DEPARTMENT,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean update(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FDepartment bean = (FDepartment)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_DEPARTMENTS_DEPARTMENT,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      FDepartment bean = (FDepartment)seed;
      return 0 < delete(cnn, TABLE_DEPARTMENTS, DEPARTMENTS_DEPARTMENT_ID + EQUAL + bean.getId());
    }    

    public FBeans getMultiRecords(Connection cnn,int idDepartment) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_DEPARTMENTS);
        //.println(SQL_SELECT_DEPARTMENTS);
        rs = prstm.executeQuery();
        String members = ",";
        FDepartment bean = null;
        boolean all = idDepartment==0;
        boolean start = false;
        int id = -1; 
            while((rs != null) && (rs.next() && members!=null))
            {
              id = rs.getInt(PARAM_01);
              if((all && members.indexOf("," + id + ",")<0) || (!start && id == idDepartment)){
                 start = true;
                 bean = new FDepartment();
                 bean.setId(id);
                 bean.setName(rs.getString(PARAM_02));
                 bean.setParentID(rs.getInt(PARAM_03));
                 if(id>0){
                      members+= id +  ",";
                      beans.add(bean);
                 }                
              }
              if(start){
                  if(all || members.indexOf("," + id + ",")>=0){
                      id = rs.getInt(PARAM_04);
                      bean = new FDepartment();
                      bean.setId(id);
                      bean.setName(rs.getString(PARAM_05));
                      bean.setParentID(rs.getInt(PARAM_06));            
                      if(id>0){
                            members+= id +  ",";
                            beans.add(bean);
                      } else {
                        all = true;                   
                      }
                  }else if(!all){
                      members=null;
                      start = false;
                  }
               }
            }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public String getMembers(Connection cnn,int idDepartment) throws EException
    {
      final String LOCATION = this.toString() + "getMembers()";
      String members = ",";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        //.println(SQL_SELECT_DEPARTMENTS);
        prstm = cnn.prepareStatement(SQL_SELECT_DEPARTMENTS);
        rs = prstm.executeQuery();
        boolean start = false;
        int id = 0; 
            while((rs != null) && (rs.next() && id>=0))
            {
              id = rs.getInt(PARAM_01);
              if(!start && id == idDepartment){
                 start = true;
                 if(id>0) members+= id +  ",";
              }
              if(start){
                  if(members.indexOf("," + id + ",")>=0){
                      id = rs.getInt(PARAM_04);
                      if(id>0)  members+= id +  ",";
                  }else{
                      id=-1;
                  }
               }
            }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return members.length()>1?members.substring(1,members.length()-1):"";        
    }
    
   
    
    public FDepartment getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDepartment requiretype = new FDepartment();
         try {
           requiretype.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
           requiretype.setCode(rs.getString(DEPARTMENTS_CODE));
           requiretype.setName(rs.getString(DEPARTMENTS_NAME));
           requiretype.setParentID(rs.getInt(DEPARTMENTS_PARENT_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return requiretype;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FDepartment bean = (FDepartment)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCode());
             params.add(bean.getName());
             params.add(bean.getParentID());                           
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public FBeans getTreeMultiRecords(Connection cnn,int idDepartment) throws EException
    {
      final String LOCATION = this.toString() + "getTreeMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_DEPARTMENTS_TREE_AREA);
        prstm.setInt(PARAM_01,idDepartment);
        rs = prstm.executeQuery();
        FDepartment bean = null;
            while((rs != null) && (rs.next()))
            {
                 bean = new FDepartment();
                 bean.setId(rs.getInt(PARAM_01));
                 bean.setParentID(rs.getInt(PARAM_02));
                 bean.setName(rs.getString(PARAM_03));
                 bean.setLevel(rs.getInt(PARAM_04));
                 beans.add(bean);
            }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
}
