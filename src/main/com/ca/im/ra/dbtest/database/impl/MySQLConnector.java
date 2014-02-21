package com.ca.im.ra.dbtest.database.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.ca.im.ra.dbtest.database.DatabaseConnector;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnector implements DatabaseConnector {

	private MysqlDataSource ds = new MysqlDataSource();
	
	public MySQLConnector(String ipaddress, String databaseName, String username,
			String password, int port) {
		ds.setAutoReconnect(true);
		ds.setServerName(ipaddress);
		ds.setUser(username);
		ds.setPassword(password);
		ds.setPort(port);
		//ds.setConnectTimeout(timeout);
	}

	@Override
	public Connection connect() throws SQLException {
		return ds.getConnection();
	}

}
