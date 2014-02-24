package com.ca.im.ra.dbtest.database;

import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class DatabaseQueryTest {
	
	public class MockDatabaseQuery extends DatabaseQuery {
		public MockDatabaseQuery(DatabaseConnector connector) {
			super(connector);
		}

		@Override
		protected ResultSet prepareAndRunQuery(String query) throws SQLException {
			return Mockito.mock(ResultSet.class);
		}
	}

	@Test
	public void testRunQuery() throws SQLException
	{
		//given: we have a connection to the database
		String query = "select    protocol,    sum(inoctets + outoctets) Bytes    from protocol_traffic    where (router=167782434 and interface=9) and TimeStamp > 1392729900 - 900 and TimeStamp <= 1392758700 and protocol not in (0,4,1,2)    group by protocol    having Bytes > 0    order by Bytes desc    limit 12;";
		
		DatabaseConnector mockConnector = Mockito.mock(DatabaseConnector.class);		
		MockDatabaseQuery databaseQuery = new MockDatabaseQuery(mockConnector);
		
		//when: we run a query		
		ResultSet rs = databaseQuery.runQuery(query);
		
		//then: we get back a result set
		assertNotNull("Result Set should not be null", rs);	
	}
}
