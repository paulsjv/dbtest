package com.ca.im.ra.dbtest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class WriteQueryResultsToDiskTest {

	@Test
	public void checkIfFileExists() throws IOException {
		// given - a file name if file exists in root of project
		File outputFile = new File("OutputFile.txt");
		WriteQueryResultsToDisk write = new WriteQueryResultsToDisk();
		
		// when - checking if file exists
		write.writeResults(outputFile, "writing to file");
		
		// then - return true
		assertTrue("file should exist", outputFile.exists());
		outputFile.delete();
	}
}
