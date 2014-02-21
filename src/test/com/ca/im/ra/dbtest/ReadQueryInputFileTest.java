package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ReadQueryInputFileTest {

	@Test
	public void readQueryFromTextFile() throws IOException {
		// given there is a query input file
		ReadQueryInputFile read = new ReadQueryInputFile();
		
		// when trying to read file
		String actual = read.readQueryFile("testquery.txt");
		
		// then should have query string
		String expected = "select    protocol,    sum(inoctets + outoctets) Bytes    from protocol_traffic    where (router=167782434 and interface=9) and TimeStamp > 1392729900 - 900 and TimeStamp <= 1392758700 and protocol not in (0,4,1,2)    group by protocol    having Bytes > 0    order by Bytes desc    limit 12;";
		assertEquals(expected, actual);
	}
}
