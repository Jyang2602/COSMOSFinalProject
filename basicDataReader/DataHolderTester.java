package basicDataReader;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This tester will analyze a given text file of data
 * @author Jeffrey Yang
 * @version July 22, 2018
 */
public class DataHolderTester {

    public static void main(String [] args) throws ScanErrorException, IOException
    {
        FileInputStream inStream = new FileInputStream(new File("FinalProject_trial2.dat"));
        DataHolder allDat = new DataHolder();
        NumScanner sc = new NumScanner(inStream, allDat);
        
        allDat.initialize();
        
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 500; j++)
            {
                KnotTransition curr = sc.nextKnot();
                ArrayList<ArrayList<Integer>> tempList = (ArrayList<ArrayList<Integer>>) allDat.getList();
                Double newK = curr.getNew();
                int location = allDat.getLocation(newK);
                ArrayList<Integer> temp = tempList.get(location);
                temp.set(i, temp.get(i) + 1);
            }
        }
        allDat.writeRawData();
        allDat.writeFile();
        allDat.writeNormalized();
        
        
    }
}