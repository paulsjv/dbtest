package com.ca.im.ra.dbtest.director;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ca.im.ra.dbtest.CompareResultSets;
import com.ca.im.ra.dbtest.ReadQueryInputFile;
import com.ca.im.ra.dbtest.ResultSetToString;
import com.ca.im.ra.dbtest.WriteQueryResultsToDisk;
import com.ca.im.ra.dbtest.database.DatabaseQuery;

public class Director {

	private static ReadQueryInputFile queryInputFile = new ReadQueryInputFile();
	private static CompareResultSets compareResultSets = new CompareResultSets();
	private static WriteQueryResultsToDisk writer = new WriteQueryResultsToDisk();
	private static final String FILE_NAME = "OutputFile.txt";
	private static File outputFile = new File(FILE_NAME);

	public static boolean validateData(DatabaseQuery oldDatabaseQuery, 
			DatabaseQuery newDatabaseQuery, String queryInputFileName) throws IOException, SQLException {
		boolean identicalData = true;
		// read the file
		List<String> queries = getQueryInputFile().readQueryFileReturnList(queryInputFileName);

		// loop through by line / per query
		for(int i=0; i<queries.size(); i++ )
		{
			// query old db for results
			ResultSet actualResultSet  = oldDatabaseQuery.runQuery(queries.get(i));

			// query new db for results
			ResultSet expectedResultSet = newDatabaseQuery.runQuery(queries.get(i));

			// compare results
			boolean resultSetsIdentical = compareResultSets.compareResults(actualResultSet, expectedResultSet);

			// if different write to file and keep flag false
			if(!resultSetsIdentical)
			{
				identicalData = false;
				writer.writeResults(outputFile, Integer.toString(i+1)+ " - " + queries.get(i) + "\n");
				
				writer.writeResults(outputFile, "EXPECTED:\n" + ResultSetToString.resultSetToString(expectedResultSet) + "\n");
				writer.writeResults(outputFile, "ACTUAL:\n" + ResultSetToString.resultSetToString(actualResultSet) + "\n");
				writer.writeResults(outputFile, "===========================================================================================================");
			}

			// if no difference, continue

		}
		if(identicalData)
		{
			writer.writeResults(outputFile, "Data is identical!");
		}
		return identicalData;
	}

	public static ReadQueryInputFile getQueryInputFile() {
		return queryInputFile;
	}

	public static void setQueryInputFile(ReadQueryInputFile queryInputFile) {
		Director.queryInputFile = queryInputFile;
	}
}
