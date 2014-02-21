package com.ca.im.ra.dbtest.database;

import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;

import org.junit.Test;
import org.mockito.Mockito;

public class DatabaseQueryTest {

	@Test
	public void testRunQuery()
	{
		//given: we have a connection to the database
		
		DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
		DatabaseQuery databaseQuery = new DatabaseQuery(connector);
		
		//when: we run a query
		
		String query = "select    protocol,    sum(inoctets + outoctets) Bytes    from protocol_traffic    where (router=167782434 and interface=9) and TimeStamp > 1392729900 - 900 and TimeStamp <= 1392758700 and protocol not in (0,4,1,2)    group by protocol    having Bytes > 0    order by Bytes desc    limit 12;";
		ResultSet rs = databaseQuery.runQuery(query);
		
		//then: we get back a result set
		assertNotNull(rs);
		
	}
}
