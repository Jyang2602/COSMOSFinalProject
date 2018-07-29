package withStars;

import java.io.*;



/**
 * 
 * @author Jeffrey Yang
 * @version July 22, 2018
 *
 */
public class NumScanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    private int lineNum;
    private StarDataHolder curr;

    /**
     * Scanner constructor for construction of a scanner that uses an
     * InputStream object for input. Usage: FileInputStream inStream = new
     * FileInputStream(new File(<file name>); Scanner lex = new
     * Scanner(inStream);
     * 
     * @param inStream
     *            the input stream to use
     */
    public NumScanner(InputStream inStream, StarDataHolder data)
    {
        lineNum = 1;
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
        curr = data;
    }

    /**
     * Scanner constructor for constructing a scanner that scans a given input
     * string. It sets the end-of-file flag an then reads the first character of
     * the input string into the instance field currentChar. Usage: Scanner lex
     * = new Scanner(input_string);
     * 
     * @param inString
     *            the string to scan
     */
    public NumScanner(String inString, StarDataHolder data)
    {
        lineNum = 0;
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
        curr = data;
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream. It sets the endOfFile flag true if the end of file is reached on
     * the input stream. Otherwise, it reads the next character from the stream
     * and converts it to a Java String object. postcondition: The input stream
     * is advanced one character if it is not at end of file and the currentChar
     * instance field is set to the String representation of the character read
     * from the input stream. The flag endOfFile is set true if the input stream
     * is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1)
                eof = true;
            else
                currentChar = (char) inp;
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * If string s is equal to currentChar, currentChar will be updated to its
     * next Character, otherwise, it will throw an exception.
     * 
     * @param s is the char compared to currentChar
     * @throws ScanErrorException if currentChar and s are not the same
     */
    private void eat(char s) throws ScanErrorException
    {
        if (s == (currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new ScanErrorException("Expected " + s + " but found " + currentChar);
        }
    }
    
    /**
     * This method also increments the instance variable of the line number if the current char is 
     * an enter symbol.
     * @param s is a one input char
     * @return true if string is a space, otherwise false
     */
    private boolean isSpace(char s)
    {
        if(s == '\n')
            lineNum++;
        return s == ' ' || s == '\n' || s == '\t' || s == '\r';
    }
    
    /**
     * @param s is a one letter char
     * @return true if string is a letter between a and z in lower case,
     *         otherwise false
     */
    private boolean isLetter(char s)
    {
        return (s >= 'A' && s <= 'Z') || (s >= 'a' && s <= 'z');
    }
    
    /**
     * @param s is a one letter input char
     * @return true if string is a number between 0 and 9, otherwise false
     */
    private boolean isDigit(char s)
    {
        return ((s >= '0' && s <= '9') || (s == '.'));
    }
    
    /**
     * This method scans the next word in the file
     * @return the next String
     * @throws ScanErrorException if file has unknown character
     */
    private String scanIdentifier() throws ScanErrorException 
    {
        String id ="";
        while (hasNextToken() && (isLetter(currentChar) || isDigit(currentChar)))
        {
            id += currentChar;
            eat(currentChar);
        }
        return id;
    }
    
    /**
     * @return true if scanner is not at lastToken
     *          else, false
     */
    public boolean hasNextToken()
    {
        return !eof;
    }
    
    /**
     * @return the nextKnot that occurs in the data with the original knot and the new one
     * @throws IOException if there is an error with the BufferedReader in DataHandler
     * @throws ScanErrorException if there is an unknown character
     */
    public KnotTransition nextKnot() throws IOException, ScanErrorException
    {
        String temp = "";

        while (hasNextToken() && isSpace(currentChar))
            eat(currentChar);
        
        if(isDigit(currentChar))
        {
            while (isDigit(currentChar))
            {
                temp += currentChar;
                eat(currentChar);
            }
            temp.replaceAll(".", ",");
            if(currentChar == '*')
            {
                eat(currentChar);
                return new KnotTransition(Double.parseDouble(temp), true);
            }
            return new KnotTransition(Double.parseDouble(temp), false);
        }
        
        if (isLetter(currentChar))
        {
            
            temp = scanIdentifier();
            if (temp.equals("unknown"))
            {
                 return new KnotTransition("s");
            }
            throw new ScanErrorException("unknown string");
        }
        else
        {
            throw new ScanErrorException("Unknown character " + currentChar);   
        }
    }
}
