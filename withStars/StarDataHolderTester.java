package withStars;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This tester will analyze a given text file of data
 * @author Jeffrey Yang
 * @version July 22, 2018
 */
public class StarDataHolderTester {

    public static void main(String [] args) throws ScanErrorException, IOException
    {
        FileInputStream inStream = new FileInputStream(new File("FinalCreData.dat"));
        StarDataHolder allDat = new StarDataHolder();
        NumScanner sc = new NumScanner(inStream, allDat);
        
        allDat.initialize();
        
        for(int i = 0; i < 27; i++)
        {
            for(int j = 0; j < 500; j++)
            {
                KnotTransition curr = sc.nextKnot();
                ArrayList<ArrayList<Integer>> tempList = (ArrayList<ArrayList<Integer>>) allDat.getList();
                int location = allDat.getLocation(curr);
                ArrayList<Integer> temp = tempList.get(location);
                temp.set(i, temp.get(i) + 1);
            }
        }
        allDat.writeRawData();
        allDat.writeFile();
        allDat.writeNormalized();
        
        
    }
}