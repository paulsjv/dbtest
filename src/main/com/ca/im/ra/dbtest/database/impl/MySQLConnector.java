package com.ca.im.ra.dbtest.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ca.im.ra.dbtest.database.DatabaseConnector;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnector implements DatabaseConnector {

	private DataSource ds = null;
	private Connection connection = null;
	
	public MySQLConnector(String ipaddress, String databaseName, String username,
			String password, int port) {
		MysqlDataSource myDs = new MysqlDataSource();
		myDs.setAutoReconnect(true);
		myDs.setServerName(ipaddress);
		myDs.setUser(username);
		myDs.setPassword(password);
		myDs.setPort(port);
		
		ds = myDs;
	}

	@Override
	public Connection connect() throws SQLException {
		if(isConnectionNotNull() || isConnectionClosed())
		{
			connection = ds.getConnection();
		}
		return connection;
	}

	private boolean isConnectionNotNull() {
		return connection != null;
	}

	private boolean isConnectionClosed() throws SQLException {
		return isConnectionNotNull() ? connection.isClosed() : true;
	}

	@Override
	public PreparedStatement prepareStatement(String query) throws SQLException {
		return this.connect().prepareStatement(query);
		
	}

	public void setMySQLDataSource(DataSource ds) {
		this.ds = ds;		
	}
}
