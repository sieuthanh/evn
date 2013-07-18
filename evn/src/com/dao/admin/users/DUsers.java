package com.dao.admin.users;

import com.dao.DSqlAdmin;
import com.dao.admin.departments.DDepartments;
import com.dao.admin.groups.DGroups;
import com.form.admin.users.FUser;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.admin.departments.FDepartment;
import com.form.admin.groups.FGroup;
import com.form.admin.login.FLoginSystem;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DUsers  extends DSqlAdmin
{
    public FBeans getAllUser(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getAllUser()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FUser bean = new FUser();
      FBeans beans =new FBeans();
      try
      {
       prstm = cnn.prepareStatement(SELECT + STAR + FROM + TABLE_USERS);
       beans = new FBeans();
       rs = prstm.executeQuery();
       while((rs != null) && (rs.next()))
        {
            bean = new FUser();
            bean =getInformation(cnn,rs);
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
    
    public FBeans search(Connection cnn,FUser bean) throws EException
    {
      final String LOCATION = this.toString() + "search()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String SELECT_SQL=SELECT + STAR + FROM + TABLE_USERS + WHERE + TRUE;
      try
      {
          List params =new ArrayList();
          if(bean.getNameUser()!=null && !bean.getNameUser().equals("")){
              SELECT_SQL+=" " + AND + OPEN + USERS_USERNAME + LIKE + QUESTION + OR + USERS_FULLNAME + LIKE + QUESTION + CLOSE;
              params.add(PER_CENT + bean.getNameUser() + PER_CENT);
              params.add(PER_CENT + bean.getNameUser() + PER_CENT);
          }
          prstm = prepareStatement(cnn,SELECT_SQL,params);          
          rs = prstm.executeQuery();
        while((rs != null) && (rs.next())){
            FUser user = new FUser();
            user.setId(rs.getInt(USERS_USER_ID));
            user.setUsername(rs.getString(USERS_USERNAME));
            user.setFullName(rs.getString(USERS_FULLNAME));                           
            beans.add(user);
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
    
    
    public boolean updateLastVisit(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "updateLastVisit()";
      boolean result = false;
      PreparedStatement prstm = null;
      FUser bean = (FUser)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_UPDATE_USERS_LASTVISIT);
        ////.println(SQL_UPDATE_USERS_LASTVISIT);
        prstm.setTimestamp(PARAM_01,new Timestamp(System.currentTimeMillis()));
        prstm.setInt(PARAM_02,bean.getId());
        result = prstm.executeUpdate()>0;
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;        
    }
    public boolean toggleActive(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "toggleActive()";
      PreparedStatement prstm = null;
      boolean result = false;
      FUser bean = (FUser)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_UPDATE_USERS_ACTIVE);
        ////.println(SQL_UPDATE_USERS_ACTIVE);
        prstm.setInt(PARAM_01,bean.getActive()==0?1:0);
        prstm.setInt(PARAM_02,bean.getId());
        result = prstm.executeUpdate()>0;
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;        
    }
    
    public boolean checkLogin(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->checkLogin()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
    FLoginSystem bean = (FLoginSystem)seed;
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_CHECK_LOGIN);           
          pstmt.setString(PARAM_01,bean.getUsername());
          pstmt.setString(PARAM_02,bean.getPassword());
          rs = pstmt.executeQuery();
          
          if(rs!=null && rs.next()){
             result = rs.getInt(PARAM_01) ==PARAM_01;
          }
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
    public boolean lock(Connection conn, String username)throws EException{
    final String LOCATION = "->lock()";
    boolean result = false;
    PreparedStatement pstmt = null;
     try {
          pstmt = conn.prepareStatement(SQL_UPDATE_USERS_LOCK);         
          pstmt.setInt(PARAM_01,0);
          pstmt.setString(PARAM_02,username);
          result = pstmt.executeUpdate()>0;
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closePreparedStatement(pstmt);        
     }
     return result;
    }
    public boolean checkOldpass(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->checkoldpass()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
    FLoginSystem bean = (FLoginSystem)seed;
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_CHECKOLD_LOGIN);         
          pstmt.setString(PARAM_01,bean.getUsername());
          pstmt.setString(PARAM_02,bean.getOldpassword());
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
    public boolean updateNewpass(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->updateNewpass()";
    boolean result = false;
    PreparedStatement pstmt = null;
    FLoginSystem bean = (FLoginSystem)seed;
     try {
          pstmt = conn.prepareStatement(SQL_UPDATE_USERS_USER_NEWPASS);         
          pstmt.setString(PARAM_01,bean.getPassword_());
         pstmt.setDate(PARAM_02,bean.getCurrentSqlDate());
          pstmt.setInt(PARAM_03,(int)bean.me.getId());
          result  = pstmt.executeUpdate()>0;
     }catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closePreparedStatement(pstmt);        
     }
     return result;
    }
    public boolean getUserInformation(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->getUserInformation()";
    boolean result = false;
    FLoginSystem bean = (FLoginSystem)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_LOGIN);         
          pstmt.setString(PARAM_01,bean.getUsername());
          pstmt.setString(PARAM_02,bean.getPassword());
          rs = pstmt.executeQuery();
          if(rs.next()){
            bean.setId(rs.getInt(USERS_USER_ID));
            bean.me.setId(bean.getId());
            bean.me.setUsername(rs.getString(USERS_USERNAME));
            bean.me.setPassword(rs.getString(USERS_PASSWORD));
            bean.me.setDepartmentID(rs.getInt(USERS_DEPARTMENT_ID));
            bean.me.setGroupID(rs.getInt(USERS_GROUP_ID));
            bean.me.setFullName(rs.getString(USERS_FULLNAME));                           
            bean.me.setPicture(rs.getString(USERS_PICTURE));
            bean.me.setAPP_ID(rs.getString(USERS_APP));
            bean.setActive(rs.getInt(USERS_ACTIVE));  
            int daysLive = rs.getInt(USERS_PERIOD);
            bean.setDaysLive(daysLive);
            if(daysLive>0){
                Date today = bean.getCurrentDate();
                Date lastvisit = rs.getDate(USERS_DATE_PASSWORD);
                if(lastvisit!=null){
                    daysLive-=bean.getDays(lastvisit,today);
                }
            }
            if(bean.getActive()>0 && (bean.getDaysLive()==0 || daysLive>0)){
              bean.me.setRole(rs.getInt(USERS_ROLE));
              bean.me.setPrivilege(rs.getInt(USERS_PRIVILEGE));
              bean.me.setTimeStart(new Timestamp(new Date().getTime()));     
              bean.me.setExtTagInt(daysLive);
            }
            if(bean.getDaysLive()>0) bean.setDaysLive(daysLive);
            result = true;
          }
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
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FUser bean = (FUser)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_INFORMATION);         
          pstmt.setString(PARAM_01,bean.getUsername());
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
    public int getUserIdByTop(Connection conn)throws EException{
    final String LOCATION = "->getUserIdByTop()";
    int result = 0;    
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_TOP1);                  
          rs = pstmt.executeQuery();
          if (rs!=null && rs.next()){
              result= rs.getInt(PARAM_01);
          }          
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
    
    public boolean checkChangePas(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "checkChangePas()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      boolean result = false;
      try
      {
        
        prstm = cnn.prepareStatement(SQL_SELECT_USERS_CHANGEPASS);
        ////.println(SQL_SELECT_USERS_CHANGEPASS);
        prstm.setString(PARAM_01,((FUser)seed).getUsername());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
           result= rs.getInt(USERS_CHANGE_PASSWORD)==1;
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
    
    public FUser getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FUser bean =(FUser)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_USERS_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(cnn,rs);
            DGroups groups = new DGroups();
            FGroup group = new FGroup();
            group.setId(bean.getGroupID());
            group = groups.getRecordByID(cnn,group);
            bean.setGroupName(group.getName());            
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
    public int insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      int result = 0;  //Thanh cong
      boolean flag = false;
      PreparedStatement prstm = null;
      FUser user = (FUser)seed;
      String departmentWhere =TABLE_DEPARTMENTS + WHERE + DEPARTMENTS_DEPARTMENT_ID + EQUAL + user.getDepartmentID();
      String usersWhere =TABLE_USERS + WHERE + DEPARTMENTS_DEPARTMENT_ID + EQUAL + user.getDepartmentID();
      int maxUsers =0;
      try{      
        maxUsers = getValue(cnn,departmentWhere,DEPARTMENTS_MAX_USERS);
      }catch(NumberFormatException exp){} //Qua so luong user
      try
      {
          //.println(SQL_INSERT_USERS_USER);
          if (maxUsers==0 || getValue(cnn,usersWhere,COUNT(STAR))< maxUsers){
              user.setDateCreate(user.dateToString(new Date()));
              user.setDatePassword(user.getDateCreate());              
              List params = setParams(seed);              
              flag = execute(cnn,SQL_INSERT_USERS_USER,params)>0;               
             // flag = createGroup(cnn,seed);
//              if(flag){
//                  if(user.getMenus()!=null){
//                    flag =new DMenu().createUPermision(cnn,user.getMenus(),user.getId());
//                  }
//              }
              result = flag?0:1;
          }else{result = 2;}
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
    // delete user of group_user table
    public boolean deleteGroupUser(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + DELETE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
         FUser bean = (FUser)seed;                
         return delete(cnn, TABLE_GROUP_USER, GROUP_USER_USER_ID + EQUAL + bean.getId())>0;
     }
      catch(EException ex)
      {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
    
    public boolean createGroup(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        // delete from group_user table
         FUser bean = (FUser)seed;
         bean.setId(bean.getId()>0?bean.getId():getUserIdByTop(cnn));
         if (bean.getGroupsID()!=null){
            result = deleteGroupUser(cnn,seed);
            
            // insert into group_user table of created user                        
            prstm = cnn.prepareStatement(SQL_ADDNEW_GROUP_USER);       
            for (int i=0;i<bean.getGroupsID().length;i++){                
                prstm.setInt(PARAM_01,bean.getGroupsID()[i]);   
                prstm.setInt(PARAM_02,bean.getId());
                prstm.addBatch();
            }
             result=prstm.executeBatch().length>0;
         }
     }
      catch(EException ex)
      {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FUser bean = (FUser)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_USERS_USER,params)>0;
      //  if(result){
           // result = createGroup(cnn,seed);
//            if(result){
//               // if(bean.getMenus()!=null){
//                    DMenu daoMenu =new DMenu();
//                    result = daoMenu.createUPermision(cnn,bean.getMenus(),bean.getId());
//               // }
//            }    
     //   } 
     }
      catch(EException ex)
      {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      boolean result=false;
      FUser bean = (FUser)seed;
       
//       deleteInformationOfUsers(cnn,bean,TABLE_DOSSIERS,DOSSIERS_CREATOR);
//       
//        deleteInformationOfUsers(cnn,bean,TABLE_MYAGENDA,MYAGENDA_USER_ID);
//        deleteInformationOfUsers(cnn,bean,TABLE_AGENDA,AGENDA_USER_ID);
//        
//        deleteInformationOfUsers(cnn,bean,TABLE_TASK_CATEGORIES,TASK_CATEGORIES_CREATOR);
//        //xoa bang giao viec 
//      
//       deleteInformationOfUsers(cnn,bean,TABLE_TEMPLATES,TEMPLATE_USER_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_CABIN,CABIN_USERS_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_REPORTS,REPORT_USERS_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_UPERMISION,UPERMISION_USER_ID);
//
//       deleteInformationOfUsers(cnn,bean,TABLE_DOCSRECV,DOCSRECV_USER_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_DOC_TRAILER_RECV,DOC_TRAILER_RECV_USERSEND_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_DOC_REVIEW_RECV,DOC_REVIEW_RECV_CREATOR);
//        
//       deleteInformationOfUsers(cnn,bean,TABLE_DOCSSEND,DOCSSEND_USER_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_DOC_TRAILER_SEND,DOC_TRAILER_SEND_USERSEND_ID);
//       deleteInformationOfUsers(cnn,bean,TABLE_DOC_REVIEW_SEND,DOC_REVIEW_SEND_CREATOR);
//       
       //MESSAGE       
       result=delete(cnn, TABLE_USERS, USERS_USER_ID + EQUAL + bean.getId())>0;
      return  result;
    }    
    public boolean deleteInformationOfUsers(Connection cnn, FSeed seed,String tableName,String field) throws EException
    {
      FUser bean = (FUser)seed;
      return  delete(cnn,tableName,field + IN + OPEN + bean.getId() + CLOSE)>0;
    }

    public FBeans getUserByGroupID_DepID(Connection cnn,FUser bean,int idGroup, int idDep, int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getUserByGroupID()";
      FBeans beans = null;
      try
      {
        
        String SQL=SQL_SELECT_USERS + WHERE + TRUE;
        if(idDep>0){
            DDepartments dao = new DDepartments();            
            //SQL += AND + USERS_DEPARTMENT_ID + IN + OPEN + dao.getMembers(cnn,idDep) + CLOSE;
             SQL += AND + USERS_DEPARTMENT_ID + EQUAL + idDep;
        }else if (idGroup>0){
            SQL+=AND + USERS_GROUP_ID + EQUAL + idGroup;
        }       
          List params = new ArrayList();//oUpperCase
          if(bean.getNameUser()!=null && !bean.getNameUser().equals("")){
              params.add(PER_CENT + bean.getNameUser()+ PER_CENT);
              SQL+= AND + OPEN  + USERS_USERNAME + LIKE + QUESTION ;
              params.add(PER_CENT + bean.getNameUser()+ PER_CENT);
              SQL+=OR + USERS_FULLNAME + LIKE + QUESTION + CLOSE ;
          }
          beans = getMultiRecords(cnn,SQL,params,pageIndex);
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
      }
      return beans;        
    }
    public FBeans getUserByDepartmentID(Connection cnn,int idDepartment,int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getUserByDepartmentID()";
      FBeans beans = null;
      try
      {
        String SQL = SQL_SELECT_USERS_BY_DEPARTMENT_ID;        
        if(idDepartment>0){
            DDepartments dao = new DDepartments();            
            SQL += AND + USERS_DEPARTMENT_ID + IN + OPEN + dao.getMembers(cnn,idDepartment) + CLOSE;
        }
        beans = getMultiRecords(cnn,SQL,null,pageIndex);
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
      }
      return beans;        
    }
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params,int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
          prstm = prepareStatement(cnn,SQL_SELECT + ORDER_BY + TABLE_USERS + STOP + USERS_FULLNAME + ASC,params);          
          rs = prstm.executeQuery();
          beans = new FBeans();
              rs.last(); 
              beans.setTotalRows(rs.getRow());
              beans.setPageIndex(pageIndex);
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
        int i=0;
        while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW)))
        {
            i++;
            FUser user = getInformation(cnn,rs);
//            DGroups groups = new DGroups();
//            FGroup group = new FGroup();
//            group.setId(user.getGroupID());
//            group = groups.getRecordByID(cnn,group);
//            user.setGroupName(group.getName());
            beans.add(user);
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
    public FUser getInformation(Connection cnn, ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FUser user = new FUser();
         try {
           user.setId(rs.getInt(USERS_USER_ID));

           user.setUsername(rs.getString(USERS_USERNAME));
           user.setPassword(rs.getString(USERS_PASSWORD));
           user.setFullName(rs.getString(USERS_FULLNAME));                           
           user.setPicture(rs.getString(USERS_PICTURE));
           user.setEmail(rs.getString(USERS_EMAIL));                           
           user.setPhone(rs.getString(USERS_PHONE));
           user.setAddress(rs.getString(USERS_ADDRESS));
           user.setDescription(rs.getString(USERS_DESCRIPTION));

           user.setDateCreate(user.dateToString(rs.getDate(USERS_DATE_CREATE)));
           user.setDatePassword(user.dateToString(rs.getDate(USERS_DATE_PASSWORD)));                           
           user.setDateLogin(user.dateToString(rs.getDate(USERS_DATE_LOGIN)));

            user.setSex(rs.getInt(USERS_SEX));
            user.setDepartmentID(rs.getInt(USERS_DEPARTMENT_ID));
            FDepartment department = new FDepartment();
            department.setId(user.getDepartmentID());
            DDepartments departments = new DDepartments();
            department = departments.getRecordByID(cnn,department);           
            user.setDepartmentName(department.getName());           
            user.setRole(rs.getInt(USERS_ROLE));
            user.setPrivilege(rs.getInt(USERS_PRIVILEGE));
            user.setActive(rs.getInt(USERS_ACTIVE));
            user.setPeriod(rs.getInt(USERS_PERIOD));
            user.setGroupID(rs.getInt(USERS_GROUP_ID));
            user.setApp(rs.getString(USERS_APP));
            user.setChangePassword(rs.getInt(USERS_CHANGE_PASSWORD));
             user.setSms(rs.getInt(USERS_SMS));
             user.setArea(rs.getString(USERS_AREA));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return user;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FUser bean = (FUser)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getUsername());
             params.add(bean.getPassword());
             params.add(bean.getFullName());                           
             params.add(bean.getPicture());
             params.add(bean.getEmail());                           
             params.add(bean.getPhone());
             params.add(bean.getAddress());                           
             params.add(bean.getDescription());

             params.add(bean.stringToSqlDate(bean.getDateCreate()));
             params.add(bean.stringToSqlDate(bean.getDatePassword()));                           
             params.add(bean.stringToSqlDate(bean.getDateLogin()));

             params.add(bean.getSex());
             params.add(bean.getDepartmentID());
             params.add(bean.getRole());
             params.add(bean.getPrivilege());
             params.add(bean.getActive());
             params.add(bean.getPeriod());
             params.add(bean.getGroupID());
             params.add(bean.getApp());
             params.add(bean.getChangePassword());
             params.add(bean.getSms());
             params.add(bean.getArea());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
