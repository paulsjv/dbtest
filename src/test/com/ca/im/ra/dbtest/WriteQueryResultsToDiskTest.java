package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


public class WriteQueryResultsToDiskTest {

	@Test
	public void checkIfFileExists() throws IOException {
		// given - a file name if file exists in root of project
		String fileName = "file.txt";
		WirteQueryResultsToDisk write = new WirteQueryResultsToDisk();
		
		// when - checking if file exists
		write.writeResults(fileName, "writing to file");
		
		// then - return true
		File resultsFile = new File(fileName);
		assertTrue("file should exist", resultsFile.exists());
		resultsFile.delete();
	}
	
}
