package withStars;

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
    
    /**
     * 
     * @param b is the new knot
     * @param starred is the boolean for if the knot is the mirror image or not
     */
    public KnotTransition(Double b, Boolean starred)
    {
        newKnot = b;
        occurences = 0;
        unk = true;
        mirrored = starred;
    }
    
    /**
     * Initializes an unknown knot transition
     * @param str is unknown
     */
    public KnotTransition(String str)
    {   
        newKnot = (double) -1;
        unk = false;
        mirrored = false;
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
