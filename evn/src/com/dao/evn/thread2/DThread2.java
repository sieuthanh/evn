package com.dao.evn.thread2;

import com.dao.DSqlEvn;
import com.form.FSeed;
import com.form.FBeans;
import com.exp.EException;
import com.form.evn.FChangeData;
import com.form.evn.FXml;
import com.form.evn.common.FCommon;
import com.form.evn.reportBuffer.FReportBuffer;

import com.form.evn.sourceConnectBuffer.FSourceConnectBuffer;

import com.inf.ICoreAction;

import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DThread2 extends DSqlEvn {
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
			// rs3 = prstm1.executeQuery();

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
				// rs2 = dbmt.getPrimaryKeys(null,null,bean.getNameTable());
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

	public String[][] getAllRecord(Connection cnn, FSeed seed)
			throws EException {
		final String LOCATION = this.toString() + "getAllRecord()";
		FChangeData bean = (FChangeData) seed;
		PreparedStatement prstm = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT * FROM ";
		String[][] mang = null;
		try {
			List params = new ArrayList();
			// SQL_SELECT+=bean.getNameTable();
			if (bean.getNameSQL().equals("") || bean.getNameSQL() == null) {
				SQL_SELECT += bean.getNameTable() + bean.getOrderBy();
			} else {
				SQL_SELECT += bean.getNameTable() + WHERE + " UPPER("
						+ bean.getColumnName() + ")" + LIKE + "'" + PER_CENT
						+ bean.getNameSQL().toUpperCase() + PER_CENT + "'"
						+ bean.getOrderBy();
			}
			if (bean.getId_sql() == 1) {
				SQL_SELECT = bean.getNameSQL();
			}

			prstm = prepareStatement(cnn, SQL_SELECT.trim(), params);
			rs = prstm.executeQuery();
			FBeans beansT = new FBeans();
			int tt = getValue(cnn, "(" + SQL_SELECT + ") a", COUNT("*"), params);
			beansT.setTotalRows(tt);// getValue(cnn, "tblUser", COUNT("*"),
									// null) );//count(cnn,SQL_SELECT,params));
			int checkP = (bean.getPageIndex() * AppConfigs.APP_ROWS_VIEW - tt);
			if (checkP > AppConfigs.APP_ROWS_VIEW) {
				bean.setPageIndex(1);
			}
			beansT.setPageIndex(bean.getPageIndex());
			if (beansT.getFirstRecord() <= 1) {
				rs.beforeFirst();
			} else {
				rs.absolute(beansT.getFirstRecord() - 1);
			}
			ResultSetMetaData rsmd = rs.getMetaData();

			if (tt < AppConfigs.APP_ROWS_VIEW) {
				mang = new String[tt + 1][rsmd.getColumnCount()];
			} else {
				int view = (bean.getPageIndex() * AppConfigs.APP_ROWS_VIEW - tt);
				if (view > 0) {
					mang = new String[AppConfigs.APP_ROWS_VIEW - view + 1][rsmd
							.getColumnCount()];
				} else {
					mang = new String[AppConfigs.APP_ROWS_VIEW + 1][rsmd
							.getColumnCount()];
				}
			}

			int k = 0;
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
				mang[k][i - 1] = rsmd.getColumnName(i);
			}

			int ok = 0;
			while ((rs != null) && (rs.next())
					&& (k < AppConfigs.APP_ROWS_VIEW)) {
				k++;
				// System.out.println(rsmd.getColumnCount());
				for (int j = 0; j < rsmd.getColumnCount(); j++) {
					mang[k][j] = rs.getString(j + 1);
				}
				ok++;
			}
			// if(ok==0){
			// mang=null;
			// }
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return mang;
	}

	public boolean insert(Connection cnn, FSeed seed, FBeans columns,
			String[][] mang) throws EException, SQLException {
		final String LOCATION = this.toString() + INSERT;
		Boolean result = false;
		FSourceConnectBuffer bean = (FSourceConnectBuffer) seed;
		String SQL = "INSERT INTO " + bean.getNameTable() + " VALUES (";

		for (int q = 0; q < columns.size(); q++) {
			SQL += "?,";
		}
		SQL = SQL.substring(0, SQL.length() - 1) + ")";

		PreparedStatement ps = cnn.prepareStatement(SQL);
		FChangeData beanT = new FChangeData();
		try {
			for (int i = 1; i < mang.length; i++) {
				for (int j = 0; j < mang[i].length; j++) {
					beanT = (FChangeData) columns.get(j);
					if (beanT.getColumnTypeName().equals("1")) {
						ps.setInt(j + 1, Integer.parseInt(mang[i][j]));
					} else if (beanT.getColumnTypeName().equals("4")) {
						ps.setDate(j + 1, beanT.stringToSqlDate(mang[i][j]));
					} else {
						ps.setString(j + 1, mang[i][j]);
					}
				}
				ps.addBatch();
			}
			result = ps.executeBatch().length > 0;
		} catch (Exception sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean insertCommon(Connection cnn, FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this.toString() + "==>insertCommon";
		Boolean result = false;
		FCommon bean = (FCommon) seed;
		String SQL = "INSERT INTO " + bean.getNameTable() + SQL_INSERT_COMMON;

		try {

			List params = new ArrayList();
			params.add(bean.getSrc_Record_Id());
			params.add(bean.getTicket_Id());
			params.add(bean.getSrc_Connect_Id());
			params.add(bean.getStatus());
			params.add(bean.getCreate_By());
			params.add(new Timestamp(new Date().getTime()));
			params.add(bean.getUpdate_By());
			params.add(bean.getUpdate_Date());

			System.out.println(SQL);
			result = execute(cnn, SQL, params) > 0;

			if (!result)
				bean = null;
		} catch (Exception sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean updateCommon(Connection cnn, FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this.toString() + "==>updateCommon";
		Boolean result = false;
		FCommon bean = (FCommon) seed;
		String SQL = UPDATE + bean.getNameTable() + SQL_UPDATE_COMMON;
		if (bean.getSrc_Record_Id() > 0) {
			SQL = UPDATE + bean.getNameTable() + SQL_UPDATE_COMMON_RECORD;
		}

		try {
			List params = new ArrayList();
			params.add(bean.getStatus());
			params.add(bean.getError_code());
			params.add(bean.getError_description());
			params.add(bean.getTicket_Id());
			if (bean.getSrc_Record_Id() > 0) {
				params.add(bean.getSrc_Record_Id());
			}
			System.out.println(SQL);
			result = execute(cnn, SQL, params) > 0;

			if (!result)
				bean = null;
		} catch (Exception sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean insertXuly(Connection cnn, String sql, List params)
			throws EException, SQLException {
		final String LOCATION = this.toString() + "==>insertXuly";
		Boolean result = false;
		PreparedStatement prstm = null;
		try {
			System.out.println("------------------------");
			prstm = prepareStatement(cnn, sql, params);
			for (int i = 0; i < params.size(); i++) {
				prstm.setObject(i + 1, params.get(i));
			}

			result = prstm.executeUpdate() > 0;
		} catch (Exception sqle) {
			System.out.println("[insertXuly]==>" + sqle.getMessage());
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean insertXuly(Connection cnn, FBeans beans, int total)
			throws EException, SQLException {
		final String LOCATION = this.toString() + "==>insertXuly";
		Boolean result = false;
		FBeans beansT = new FBeans();
		int t = 0;
		try {
			for (int i = 0; i < beans.size(); i++) {
				t++;
				FXml bean = new FXml();
				bean = (FXml) beans.get(i);
				beansT.add(bean);
				if (t == total) {
					result = insertXuly(cnn, beansT);
					if (result == false) {
						return result;
					}
					beansT = new FBeans();
					t = 0;
				}
			}
			if (beansT.size() > 0) {
				result = insertXuly(cnn, beansT);
				if (result == false) {
					return result;
				}
			}

		} catch (Exception sqle) {
			System.out.println("[insertXuly]==>" + sqle.getMessage());
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		}
		return result;
	}

	public boolean insertXuly(Connection cnn, FBeans beans) throws EException,
			SQLException {
		final String LOCATION = this.toString() + "==>insertXuly";
		Boolean result = false;
		PreparedStatement prstm = null;
		PreparedStatement prstm1 = null;
		int checkLevel = 0;
		int checkLevel1 = 0;
		for (int i = 0; i < beans.size(); i++) {
			FXml bean = new FXml();
			bean = (FXml) beans.get(i);
			if (bean.getLevel() == 0) {
				if (checkLevel == 0) {
					prstm = cnn.prepareStatement(bean.getSql());
					System.out.println(bean.getSql());
				}

				try {
					List params = bean.getParams();
					for (int j = 0; j < params.size(); j++) {
						prstm.setObject(j + 1, params.get(j));
						System.out.println(j + 1 + "-----" + params.get(j));
					}
					prstm.addBatch();
					++checkLevel;
				} catch (Exception sqle) {
					System.out.println("[insertXuly]==>" + sqle.getMessage());
					if (AppConfigs.APP_DEBUG)
						throw new EException(LOCATION, sqle);
				}
			} else {
				if (checkLevel1 == 0) {
					prstm1 = cnn.prepareStatement(bean.getSql());
					System.out.println(bean.getSql());
				}
				try {
					List params = bean.getParams();
					for (int j = 0; j < params.size(); j++) {
						prstm1.setObject(j + 1, params.get(j));
					}
					prstm1.addBatch();
					++checkLevel1;
				} catch (Exception sqle) {
					System.out.println("[insertXuly]==>" + sqle.getMessage());
					if (AppConfigs.APP_DEBUG)
						throw new EException(LOCATION, sqle);
				}
			}
		}
		if (checkLevel1 > 0) {
			prstm1.executeBatch();
		}
		if (checkLevel > 0) {
			prstm.executeBatch();
			result = true;
		}

		return result;
	}

	public FBeans getPaging(Connection cnn, FSeed seed) throws EException {
		final String LOCATION = this.toString() + "getPaging()";
		FBeans beans = new FBeans();
		FChangeData bean = (FChangeData) seed;
		PreparedStatement prstm = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT * FROM ";
		try {
			List params = new ArrayList();

			// SQL_SELECT+=bean.getNameTable();
			if (bean.getNameSQL().equals("") || bean.getNameSQL() == null) {
				SQL_SELECT += bean.getNameTable() + bean.getOrderBy();
			} else {
				SQL_SELECT += bean.getNameTable() + WHERE + " UPPER("
						+ bean.getColumnName() + ")" + LIKE + "'" + PER_CENT
						+ bean.getNameSQL().toUpperCase() + PER_CENT + "'"
						+ bean.getOrderBy();
				// System.out.println(SQL_SELECT);
			}

			prstm = prepareStatement(cnn, SQL_SELECT.trim(), params);
			rs = prstm.executeQuery();
			// rs.last();
			beans.setTotalRows(getValue(cnn, "(" + SQL_SELECT + ") a",
					COUNT("*"), params));
			beans.setPageIndex(bean.getPageIndex());
			// if(beans.getFirstRecord()<=1){
			// rs.beforeFirst();
			// }else{
			// rs.absolute(beans.getFirstRecord()-1);
			// }
		} catch (SQLException sqle) {
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, sqle);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(prstm);
		}
		return beans;
	}

}
