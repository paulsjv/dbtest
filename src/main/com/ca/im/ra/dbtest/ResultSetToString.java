package com.ca.im.ra.dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToString {

	private static int numCol = 0;
	
	public static String resultSetToString(ResultSet resultSet) throws SQLException {
		String result = "";
		
		result = getColumnHeaders(resultSet);
		
		result += getColumnValues(resultSet);
		
		return result.trim();
	}

	private static String getColumnValues(ResultSet resultSet)
			throws SQLException {
		String result = "";
		while (resultSet.next()) {
			for (int i=1; i<=numCol; i++) {			
				result += getColumnValue(resultSet, i);
			}
		}
		return result;
	}
	
	private static String getColumnValue(ResultSet resultSet, int i) throws SQLException {
		String result = resultSet.getString(i);
		
		result += addTabs(i);
		
		result += addNewLine(i);
				
		return result;
	}

	private static String getColumnHeaders(ResultSet resultSet)
			throws SQLException {
		String result = "";
		numCol = resultSet.getMetaData().getColumnCount();
		
		for (int i=1; i<=numCol; i++) {
			result += getColumnName(resultSet, i);
		}
		return result;
	}

	private static String getColumnName(ResultSet resultSet,
			 int i) throws SQLException {
		String result = resultSet.getMetaData().getColumnName(i);
		
		result += addTabs(i);
		
		result += addNewLine(i);
		
		return result;
	}

	private static String addNewLine(int i) {
		if (isLastColumn(i)) {
			return "\n";
		}
		return "";
	}

	private static String addTabs(int i) {
		if (hasMoreColumns(i)) {
			return "\t\t";
		}
		return "";
	}

	private static boolean isLastColumn(int i) {
		return i == numCol;
	}

	private static boolean hasMoreColumns(int i) {
		return i < numCol;
	}

}
