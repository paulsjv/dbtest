package com.ca.im.ra.dbtest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class WriteQueryResultsToDisk {

	public void writeResults(File fileName, String results) throws IOException {
		FileUtils.writeStringToFile(fileName, new SimpleDateFormat().format( new Date() ) + " - "+ results + "\n", true);
	}
}
