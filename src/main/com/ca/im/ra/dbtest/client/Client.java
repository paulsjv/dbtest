package com.ca.im.ra.dbtest.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.ca.im.ra.dbtest.database.DatabaseConnector;
import com.ca.im.ra.dbtest.database.DatabaseQuery;
import com.ca.im.ra.dbtest.database.impl.MySQLConnector;
import com.ca.im.ra.dbtest.director.Director;

public class Client {

	private static Properties configuration = new Properties();

	public static void main(String args[]) throws IOException, SQLException
	{
		if(args.length != 2)
		{
			System.out.println("Please provide a query input file and a properties file \n\nUsage:");
			System.out.println("java -jar DataValidator.jar <query input file> <properties file>");
			System.out.println("E.g: java -jar DataValidator.jar as_traffic_RPR.txt DataValidator.properties");
		}
		else
		{
			String queryInputFileName = args[0];
			String propFileName = args[1];

			InputStream inputStream = new FileInputStream(propFileName);
			configuration.load(inputStream);

			DatabaseConnector oldConnection = new MySQLConnector(configuration.getProperty("oldDatabaseIP"), 
					configuration.getProperty("oldDatabaseName"), 
					configuration.getProperty("oldUserName"), 
					configuration.getProperty("oldPassword"), 
					Integer.parseInt(configuration.getProperty("oldPort")), 
					Integer.parseInt(configuration.getProperty("oldTimeout")));

			DatabaseConnector newConnection = new MySQLConnector(configuration.getProperty("newDatabaseIP"), 
					configuration.getProperty("newDatabaseName"), 
					configuration.getProperty("newUserName"), 
					configuration.getProperty("newPassword"), 
					Integer.parseInt(configuration.getProperty("newPort")), 
					Integer.parseInt(configuration.getProperty("newTimeout")));

			DatabaseQuery oldQuery = new DatabaseQuery(oldConnection);
			DatabaseQuery newQuery = new DatabaseQuery(newConnection);

			Director.validateData(oldQuery, newQuery, queryInputFileName);

		}
	}
}