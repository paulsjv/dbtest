package com.ca.im.ra.dbtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ReadQueryInputFile {

	public String readQueryFile(String fileName) throws IOException {
		return FileUtils.readFileToString(new File(fileName));
	}

	public List<String> readQueryFileReturnList(String fileName) throws IOException {
		return FileUtils.readLines(new File(fileName));	
	}
}
