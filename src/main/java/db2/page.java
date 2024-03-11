package db2;

import java.io.*;
import java.util.*;

public class page implements Serializable {

    private int N = 10;
    private int count;
    private int pageID;
    private Vector<Vector> elements;

    /** create new page */
    public page() {

        count = 0;
        pageID = Tool.readNextId("config//DBApp.properties");
        Tool.incrementNextId("config//DBApp.properties");
        elements = new Vector<Vector>();

    }

    public int getN() {
        return N;
    }

    public int getCount() {
        return count;
    }

    public int getPageID() {
        return pageID;
    }

    public String toString() {

        return "";
    }
    public static void verifyTuple(String strTableName, Hashtable<String,Object> htblColNameValue, Hashtable<String, String>columnNameColumnType) throws DBAppException {
		//and checking if the user gave me a value for every column

		for (Map.Entry<String, String> mapElement :columnNameColumnType.entrySet()) {
			if(htblColNameValue.get(mapElement.getKey())==null)
			{
				//didn't receive a value for this specific column
				throw new DBAppException("Invalid tuple. Did not recieve values for every column");
			}
		}
		//check that column names in htblColNameValue actually exist and that the corresponding values respect the data type
		for(Map.Entry<String, Object> mapElement :htblColNameValue.entrySet())
		{
			//column name does not exist
			if(!columnNameColumnType.containsKey(mapElement.getKey()))
			{
				throw new DBAppException("Invalid column name.");
			}
			else
			{

				//determining type mismatch
				if(columnNameColumnType.get(mapElement.getKey()).equals("java.lang.Integer") && !(mapElement.getValue()instanceof Integer))
				{
					throw new DBAppException("Type mismatch. Cannot convert value to Integer.");
				}
				if(columnNameColumnType.get(mapElement.getKey()).equals("java.lang.String") && !(mapElement.getValue()instanceof String))
				{
					throw new DBAppException("Type mismatch. Cannot convert value to String.");
				}
				if(columnNameColumnType.get(mapElement.getKey()).equals("java.lang.Double") && !(mapElement.getValue()instanceof Double))
				{
					throw new DBAppException("Type mismatch. Cannot convert value to Double.");
				}
				if(columnNameColumnType.get(mapElement.getKey()).equals("java.lang.Boolean") && !(mapElement.getValue()instanceof Boolean))
				{
					throw new DBAppException("Type mismatch. Cannot convert value to Boolean.");
				}
				if(columnNameColumnType.get(mapElement.getKey()).equals("java.util.Date") && !(mapElement.getValue()instanceof Date))
				{
					throw new DBAppException("Type mismatch. Cannot convert value to Date.");
				}
			}
		}
	}
}
