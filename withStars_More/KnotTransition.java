package withStars_More;

/**
 * This class will contain information about the Knot
 * @author Jeffrey Yang
 * @version July 22, 2018
 *
 */
public class KnotTransition
{
    private Double newKnot;
    private Boolean unk;
    private int occurences;
    private Boolean mirrored;
    private int numK;
    
    /**
     * 
     * @param b is the new knot
     * @param starred is the boolean for if the knot is the mirror image or not
     */
    public KnotTransition(Double b, Boolean starred, int temp)
    {
        newKnot = b;
        occurences = 0;
        unk = true;
        mirrored = starred;
        numK = temp;
    }
    
    /**
     * Initializes an unknown knot transition
     * @param str is unknown
     */
    public KnotTransition(String str, int temp)
    {   
        newKnot = (double) -1;
        unk = false;
        mirrored = false;
        numK = temp;
    }
    
    /**
     * 
     * @return new knot
     */
    public Double getNew()
    {
        return newKnot;
    }
    
    /**
     * increments the number of times knot occurred
     */
    public void increment()
    {
        occurences += 1;
    }
    
    /**
     * @return true if the knot is the mirror image
     *  otherwise, false
     */
    public boolean starred()
    {
        return mirrored;
    }
    
}
