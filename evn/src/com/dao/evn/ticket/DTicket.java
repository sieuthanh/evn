package com.dao.evn.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dao.DSqlEvn;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.ticket.FTicket;
import com.inf.DateProc;
import com.lib.AppConfigs;


public class DTicket extends DSqlEvn {

	public FTicket getTicketByID(Connection cnn,long id) throws Exception {
		final String LOCATION = this.toString() + "~~>getTicketByID()";
		ResultSet rs = null;
		PreparedStatement prstm = null;
		FTicket bean = new FTicket();
		try {
			List<Object> params = new ArrayList<Object>();
			String SQL = SQL_SElECT_TICKET_BUFFER_BY_ID ;
			params.add(id);
			System.out.println(SQL);
			prstm = prepareStatement(cnn, SQL, params);
			rs = prstm.executeQuery();
			while (rs != null && rs.next()) {				
				bean= getInformation(rs);
			}
			
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new Exception(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return bean;
	}
    public FTicket insert(Connection cnn, FTicket bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          bean.setTicket_id(getSeq(cnn)); 
          bean.setActive(-1);
          bean.setWheres(0);
          bean.setStatus("O");
          bean.setEvn_time(DateProc.getYYYYMMDDHHMMString(DateProc.createTimestamp()));
          List params = setParams(bean);
          System.out.println(SQL_INSERT_TICKET_BUFFER);
          result = execute(cnn,SQL_INSERT_TICKET_BUFFER,params)>0;
         
          if(!result)bean=null;
          //beanrs=getTicketByID(cnn,bean.getEvn_id());
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return bean;    
    }
    public boolean update_ticket_rasoat(Connection cnn,long id, String nameFile) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
          ArrayList params=new ArrayList();
          params.add(nameFile);
          params.add(id);
       System.out.println(SQL_UPDATE_TICKET_BUFFER_RASOAT);
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER_RASOAT,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    public boolean updateAll(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FTicket bean = (FTicket)seed;
        List params = setParamsUpdate(seed);
        params.add(bean.getTicket_id());
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    public boolean updateStatusInsert(Connection cnn, long id,String status, int active,int where) throws EException
    {
    
        boolean result = false;
      final String LOCATION = this.toString() + DELETE;
        try
        {
        List params = new ArrayList();
            params.add(status);
            params.add(active);
            params.add(where);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER_STATUS_BY_INSERT,params)>0;
        }
        catch(EException sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
      return result;
    } 
    public boolean updateStatus(Connection cnn, long id,String status) throws EException
    {
    
        boolean result = false;
      final String LOCATION = this.toString() + DELETE;
        try
        {
        List params = new ArrayList();
            params.add(status);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER_STATUS,params)>0;
        }
        catch(EException sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
      return result;
    }     
    public boolean updateActive(Connection cnn,long id,int active) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        List params = new ArrayList();
            params.add(active);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER_ACTIVE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    public boolean updateWhere(Connection cnn,long id,int where) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        List params = new ArrayList();
            params.add(where);
            params.add(id);
        result = execute(cnn,SQL_UPDATE_TICKET_BUFFER_WHERE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    public int getSeq(Connection cnn) throws Exception {
            final String LOCATION = this.toString() + "~~>getSeq()";
            ResultSet rs = null;
            PreparedStatement prstm = null;
            int seq=0;
            try {
                    List<Object> params = new ArrayList<Object>();
                    String SQL = SQL_SElECT_TICKET_BUFFER_SEQ ;
                   
                    prstm = prepareStatement(cnn, SQL, params);
                    rs = prstm.executeQuery();
                    if (rs.next())seq=rs.getInt(1);
    
                    
            } catch (SQLException sqle) {
                    if (AppConfigs.APP_DEBUG)
                            throw new Exception(LOCATION, sqle);
            } finally {
                    closeResultSet(rs);
                    closePreparedStatement(prstm);
            }
            return seq;
    }
	public FTicket getTicketByReportCode(Connection cnn,String code) throws Exception {
		final String LOCATION = this.toString() + "~~>getTicketByReportCode()";
		ResultSet rs = null;
		PreparedStatement prstm = null;
		FTicket bean = new FTicket();
		try {
			List<Object> params = new ArrayList<Object>();
			String SQL = SQL_SElECT_TICKET_BUFFER_BY_REPORTCODE ;
			params.add(code);
			System.out.println(SQL);
			prstm = prepareStatement(cnn, SQL, params);
			rs = prstm.executeQuery();
			while (rs != null && rs.next()) {				
				bean= getInformation(rs);
			}
			
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new Exception(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return bean;
	}

	public FTicket getInformation(ResultSet rs) throws EException {
		final String LOCATION = "->getInformation()";
		FTicket bean = new FTicket();
		try {
			bean.setTicket_id(rs.getInt(TICKET_ID));
			bean.setTicket_id_err(rs.getInt(TICKET_ID_ERR));
			bean.setSrc_connect_id(rs.getInt(TICKET_SRC_CONNECT_ID));
			bean.setStatus(rs.getString(TICKET_STATUS));
			bean.setActive(rs.getInt(TICKET_ACTIVE));
			bean.setEvn_id(rs.getInt(TICKET_EVN_ID));
			bean.setEvn_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_EVN_TIME)));
			bean.setBuffer_id(rs.getInt(TICKET_BUFFER_ID));
			bean.setBuffer_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_BUFFER_TIME)));
			bean.setEbs_id(rs.getInt(TICKET_EBS_ID));
			bean.setEbs_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_EBS_TIME)));
			bean.setStart_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_START_TIME)));
			bean.setEnd_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_END_TIME)));
			bean.setTotal_records(rs.getInt(TICKET_TOTAL_RECORDS));
			bean.setCode(rs.getString(TICKET_CODE));
			bean.setTable_name(rs.getString(TICKET_TABLE_NAME));
			bean.setWheres(rs.getInt(TICKET_WHERES));
			bean.setDescription(rs.getString(TICKET_DESCRIPTION));
                        bean.setDataFile(rs.getString(TICKET_DATA_FILE));
		    bean.setError_code(rs.getString(TICKET_ERROR_CODE));
		    bean.setPriority_time(DateProc.getYYYYMMDDHHMMString(rs.getTimestamp(TICKET_PRIORITY_TIME)));
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
		}
		return bean;
	}
    public List setParamsUpdate(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FTicket bean = (FTicket)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getTicket_id());
             params.add(bean.getTicket_id_err());
             params.add(bean.getSrc_connect_id());
             params.add(bean.getStatus());
             params.add(bean.getActive());
             params.add(bean.getEvn_id());
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEvn_time()));
             params.add(bean.getBuffer_id());
             if(bean.getBuffer_time()==null){
                 params.add(null);
             }else{
                 params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getBuffer_time()));
             }
             params.add(bean.getEbs_id());
             if(bean.getEbs_time()==null){
                 params.add(null);
             }else{
                 params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEbs_time()));
             }
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getStart_time()));
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEnd_time()));
             params.add(bean.getTotal_records());
             params.add(bean.getCode());                           
             params.add(bean.getTable_name());                                               
             params.add(bean.getWheres());                                               
             params.add(bean.getDescription());                                               
             params.add(bean.getDataFile());
             params.add(bean.getError_code());
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEnd_time()));
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FTicket bean = (FTicket)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getTicket_id());
             params.add(bean.getTicket_id_err());
             params.add(bean.getSrc_connect_id());
             params.add(bean.getStatus());
             params.add(bean.getActive());
             params.add(bean.getEvn_id());
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEvn_time()));
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getStart_time()));
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEnd_time()));
             params.add(bean.getTotal_records());
             params.add(bean.getCode());                           
             params.add(bean.getTable_name());                                               
             params.add(bean.getWheres());                                               
             params.add(bean.getDescription());                                               
             params.add(DateProc.StringYYYYMMDDHH24MI2Timestamp(bean.getEnd_time()));
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        System.out.println(SQL_SELECT);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            beans.add(getInformation(rs));
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
