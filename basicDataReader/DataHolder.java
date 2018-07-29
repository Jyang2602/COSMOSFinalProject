package basicDataReader;
import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import basicDataReader.NumScanner;

/**
 * This class holds and manipulates the data for the knots that have recombination applied to it
 * @author Jeffrey Yang
 * @version July 22, 2018
 *
 */
public class DataHolder
{
    private List<ArrayList<Integer>> allProb;
    
    /**
     * initializes DataHolder object and the arraylist
     */
    public DataHolder()
    {
        allProb = new ArrayList<ArrayList<Integer>>();
    }
    
    /**
     * 
     * @return the contained arraylist
     */
    public List<ArrayList<Integer>> getList()
    {
        return allProb;
    }
    
    /**
     * Initializes the dimensions of the 2D Array
     */
    public void initialize()
    {
        for (int i = 0; i < 17; i++)
        {
            allProb.add(new ArrayList<Integer>());
            for (int j = 0; j < 15 ; j++)
            {
                allProb.get(i).add(0);
            }
        }
    }
    
    /**
     * @param the currentKnot from the reader file
     * @return the vertical location of the knot
     */
    public int getLocation(double d)
    {
        if (Math.abs(d-0.1) <= 0.000001)
        {
            return 0;
        }
        else if (Math.abs(d-3.1) <= 0.000001)
        {
            return 1;
        }
        else if (Math.abs(d-4.1) <= 0.000001)
        {
            return 2;
        }
        else if (Math.abs(d-5.1) <= 0.000001)
        {
            return 3;
        }
        else if (Math.abs(d-5.2) <= 0.000001)
        {
            return 4;
        }
        else if (Math.abs(d-6.1) <= 0.000001)
        {
            return 5;
        }
        else if (Math.abs(d-6.2) <= 0.000001)
        {
            return 6;
        }
        else if (Math.abs(d-6.3) <= 0.000001)
        {
            return 7;
        }
        else if (Math.abs(d-7.1) <= 0.000001)
        {
            return 8;
        }
        else if (Math.abs(d-7.2) <= 0.000001)
        {
            return 9;
        }
        else if (Math.abs(d-7.3) <= 0.000001)
        {
            return 10;
        }
        else if (Math.abs(d-7.4) <= 0.000001)
        {
            return 11;
        }
        else if (Math.abs(d-7.5) <= 0.000001)
        {
            return 12;
        }
        else if (Math.abs(d-7.6) <= 0.000001)
        {
            return 13;
        }
        else if (Math.abs(d-7.7) <= 0.000001)
        {
            return 14;
        }
        else if (d > 8.0)
        {
            return 15;
        }
        else
            return 16;
    }
    
    /**
     * This method will write the values of the probability of the knot occurring to an excel-readable file
     * @throws IOException if there is an error generating file
     */
    public void writeRawData() throws IOException
    {
        FileWriter fileWriter = new FileWriter("DataRaw.xls");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i = 0; i < allProb.size(); i++)
        {
            String line = "";
            for(int j = 0; j < allProb.get(i).size(); j++)
            {
                int occur =  allProb.get(i).get(j);
                line += occur + "\t";
            }
            printWriter.println(line); 
        }
        
        printWriter.close();
    }
    
    /**
     * This method will write the values of the probability of the knot occurring to an excel-readable file
     * @throws IOException if there is an error generating file
     */
    public void writeFile() throws IOException
    {
        FileWriter fileWriter = new FileWriter("Data.xls");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i = 0; i < allProb.size(); i++)
        {
            String line = "";
            for(int j = 0; j < allProb.get(i).size(); j++)
            {
                double occur =  (double) allProb.get(i).get(j)/500;
                double finalVal = Math.round(occur * 1000.0) / 1000.0;
                line += finalVal + "\t";
            }
            printWriter.println(line); 
        }
        
        printWriter.close();
    }
    
    /**
     * This method will write the normalized values of the probability of the knot occurring to an 
     * excel-readable file. It will normalize the data by subtracting the number of times unknown
     * and knots more complicated than 7.7 occur.
     * @throws IOException if there is an error generating file
     */
    public void writeNormalized() throws IOException
    {
        ArrayList<Integer> aboveNormal = allProb.get(allProb.size()-2);
        ArrayList<Integer> unknown = allProb.get(allProb.size()-1);
        
        
        FileWriter fileWriter = new FileWriter("DataNormalized.xls");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i = 0; i < 15; i++)
        {
            String line = "";
            for(int j = 0; j < 15; j++)
            {
                int total = 500 - aboveNormal.get(i) - unknown.get(i);
                double occur = (double) allProb.get(i).get(j) / total;
                line += occur + "\t";
            }
            printWriter.println(line); 
        }
        
        printWriter.close();
        
    }
    
}
