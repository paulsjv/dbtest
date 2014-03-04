package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ResultSetToStringTest {

	@Test
	public void testResultSetToString() throws SQLException {
		//given: A ResultSet
		
		ResultSet mockResultSet = Mockito.mock(ResultSet.class);
		ResultSetMetaData mockResultSetMetaData = Mockito.mock(ResultSetMetaData.class);
		
		BDDMockito.given(mockResultSet.next()).will(new Answer<Boolean>() {
			private int count = 0;
			public Boolean answer(InvocationOnMock invocation) {
				if (count < 2) {
					count++;
					return true;
				}
				return false;
			}
		});
		
		
		BDDMockito.given(mockResultSet.getMetaData()).willReturn(mockResultSetMetaData);
		BDDMockito.given(mockResultSetMetaData.getColumnCount()).willReturn(2);
		BDDMockito.given(mockResultSetMetaData.getColumnName(0)).willReturn("column1");
		BDDMockito.given(mockResultSetMetaData.getColumnName(1)).willReturn("column2");
		BDDMockito.given(mockResultSet.getString(0)).willReturn("value1");
		BDDMockito.given(mockResultSet.getString(1)).willReturn("value2");
		
		String expectedResultSet = "column1\t\tcolumn2\nvalue1\t\tvalue2\nvalue1\t\tvalue2";
		
		//when: we call resultSetToString() on it
		
		String actualResultSet = ResultSetToString.resultSetToString(mockResultSet);
		
		//then: should return the String representation of the ResultSet
		
		assertEquals(expectedResultSet, actualResultSet);
	}
	
}
