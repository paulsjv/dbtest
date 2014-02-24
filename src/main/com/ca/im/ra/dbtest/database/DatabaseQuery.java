package com.ca.im.ra.dbtest.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery 
{
	private DatabaseConnector connector;

	public DatabaseQuery(DatabaseConnector connector) {
		this.connector = connector;
	}

	public ResultSet runQuery(String query) throws SQLException {
		return prepareAndRunQuery(query);
	}
	
	protected ResultSet prepareAndRunQuery(String query) throws SQLException {
		PreparedStatement p = connector.prepareStatement(query);
		return p.executeQuery();
	}
}
