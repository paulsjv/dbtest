package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ReadQueryInputFileTest {

	private String fileName = "testquery.txt";
	
	@Test
	public void readQueryFromTextFile() throws IOException {
		// given there is a query input file
		ReadQueryInputFile read = new ReadQueryInputFile();
		
		// when trying to read file
		
		String actual = read.readQueryFile(fileName);
		
		// then should have query string
		String expected = "select    protocol,    sum(inoctets + outoctets) Bytes    from protocol_traffic    where (router=167782434 and interface=9) and TimeStamp > 1392729900 - 900 and TimeStamp <= 1392758700 and protocol not in (0,4,1,2)    group by protocol    having Bytes > 0    order by Bytes desc    limit 12;";
		assertEquals(expected, actual);
	}
	
	@Test
	public void readQueryFromTextFileReturnListStrings() throws IOException {
		// given there is a query input file
		ReadQueryInputFile read = new ReadQueryInputFile();
		
		// when trying to read file
		List<String> actual = read.readQueryFileReturnList(fileName);
		
		// then should have list of strings
		String expected = "select    protocol,    sum(inoctets + outoctets) Bytes    from protocol_traffic    where (router=167782434 and interface=9) and TimeStamp > 1392729900 - 900 and TimeStamp <= 1392758700 and protocol not in (0,4,1,2)    group by protocol    having Bytes > 0    order by Bytes desc    limit 12;";
		assertEquals(expected, actual.get(0));
		
	}
}
