package com.dao.admin.groups;


import com.dao.DSqlAdmin;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.groups.FGroup;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DGroups  extends DSqlAdmin
{
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FGroup bean = (FGroup)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_GROUPS_INFORMATION);         
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
    
    
   
    
    public boolean haveChild(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FGroup bean = (FGroup)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_GROUPS_HAVECHILD);         
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
    
    public String getGroupMembers(Connection cnn,int groupId) throws EException
    {
      final String LOCATION = this.toString() + "getGroupMembers()";
      String members = ",";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_GROUPS_TREE);
        //.println(SQL_SELECT_GROUPS_TREE);
        rs = prstm.executeQuery();
        boolean start = false;
        int id = 0; 
            while((rs != null) && (rs.next() && id>=0))
            {
              id = rs.getInt(PARAM_01);
              if(!start && id == groupId){
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
    
    public FBeans getMultiRecordsGroupTree(Connection cnn,int groupId,int userId) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecordsGroupTree()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_GROUPS_TREE);
        //.println(SQL_SELECT_GROUPS_TREE);
        rs = prstm.executeQuery();
        String members = ",";
        FGroup bean = null;
        boolean all = groupId==0;
        boolean start = false;
        int id = -1; 
            while((rs != null) && (rs.next() && members!=null))
            {
              id = rs.getInt(PARAM_01);
              if((all && members.indexOf("," + id + ",")<0) || (!start && id == groupId)){
                 start = true;
                 bean = new FGroup();
                 bean.setId(id);
                 bean.setName(rs.getString(PARAM_02));
                 bean.setParentID(rs.getInt(PARAM_03));
                 bean.setSpase("");
                 if (userId>0){
                    bean.setSelected(getCheckedGroup(cnn,userId,bean.getId()));
                 }
                 if(id>0){
                      members+= id +  ",";
                      beans.add(bean);
                 }
              }
              if(start){
                  if(all || members.indexOf("," + id + ",")>=0){
                      id = rs.getInt(PARAM_04);
                      bean = new FGroup();
                      bean.setId(id);
                      bean.setName(rs.getString(PARAM_05));
                      bean.setParentID(rs.getInt(PARAM_06));  
                      if (userId>0){
                         bean.setSelected(getCheckedGroup(cnn,userId,bean.getId()));
                      }
                      bean.setSpase("  . . . ");
                      if(id>0){
                            members+= id +  ",";
                            beans.add(bean);
                      }
                  }else if(!all){
                      members=null;
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
    
    public FGroup getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FGroup bean = new FGroup();
      bean = (FGroup)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_GROUPS_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
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
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          FGroup bean = (FGroup)seed;
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_GROUPS_GROUP,params)>0;
//          if(result){
//              DMenu daoMenu=new DMenu();
//              result=daoMenu.createGPermision(cnn,bean.getMenus(),bean.getId());
//          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;    
    }
    
    public boolean update(Connection cnn, FSeed seed) throws  SQLException,EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FGroup bean = (FGroup)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_GROUPS_GROUP,params)>0;
//        if(result){
//            DMenu daoMenu=new DMenu();
//            result=daoMenu.createGPermision(cnn,bean.getMenus(),bean.getId());
//        }
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      FGroup bean = (FGroup)seed;
      return 0 < delete(cnn, TABLE_GROUPS, GROUPS_GROUP_ID + EQUAL + bean.getId());
    }    

    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params,int userId) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            FGroup group = new FGroup();
            group = getInformation(rs);  
//            if (userId>0){
//                group.setSelected(getCheckedGroup(cnn,userId,group.getId()));
//            }
            beans.add(group);
           

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
    
    public String getCheckedGroup(Connection cnn,int userId,int groupID) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String result ="";     
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_GROUP_USER_BY_ID);
        prstm.setInt(PARAM_01,userId);
        prstm.setInt(PARAM_02,groupID);
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next())){
            result = "selected";
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
    
    public FGroup getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FGroup group = new FGroup();
         try {
           group.setId(rs.getInt(GROUPS_GROUP_ID));
           group.setCode(rs.getString(GROUPS_CODE));
           group.setName(rs.getString(GROUPS_NAME));
           group.setParentID(rs.getInt(GROUPS_PARENT_ID));
           group.setRole(rs.getInt(GROUPS_ROLE));
           group.setPrivilege(rs.getInt(GROUPS_PRIVILEGE));
//           group.setChangePassword(rs.getInt(USERS_CHANGE_PASSWORD));
           group.setDateCreate(rs.getString(GROUPS_DATE_CREATE));
           group.setApp(rs.getString(GROUPS_APP));
           
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return group;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FGroup bean = (FGroup)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCode());
             params.add(bean.getName());
             params.add(bean.getParentID());                           
             params.add(bean.getRole());
             params.add(bean.getPrivilege());
             params.add(bean.getApp());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
//    public int getTopGroupId(Connection cnn) throws EException
//    {
//      final String LOCATION = this.toString() + "getTopGroupId()";
//      PreparedStatement prstm = null;
//      ResultSet rs = null;
//      int result =0;     
//      try
//      {
//        prstm = cnn.prepareStatement(SQL_SELECT_GROUP_BY_TOP);
//        rs = prstm.executeQuery();
//        if((rs != null) && (rs.next()))
//        {
//            result =rs.getInt(GROUPS_GROUP_ID);
//        }
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
//      return result;        
//    }
}
