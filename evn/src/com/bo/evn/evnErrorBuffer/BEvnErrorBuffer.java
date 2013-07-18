package com.bo.evn.evnErrorBuffer;

import java.sql.Connection;
import java.sql.SQLException;

import com.dao.connection.DBConnector;
import com.dao.evn.evnErrorBuffer.DEvnErrorBuffer;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.form.evn.evnErrorBuffer.FEvnErrorBuffer;
import com.lib.AppConfigs;

public class BEvnErrorBuffer {

	public FEvnErrorBuffer getRecordByCode(String code) throws EException,
			SQLException {
		{
			final String LOCATION = this + "->getAppByID()";
			FEvnErrorBuffer result = null;
			Connection conn = null;
			try {
				conn = DBConnector
						.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
				DBConnector.startTransaction(conn);
				DEvnErrorBuffer dao = new DEvnErrorBuffer();
				result = dao.getRecordByCode(conn, code);
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

	public FEvnErrorBuffer getRecordByID(FSeed seed) throws EException,
			SQLException {
		final String LOCATION = this + "->getAppByID()";
		FEvnErrorBuffer result = null;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnErrorBuffer dao = new DEvnErrorBuffer();
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

	public FBeans getAllRecord() throws EException, SQLException {
		final String LOCATION = this + "->getAllRecord()";
		FBeans result = null;
		Connection conn = null;
		try {
			conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
			DBConnector.startTransaction(conn);
			DEvnErrorBuffer dao = new DEvnErrorBuffer();
			result = dao.getMultiRecords(conn, dao.SQL_SELECT_EVN_ERROR_BUFFER,
					null);
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
			DEvnErrorBuffer dao = new DEvnErrorBuffer();
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
			DEvnErrorBuffer dao = new DEvnErrorBuffer();
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
			DEvnErrorBuffer dao = new DEvnErrorBuffer();
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
