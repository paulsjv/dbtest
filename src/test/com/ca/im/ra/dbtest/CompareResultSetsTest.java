package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class CompareResultSetsTest {

	@Test
	public void testCompareResultSets_ShouldReturnTrue() throws SQLException {
		
		//given: two resultSets that are equal
		ResultSet actualResultSet = Mockito.mock(ResultSet.class);
		ResultSet expectedResultSet = Mockito.mock(ResultSet.class);
		
		BDDMockito.given(actualResultSet.getObject(0)).willReturn("blah1");
		BDDMockito.given(expectedResultSet.getObject(0)).willReturn("blah1");
		BDDMockito.given(actualResultSet.getObject(1)).willReturn("blah2");
		BDDMockito.given(expectedResultSet.getObject(1)).willReturn("blah2");
		
		//when: we compare them
		CompareResultSets compareResultSets = new CompareResultSets();
		boolean resultSetsAreEqual =  compareResultSets.compareResults(actualResultSet, expectedResultSet);
		
		//then: returns true 
		assertTrue(resultSetsAreEqual);		
	}
	
	@Test
	public void testCompareResultSets_ShouldReturnFalse() throws SQLException {
		
		//given: two resultSets that are not equal
		ResultSet actualResultSet = Mockito.mock(ResultSet.class);
		ResultSet expectedResultSet = Mockito.mock(ResultSet.class);
		
		BDDMockito.given(actualResultSet.getObject(1)).willReturn("blah1");
		BDDMockito.given(expectedResultSet.getObject(1)).willReturn("blah1");
		BDDMockito.given(actualResultSet.getObject(2)).willReturn("blah2");
		BDDMockito.given(expectedResultSet.getObject(2)).willReturn("blah1");
		
		//when: we compare them
		CompareResultSets compareResultSets = Mockito.mock(CompareResultSets.class);
		boolean resultSetsAreEqual =  compareResultSets.compareResults(actualResultSet, expectedResultSet);
		
		//then: returns false
		assertFalse(resultSetsAreEqual);
	}
}
