package db2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Tool {
    public static boolean CheckColumn(Hashtable<String, String> htblColNameType, String strClusteringKeyColumn) {
        for (Map.Entry<String, String> entry : htblColNameType.entrySet()) {
            String key = entry.getKey();
            if (key == strClusteringKeyColumn)
                return true;
        }
        return false;
    }

    public static boolean isTableUnique(String strTableName) throws DBAppException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data//metadata.csv"))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // if (isFirstLine) { // Skip the first line (header)
                // isFirstLine = false;
                // continue;
                // }

                String[] data = line.split(",");
                String existingTableName = data[0];

                if (existingTableName.equals(strTableName)) {
                    return false;
                }
            }

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Metadata file cannot be located.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading from Metadata file.");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean checkApplicable(String ClassType) {
        Vector<String> type = new Vector<String>();
        type.add("java.lang.Integer");
        type.add("java.lang.String");
        type.add("java.lang.Double");
        type.add("java.lang.Boolean");
        type.add("java.util.Date");
        type.add("java.awt.Polygon");
        for (String possible : type) {
            if (possible.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static void WriteIntoMetaData(Hashtable<String, String> htblColNameType, String strTableName,
            String strClusteringKeyColumn) {
        String filePath = "./metadata.csv"; // Adjust the file path as needed
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Map.Entry<String, String> entry : htblColNameType.entrySet()) {
                // Get the key and value of the current entry
                String key = entry.getKey();
                String value = entry.getValue();

                boolean flag = key.equals(strClusteringKeyColumn);

                // Write metadata to the CSV file
                writer.write(strTableName + ',' + key + ',' + value + ',' + flag + ",null,null");
                writer.newLine(); // Add a new line after each entry
            }

        } catch (Exception E) {
            System.out.println("Failed to update metadata.csv!");
			E.printStackTrace();
        }
    }

    public static boolean checkKey(String strClusteringKeyColumn, Hashtable<String, String> htblColNameType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkKey'");
    }

}
