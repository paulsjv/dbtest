package com.ca.im.ra.dbtest.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {
	public Connection connect() throws SQLException;
}
