package com.ca.im.ra.dbtest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class WirteQueryResultsToDisk {

	public void writeResults(String fileName, String results) throws IOException {
		FileUtils.writeStringToFile(new File(fileName), results);
	}
}
