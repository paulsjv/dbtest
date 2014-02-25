package com.ca.im.ra.dbtest.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ca.im.ra.dbtest.database.DatabaseConnector;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnector implements DatabaseConnector {

	private MysqlDataSource ds = null;
	private Connection connection = null;
	private String ipAddress;
	private String databaseName;
	private String userName;
	private String password;
	private int port;
	private int timeout;
	
	
	public MySQLConnector(String ipAddress, String databaseName, String username,
			String password, int port, int timeout) {
		this.ipAddress = ipAddress;
		this.databaseName = databaseName;
		this.userName = username;
		this.password = password;
		this.port = port;
		this.timeout = timeout;
	}

	protected void create() throws SQLException {
		ds = new MysqlDataSource();
		ds.setServerName(ipAddress);
		ds.setDatabaseName(databaseName);
		ds.setUser(userName);
		ds.setPassword(password);
		ds.setPort(port);
		ds.setConnectTimeout(timeout);
		ds.setAutoReconnect(true);
	}

	@Override
	public Connection connect() throws SQLException {
		create();
		createConnection();
		return getConnection();
	}
	
	@Override
	public PreparedStatement prepareStatement(String query) throws SQLException {
		return this.connect().prepareStatement(query);
		
	}

	private void createConnection() throws SQLException {
		if(isConnectionNull() || isConnectionNotValid())
		{
			setConnection(ds.getConnection());
		}
	}

	private boolean isConnectionNotValid() throws SQLException {
		return isConnectionNotNull() && isConnectionClosed();
	}

	private boolean isConnectionNull() {
		return connection == null;
	}

	private boolean isConnectionNotNull() throws SQLException {
		return getConnection() != null;
	}

	private boolean isConnectionClosed() throws SQLException {
		return isConnectionNotNull() ? getConnection().isClosed() : true;
	}

	protected void setMySQLDataSource(MysqlDataSource ds) {
		this.ds = ds;		
	}
	
	protected Connection getConnection() throws SQLException {
		return connection;
	}
	
	protected void setConnection(Connection connection) {
		this.connection = connection;
	}
}
