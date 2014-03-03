package com.ca.im.ra.dbtest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CompareResultSets {

	public boolean compareResults(ResultSet actualResultSet,
			ResultSet expectedResultSet) throws SQLException {
		while (actualResultSet.next()) {
			if(!expectedResultSet.next())
			{
				return false;
			}
			
			ResultSetMetaData resultSetMetaData = actualResultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				if (!isEqual(actualResultSet, expectedResultSet, i)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isEqual(ResultSet actualResultSet,
			ResultSet expectedResultSet, int i) throws SQLException {
		return actualResultSet.getObject(i).equals(expectedResultSet.getObject(i));
	}
}