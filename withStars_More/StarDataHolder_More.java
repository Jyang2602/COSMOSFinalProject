package withStars_More;
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
public class StarDataHolder_More
{
    private List<ArrayList<Integer>> allProb;
    
    /**
     * initializes DataHolder object and the arraylist
     */
    public StarDataHolder_More()
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
        for (int i = 0; i < 29; i++)
        {
            allProb.add(new ArrayList<Integer>());
            for (int j = 0; j < 27 ; j++)
            {
                allProb.get(i).add(0);
            }
        }
    }
    
    /**
     * @param the currentKnot from the reader file
     * @return the vertical location of the knot
     */
    public int getLocation(KnotTransition k)
    {
        int location;
        if (Math.abs(k.getNew()-0.1) <= 0.000001)
        {
            location = 0;
        }
        else if (Math.abs(k.getNew()-3.1) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 2;
            else 
                location = 1;
        }
        else if (Math.abs(k.getNew()-4.1) <= 0.000001)
        {
            location =  3;
        }
        else if (Math.abs(k.getNew()-5.1) <= 0.000001)
        {
            if (k.starred())
            {
                location = 5;
            }
            else
                location = 4;
        }
        else if (Math.abs(k.getNew()-5.2) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 7;
            else
                location = 6; 
        }
        else if (Math.abs(k.getNew()-6.1) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 9;
            else
                location = 8;
        }
        else if (Math.abs(k.getNew()-6.2) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 11;
            else
                location = 10;
        }
        else if (Math.abs(k.getNew()-6.3) <= 0.000001)
        {
            location =  12;
        }
        else if (Math.abs(k.getNew()-7.1) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 14;
            else 
                location = 13;
        }
        else if (Math.abs(k.getNew()-7.2) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 16;
            else
                location = 15;
        }
        else if (Math.abs(k.getNew()-7.3) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 18;
            else
                location = 17;
        }
        else if (Math.abs(k.getNew()-7.4) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 20;
            else
                location = 19;
        }
        else if (Math.abs(k.getNew()-7.5) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 22;
            else
                location = 21;
        }
        else if (Math.abs(k.getNew()-7.6) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 24;
            else
                location = 23;
        }
        else if (Math.abs(k.getNew()-7.7) <= 0.000001)
        {
            if (k.starred()) // do manually
                location = 26;
            else 
                location = 25;
        }
        else if (k.getNew() > 8.0)
        {
            location = 27;
        }
        else
        {
            location = 28;
        }
        return location;
    }
    
    /**
     * This method will write the values of the probability of the knot occurring to an excel-readable file
     * @throws IOException if there is an error generating file
     */
    public void writeRawData() throws IOException
    {
        FileWriter fileWriter = new FileWriter("DataRawMoreTrials.xls");
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
        FileWriter fileWriter = new FileWriter("DataMoreTrials.xls");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i = 0; i < allProb.size(); i++)
        {
            String line = "";
            for(int j = 0; j < allProb.get(i).size(); j++)
            {
                double occur =  (double) allProb.get(i).get(j)/2000;
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
        
        
        FileWriter fileWriter = new FileWriter("DataNormalizedMoreTrials.xls");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i = 0; i < 27; i++)
        {
            String line = "";
            for(int j = 0; j < 27; j++)
            {
                int total = 2000 - aboveNormal.get(i) - unknown.get(i);
                double occur = (double) allProb.get(i).get(j) / total;
                line += occur + "\t";
            }
            printWriter.println(line); 
        }
        
        printWriter.close();
        
    }
    
}
