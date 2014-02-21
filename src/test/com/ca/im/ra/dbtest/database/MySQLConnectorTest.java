package com.ca.im.ra.dbtest.database;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ca.im.ra.dbtest.database.impl.MySQLConnector;

public class MySQLConnectorTest {

	@Test
	public void connectToDB() throws SQLException, IOException {
		//given: A database connector
		DatabaseConnector dbConnector = new MySQLConnector("10.0.2.118", "archive15", "netqos", "netqos", 3307);
		
		//when: I connect to the database
		Connection connection = dbConnector.connect(); 
		
		//then: The connection must be valid
		assertTrue(connection.isValid(10));
	}
	

	
	
	
	
	
	/*String sql = connection.nativeSQL("show create table as_nexthop_traffic;");
	PreparedStatement p = connection.prepareStatement(sql);
	p.execute();
	
	String actual = null;
	ResultSet rs = p.getResultSet();
	if (rs.next()) {
		actual = rs.getString("Create Table");
	}
	
	//FileUtils.writeStringToFile(new File("testfile.txt"), actual, "utf-8");
	String expected = FileUtils.readFileToString(new File("testfile.txt"), "utf-8");
	
	assertEquals(expected, actual);
	
	@SuppressWarnings("unused")
	private MysqlDataSource getMySQLDS(String IpOfHost, String dbName,
			String dbuser, String dbpasswd, int port, int timeout) throws SQLException {

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setAutoReconnect(true);
		mysqlDS.setServerName(IpOfHost);
		mysqlDS.setUser(dbuser);
		mysqlDS.setPassword(dbpasswd);
		mysqlDS.setDatabaseName(dbName);
		mysqlDS.setPort(port);
		mysqlDS.setConnectTimeout(90*1000);
	
		return (mysqlDS);
	}
	*/
}
