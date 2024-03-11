package db2;
import java.io.*;
import java.util.*;

import javax.swing.text.Utilities;



public class page implements Serializable{

    private int N = 10;
	private int count;
	private int pageID;
	private Vector<Vector> elements;
    
    /**create new page */
    public page(){
        
        count=0;
        pageID = Tool.readNextId("config//DBApp.properties");
		Tool.incrementNextId("config//DBApp.properties"); 
        elements = new Vector<Vector>();
        N = Tool.readPageSize("config//DBApp.properties");
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


}
