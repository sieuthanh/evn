package com.dao.evn.evnErrorBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;

import com.form.evn.evnErrorBuffer.FEvnErrorBuffer;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.TabExpander;

public class DEvnErrorBuffer extends DSqlEvn {

	public boolean isExist(Connection conn, FSeed seed) throws EException {
		final String LOCATION = "->isExist()";
		boolean result = false;
		FEvnErrorBuffer bean = (FEvnErrorBuffer) seed;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement(SQL_SELECT_EVN_ERROR_BUFFER_INFORMATION);
			pstmt.setString(PARAM_01, bean.getError_Code());
			pstmt.setInt(PARAM_02, bean.getError_Id());
			rs = pstmt.executeQuery();
			result = rs != null && rs.next();
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return result;
	}
	
	public FEvnErrorBuffer getRecordByCode(Connection cnn, String code)
			throws EException {
		final String LOCATION = this.toString() + "getRecordByID()";
		PreparedStatement prstm = null;
		ResultSet rs = null;
		FEvnErrorBuffer bean = new FEvnErrorBuffer();
		try {
			prstm = cnn.prepareStatement(SQL_SELECT_EVN_ERROR_BUFFER_BY_CODE);
			prstm.setString(PARAM_01,code);
			rs = prstm.executeQuery();
			if ((rs != null) && (rs.next())) {
				bean = getInformation(rs);
			}
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return bean;
	}
	public FEvnErrorBuffer getRecordByID(Connection cnn, FSeed seed)
			throws EException {
		final String LOCATION = this.toString() + "getRecordByID()";
		PreparedStatement prstm = null;
		ResultSet rs = null;
		FEvnErrorBuffer bean = new FEvnErrorBuffer();
		bean = (FEvnErrorBuffer) seed;
		try {
			prstm = cnn.prepareStatement(SQL_SELECT_EVN_ERROR_BUFFER_BY_ID);
			prstm.setInt(PARAM_01, bean.getError_Id());
			rs = prstm.executeQuery();
			if ((rs != null) && (rs.next())) {
				bean = getInformation(rs);
			}
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return bean;
	}

	
	public boolean insert(Connection cnn, FSeed seed) throws EException {
		final String LOCATION = this.toString() + INSERT;
		Boolean result = false;
		try {
			List params = setParams(seed);
			result = execute(cnn, SQL_INSERT_EVN_ERROR_BUFFER, params) > 0;
		} catch (Exception sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean update(Connection cnn, FSeed seed) throws SQLException,
			EException {
		final String LOCATION = this.toString() + UPDATE;
		boolean result = false;
		try {
			FEvnErrorBuffer bean = (FEvnErrorBuffer) seed;
			List params = setParams(seed);
			params.add(bean.getError_Id());
			result = execute(cnn, SQL_UPDATE_EVN_ERROR_BUFFER, params) > 0;
		} catch (EException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean delete(Connection cnn, FSeed seed) throws EException {
		final String LOCATION = this.toString() + DELETE;
		FEvnErrorBuffer bean = (FEvnErrorBuffer) seed;
		return 0 < delete(cnn, TABLE_EVN_ERROR_BUFFER,
				EVN_ERROR_BUFFER_ID + EQUAL + bean.getError_Id());
	}

	public FEvnErrorBuffer getInformation(ResultSet rs) throws EException {
		final String LOCATION = "->getInformation()";
		FEvnErrorBuffer bean = new FEvnErrorBuffer();
		try {
			bean.setError_Id(rs.getInt(EVN_ERROR_BUFFER_ID));
			bean.setError_Code(rs.getString(EVN_ERROR_BUFFER_CODE));
			bean.setError_Description(rs
					.getString(EVN_ERROR_BUFFER_DESCRIPTION));
			bean.setModule_Id(rs.getInt(EVN_ERROR_BUFFER_MODULE_ID));
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
		}
		return bean;
	}

	public FBeans getMultiRecords(Connection cnn, String SQL_SELECT, List params)
			throws EException {
		final String LOCATION = this.toString() + "getMultiRecords()";
		FBeans beans = new FBeans();
		PreparedStatement prstm = null;
		ResultSet rs = null;
		try {
			prstm = prepareStatement(cnn, SQL_SELECT, params);
			rs = prstm.executeQuery();
			while ((rs != null) && (rs.next())) {
				beans.add(getInformation(rs));
			}
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return beans;
	}

	public List setParams(FSeed seed) throws EException {
		final String LOCATION = "->setParams()";
		FEvnErrorBuffer bean = (FEvnErrorBuffer) seed;
		List params = new ArrayList();
		try {
			params.add(bean.getError_Id());
			params.add(bean.getError_Code());
			params.add(bean.getError_Description());
			params.add(bean.getModule_Id());
		
		} catch (Exception exp) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, exp);
		} finally {
		}
		return params;
	}

}
