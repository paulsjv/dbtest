package com.ca.im.ra.dbtest.director;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.ca.im.ra.dbtest.ReadQueryInputFile;
import com.ca.im.ra.dbtest.database.DatabaseQuery;

public class DirectorTest {

	@Test
	public void testValidateData() throws IOException, SQLException {
		// given: a valid config bean
		DatabaseQuery oldMySQLQuery = Mockito.mock(DatabaseQuery.class);
		DatabaseQuery newMySQLQuery = Mockito.mock(DatabaseQuery.class);

		ResultSet oldResultSet = Mockito.mock(ResultSet.class);
		// mock result set (some method) and the mock data it returns.
		BDDMockito.given(oldMySQLQuery.runQuery(Mockito.anyString())).willReturn(oldResultSet);
		
		ResultSet newResultSet = Mockito.mock(ResultSet.class);
		// mock result set (some method) and the mock data it returns.
		BDDMockito.given(newMySQLQuery.runQuery(Mockito.anyString())).willReturn(newResultSet);
		
		ReadQueryInputFile mockReadQueryInputFile = Mockito
				.mock(ReadQueryInputFile.class);

		String queryInputFileName = "fileName";
		Director.setQueryInputFile(mockReadQueryInputFile);

		List<String> returnList = new ArrayList<String>();
		returnList.add("one");
		returnList.add("two");
		BDDMockito.given(
				mockReadQueryInputFile
						.readQueryFileReturnList(queryInputFileName))
				.willReturn(returnList);

		// when: we call run
		boolean result = Director.validateData(oldMySQLQuery,
				newMySQLQuery, queryInputFileName);

		// then: it should return true
		assertTrue(result);
		Mockito.verify(mockReadQueryInputFile, Mockito.times(1))
				.readQueryFileReturnList(Mockito.anyString());
	}
}
