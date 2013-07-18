package com.dao.evn.evnModuleBuffer;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;

import com.form.evn.FChangeData;
import com.form.evn.evnErrorBuffer.FEvnErrorBuffer;
import com.form.evn.evnModuleBuffer.FEvnModuleBuffer;
import com.form.evn.sourceAccountBuffer.FSourceAccountBuffer;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.TabExpander;

public class DEvnModuleBuffer extends DSqlEvn {
	public FBeans getAllColumnsInTable(Connection cnn, FSeed seed)
			throws EException {
		final String LOCATION = this.toString() + "getAllColumnsInTable()";
		FBeans beans = new FBeans();
		FChangeData bean = (FChangeData) seed;
		PreparedStatement prstm = null;
		PreparedStatement prstm1 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String SQL_SELECT = "SELECT * FROM ";
		try {
			List params = new ArrayList();
			List params1 = new ArrayList();
			SQL_SELECT += bean.getNameTable();
			if (bean.getId_sql() == 1) {
				SQL_SELECT = bean.getNameSQL();
			}

			String[] tableFKKey = new String[2];
			String[] columnFKKey = new String[2];
			params1.add(bean.getNameTable());
			prstm1 = prepareStatement(cnn, SQL_SELECT_FOREIGN.trim(), params1);
			rs3 = prstm1.executeQuery();

			int f = 0;
			while (rs3 != null && rs3.next()) {
				columnFKKey[f] = rs3.getString("COLUMN_NAME");
				tableFKKey[f] = rs3.getString("TABLE_NAME");
				f++;
			}
			closeResultSet(rs3);
			closePreparedStatement(prstm1);

			prstm = cnn.prepareStatement(SQL_SELECT.trim());
			DatabaseMetaData dbmt = cnn.getMetaData();
			try {
				rs2 = dbmt.getPrimaryKeys(null, null, bean.getNameTable());
			} catch (Exception ex) {
			} finally {
			}

			String columnPrimaryKey = "";
			while (rs2 != null && rs2.next()) {
				columnPrimaryKey += rs2.getString("COLUMN_NAME");
			}
			rs = prstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
				FChangeData beanT = new FChangeData();
				if (rs2 != null
						&& columnPrimaryKey.equals(rsmd.getColumnName(i))) {
					beanT.setNotNull(1);
				} else {
					beanT.setNotNull(0);
				}
				boolean rsmdata_NoNulls = (rsmd.isNullable(i) == java.sql.DatabaseMetaData.columnNoNulls);
				if (rsmdata_NoNulls) {
					beanT.setNotNull(beanT.getNotNull() + 1);
				}
				beanT.setColumnName(rsmd.getColumnName(i));
				// ---------
				for (int i1 = 0; i1 < columnFKKey.length; ++i1) {
					if (beanT.getColumnName().equals(columnFKKey[i1])) {
						beanT.setColumnFK(1);
						beanT.setTableFK(tableFKKey[i1]);
					}

				}

				// -----------
				if ((rsmd.getColumnTypeName(i).toLowerCase().indexOf("number") == 0)
						|| (rsmd.getColumnTypeName(i).toLowerCase()
								.indexOf("int") == 0)) {
					beanT.setColumnTypeName("1");
				} else if ((rsmd.getColumnTypeName(i).toLowerCase()
						.indexOf("time") == 0)
						|| (rsmd.getColumnTypeName(i).toLowerCase()
								.indexOf("date") == 0)) {
					beanT.setColumnTypeName("4");
				} else if (rsmd.getColumnTypeName(i).toLowerCase()
						.indexOf("long") == 0) {
					beanT.setColumnTypeName("3");
				} else {
					beanT.setColumnTypeName("2");
				}

				beans.add(beanT);
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

	public boolean isExist(Connection conn, FSeed seed) throws EException {
		final String LOCATION = "->isExist()";
		boolean result = false;
		FEvnModuleBuffer bean = (FEvnModuleBuffer) seed;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement(SQL_SELECT_EVN_MODULE_BUFFER_INFORMATION);
			pstmt.setString(PARAM_01, bean.getModuleCode());
			pstmt.setInt(PARAM_02, bean.getModuleId());
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

	public FEvnModuleBuffer getRecordByID(Connection cnn, FSeed seed)
			throws EException {
		final String LOCATION = this.toString() + "getRecordByID()";
		PreparedStatement prstm = null;
		ResultSet rs = null;
		FEvnModuleBuffer bean = new FEvnModuleBuffer();
		bean = (FEvnModuleBuffer) seed;
		try {
			prstm = cnn.prepareStatement(SQL_SELECT_EVN_MODULE_BUFFER_BY_ID);
			prstm.setInt(PARAM_01, bean.getModuleId());
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
			result = execute(cnn, SQL_INSERT_EVN_MODULE_BUFFER, params) > 0;
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
			FEvnModuleBuffer bean = (FEvnModuleBuffer) seed;
			List params = setParams(seed);
			params.add(bean.getModuleId());
			result = execute(cnn, SQL_UPDATE_EVN_MODULE_BUFFER, params) > 0;
		} catch (EException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean delete(Connection cnn, FSeed seed) throws EException {
		final String LOCATION = this.toString() + DELETE;
		FEvnModuleBuffer bean = (FEvnModuleBuffer) seed;
		return 0 < delete(cnn, TABLE_EVN_MODULE_BUFFER, EVN_MODULE_BUFFER_ID
				+ EQUAL + bean.getModuleId());
	}

	public FEvnModuleBuffer getInformation(ResultSet rs) throws EException {
		final String LOCATION = "->getInformation()";
		FEvnModuleBuffer bean = new FEvnModuleBuffer();
		try {
			bean.setModuleId(rs.getInt(EVN_MODULE_BUFFER_ID));
			bean.setModuleCode(rs.getString(EVN_MODULE_BUFFER_CODE));
			bean.setModuleDescription(rs
					.getString(EVN_MODULE_BUFFER_DESCRIPTION));
			bean.setModuleAtribute1(rs.getString(EVN_MODULE_BUFFER_ATRIBUTE_1));
			bean.setModuleAtribute2(rs.getString(EVN_MODULE_BUFFER_ATRIBUTE_2));
			bean.setModuleAtribute3(rs.getString(EVN_MODULE_BUFFER_ATRIBUTE_3));
			if(bean.getModuleAtribute1()==null){
				 bean.setModuleAtribute1("");
			}
			if(bean.getModuleAtribute2()==null){
				 bean.setModuleAtribute2("");
			}
			if(bean.getModuleAtribute3()==null){
				 bean.setModuleAtribute3("");
			}
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
		}
		return bean;
	}

	public List<FEvnModuleBuffer> getListRecord(Connection cnn,String SQL_SELECT) throws EException{
		final String LOCATION = this.toString() + "getListRecord()";
		//List<FEvnModuleBuffer> result = new ArrayList<>();
		List<FEvnModuleBuffer> result = new ArrayList<FEvnModuleBuffer>();
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = cnn.createStatement();
			rs = stm.executeQuery(SQL_SELECT);
			while((rs != null) && (rs.next())){
				result.add(getInformation(rs));
			}
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closeStatement(stm);
		}
		return result;
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
		FEvnModuleBuffer bean = (FEvnModuleBuffer) seed;
		List params = new ArrayList();
		try {
			params.add(bean.getModuleId());
			params.add(bean.getModuleCode());
			params.add(bean.getModuleDescription());
			params.add(bean.getModuleAtribute1());
			params.add(bean.getModuleAtribute2());
			params.add(bean.getModuleAtribute3());

		} catch (Exception exp) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, exp);
		} finally {
		}
		return params;
	}

}
