package com.ca.im.ra.dbtest.database;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.ca.im.ra.dbtest.database.impl.MySQLConnector;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnectorTest {
	
	public MockMySQLConnector dbConnector;
	public MysqlDataSource mockDataSource;
	public Connection mockConnection;
	
	public class MockMySQLConnector extends MySQLConnector {
		
		public MockMySQLConnector(String ipAddress, String databaseName,
				String username, String password, int port, int timeout) {
			super(ipAddress, databaseName, username, password, port, timeout);
		}

		public void setConnection(Connection connection) {
			super.setConnection(connection);
		}
		
		public void setMySQLDataSource(MysqlDataSource ds) {
			super.setMySQLDataSource(ds);
		}
		
		@Override
		protected void create() throws SQLException {
			super.setMySQLDataSource(mockDataSource);
		}
		
	}
	
	@Before
	public void setUp() throws SQLException {
		dbConnector = new MockMySQLConnector("10.0.2.118", "archive15", "netqos", "netqos", 3307, 12345);
		mockDataSource = Mockito.mock(MysqlDataSource.class);
		mockConnection = Mockito.mock(Connection.class);
		BDDMockito.given(mockDataSource.getConnection()).willReturn(mockConnection);
		dbConnector.setMySQLDataSource(mockDataSource);
	}
	
	@After
	public void tearDown() {
		dbConnector = null;
		mockDataSource = null;
		mockConnection = null;
	}

	@Test
	public void connectToDB() throws SQLException, IOException {
		//given: A database connector
		
		BDDMockito.given(mockDataSource.getConnection()).willReturn(mockConnection);
		dbConnector.setMySQLDataSource(mockDataSource);

		//when: I connect to the database
		Connection connection = dbConnector.connect(); 

		//then: The connection must be valid
		Mockito.verify(mockDataSource, Mockito.times(1)).getConnection();
	}
	
	@Test
	public void connectionIsNull_ShouldBeSameObject() throws SQLException {
		BDDMockito.given(mockDataSource.getConnection()).willReturn(Mockito.mock(Connection.class));
		Assert.assertNotNull(dbConnector.connect());
		Mockito.verify(mockConnection, Mockito.times(0)).isClosed();
	}

	@Ignore // TODO: need to have not null Connection object - how to do this?
	public void connectionIsNotNull_ShouldBeSameObject() throws SQLException {
		BDDMockito.given(mockConnection.isClosed()).willReturn(false);
		BDDMockito.given(mockDataSource.getConnection()).willReturn(Mockito.mock(Connection.class));
		Assert.assertEquals(mockConnection, dbConnector.connect());
		Mockito.verify(mockConnection, Mockito.times(1)).isClosed();
	}
	
	@Test
	public void connectionShouldBeDifferentObject() throws SQLException {		
		// given: we have a datasource and our connection is invalid
		MockMySQLConnector mockMysqlConnector = new MockMySQLConnector("", "", "", "", 1, 1);
		BDDMockito.given(mockConnection.isClosed()).willReturn(true);
		mockMysqlConnector.setConnection(mockConnection);
		
		Connection mockNewConnection = Mockito.mock(Connection.class);
		BDDMockito.given(mockDataSource.getConnection()).willReturn(mockNewConnection);
		
		// when: connect is called
		Connection validConnection = mockMysqlConnector.connect();
		
		// then: return new valid connection
		Assert.assertNotEquals(mockConnection, validConnection);
		Mockito.verify(mockConnection, Mockito.times(1)).isClosed();
	}
	

	@Test
	public void testPreparedStatement() throws SQLException
	{
		//given: a database connector and a query string
		String query = "DUMMY_QUERY";
		
		PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);
		BDDMockito.given(mockConnection.prepareStatement(Mockito.anyString())).willReturn(mockStatement);
				
		//when: we call prepareStatement
		PreparedStatement statement = dbConnector.prepareStatement(query);
		
		//then: it returns a ResultSet
		assertNotNull("PrepareSatement should not be null", statement);
		Mockito.verify(mockDataSource, Mockito.times(1)).getConnection();
		Mockito.verify(mockConnection, Mockito.times(1)).prepareStatement(query);
		Mockito.verify(mockStatement, Mockito.times(0)).executeQuery();
	}
	
	
	


	/*
	 String sql = connection.nativeSQL("show create table as_nexthop_traffic;");
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
