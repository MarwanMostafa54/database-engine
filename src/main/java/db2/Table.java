package db2;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.Utilities;

public class Table implements Serializable {
    private Vector<Integer> pagesGroup;
    private String tableName;

    public Table(String strTableName, String strClusteringKeyColumn, Hashtable<String, String> htblColNameType)
            throws DBAppException {
        if (!Tool.checkKey(strClusteringKeyColumn, htblColNameType)) {
            throw new DBAppException("The selected clustering column does not exist in your list of columns.");
        } else {
            if (Tool.isTableUnique(strTableName)) {

                tableName = strTableName;
                // step one: check if all the column types are acceptable.
                Set<String> colName = htblColNameType.keySet();

                for (String n : colName) {
                    if (Tool.checkApplicable(htblColNameType.get(n)) == false) {
                        throw new DBAppException("Inapplicable column type.");
                    }
                }
                Tool.WriteIntoMetaData(htblColNameType, strTableName, strClusteringKeyColumn);
                pagesGroup = new Vector<Integer>();
            } else {
                throw new DBAppException("Table already exists. Please use another name.");
            }
        }

    }

}
