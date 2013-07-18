package com.bo.evn.evnModuleBuffer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.dao.connection.DBConnector;
import com.dao.connection.DBTypes;
import com.dao.evn.evnModuleBuffer.DEvnModuleBuffer;
import com.dao.evn.reportBuffer.DReportBuffer;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.evnModuleBuffer.FEvnModuleBuffer;
import com.inf.IDBKey;
import com.lib.AppConfigs;

public class BEvnModuleBuffer {
	public FBeans getAllColumnsInTable(FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this + "->getAllColumnsInTable()";
		FBeans result = null;
		Connection conn = null;
		try {
			if (IDBKey.getValue(DBTypes.DATABASE_TYPE.toString()).equals("-1")) {
				try {
					Class.forName(IDBKey.getValue(DBTypes.DATABASE_DRIVER
							.toString()));
				} catch (Exception e) {
				}
				conn = DriverManager.getConnection(IDBKey
						.getValue(DBTypes.DATABASE_URL.toString()));
			} else {
				conn = DBConnector.getConnection();
			}
			// DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			result = dao.getAllColumnsInTable(conn, seed);
			// DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

	public FEvnModuleBuffer getRecordByID(FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this + "->getAppByID()";
		FEvnModuleBuffer result = null;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			result = dao.getRecordByID(conn, seed);
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

	public List<FEvnModuleBuffer> getListRecord() throws EException, SQLException{
		final String LOCATION = this + "->getListRecord()";
		List<FEvnModuleBuffer> result = null;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			result = dao.getListRecord(conn,dao.SQL_SELECT_EVN_MODULE_BUFFER);
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}
	public FBeans getAllRecord() throws EException, SQLException {
		final String LOCATION = this + "->getAllRecord()";
		FBeans result = null;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			result = dao.getMultiRecords(conn,
					dao.SQL_SELECT_EVN_MODULE_BUFFER, null);
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

	public boolean delete(FSeed seed) throws EException, SQLException {
		final String LOCATION = this + "->delete()";
		boolean result = false;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			result = dao.delete(conn, seed);
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

	public boolean insert(FSeed seed) throws EException, SQLException {
		final String LOCATION = this + "->insert()";
		boolean result = false;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			if (!dao.isExist(conn, seed)) {
				result = dao.insert(conn, seed);
			}
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

	public boolean update(FSeed seed) throws EException, SQLException {
		final String LOCATION = this + "->insert()";
		boolean result = false;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnModuleBuffer dao = new DEvnModuleBuffer();
			if (!dao.isExist(conn, seed)) {
				result = dao.update(conn, seed);
			}
			DBConnector.endTransaction(conn);
		} catch (EException ex) {
			DBConnector.rollBackTransaction(conn);
			if (AppConfigs.APP_DEBUG)
				throw new EException(LOCATION, ex);
		} finally {
			DBConnector.closeConnection(conn);
		}
		return result;
	}

}
