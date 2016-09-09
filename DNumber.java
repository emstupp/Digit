import java.util.ArrayList;
import java.lang.Math;
/**
 * A number in decimal format that can be represented by a string of 8 Digits (bits)
 * 
 * @author Eric Stuppard 
 * @version June 15, 2014
 */
public class DNumber
{
    // Initializes DNumber as a collection of Digit objects
    private ArrayList<Digit> bits;

    /**
     * Constructor for DNumber, initializes string of eight digits (0).
     */
    public DNumber()
    {
        //Initializes DNumber() to be an ArrayList containing eight 0's
        for (int i=bits.size(); i<8;i++) { 
            bits.add(new Digit(0));
        }
    }
    
    /** 
     * Converts decimalVal to its binary equivalent in the form of an ArrayList
     * by adding 1's to the list in the appropriate places; fills out the rest
     * of the list with 0's (if positive) or 1's (if negative)
     */
    public DNumber(int decimalVal)
    {
        this.bits = new ArrayList<Digit>();
        
        //If decimalVal / 2 has a remainder, adds 1 to the ArrayList
        if (decimalVal > 0) {
            while (decimalVal > 0) {
                Digit bit = new Digit(decimalVal % 2);
                bits.add(bit);
                decimalVal = decimalVal / 2;
            }
            for (int i = bits.size(); i<8; i++) {
            bits.add(new Digit(0));
            }
        
        //Two's complement conversion:
        } else if (decimalVal < 0) {
            //Make the number positive, perform the same operations as previously
            decimalVal = decimalVal * -1;
            while (decimalVal > 0) {
                Digit bit = new Digit(decimalVal % 2);
                bits.add(bit);
                decimalVal = decimalVal / 2;
            }
            
            //Flips the bits, then adds 1 immediately after
            for (Digit bit : bits) {
                if (bit.getValue() == 1) {
                    bit.setValue(0);
                } else if (bit.getValue() == 0) {
                    bit.setValue(1);
                }
            }
            this.add(new DNumber(1));
            
            for (int i = bits.size(); i<8; i++) {
            bits.add(new Digit(1));
            }
        }         
               
    }

    /**
     * Converts each DNumber to a string representing its 8 Digits (bits)
     * 
     *@return     the binary equivalent of the decimal value entered 
     *             in the DNumber constructor
     */
    public String toString()
    {
        String stringBits = "";
        //Initializes stringBits to have length of 8
        int zeros = 8 - bits.size();
        
        //Adds 0 to stringBits for each Digit not represented
        for (int i = 0; i < zeros; i++) {
            stringBits += "0";
        }
        
        //Gets the value of each Digit in DNumber ArrayList
        //Adds its value to stringBits
        if (this.getDecimalValue() < 256) {
            for (int i = bits.size() - 1; i  >= 0; i--) {
                stringBits += bits.get(i);
            }
            
         //Accounts for overflow
        } else if (this.getDecimalValue() >= 256) {
            stringBits = "0000000";
        }
            
        return stringBits;
    }
    
    
    /**
     * Converts a positive DNumber to a decimal value
     * 
     * @return the positive decimal equivalent of a DNumber ArrayList
     */
    public int getDecimalValue()
    {
        int decVal = 0;
        
        for (int i = bits.size()-1; i >= 0; i--) {
            /**If index value is 1, adds two raised to the power of that
             * index value to decVal total
             */
            if (bits.get(i).getValue() == 1) {
                decVal += Math.pow(2,i);
            }
        }
        return decVal;
    }
    
    
    /** Gets the decimal value of a negative DNumber
     * 
     * @return the negative number representing the 2C value
     */
    public int getNegValue()
    {
              
        //Converts the 2C number back to normal notation
        for (Digit bit : bits) {
                if (bit.getValue() == 1) {
                    bit.setValue(0);
                } else if (bit.getValue() == 0) {
                    bit.setValue(1);
                }
        }
        this.add(new DNumber(1));
        
        return (this.getDecimalValue() * -1);
    }
   

    /** Adds the inputted DNumber to the one for which the method was
     * called using binary arithmetic
     * 
     * @param a separate DNumber
     */
    public void add(DNumber b)
    {
        Digit carryIn = new Digit(0);
        for (int i = 0; i<bits.size(); i++) {
            Digit bAdd = bits.get(i);
            Digit otherAdd = b.bits.get(i);
            carryIn = bAdd.fullAdd(otherAdd,carryIn);
        }
    }
    
    /** Tests to see if the inputted DNumber is equivalent to the one for
     * which the method was called
     * 
     * @param A DNumber separate from the one on which the method was called
     * @return true or false, depending on whether or not the two DNumbers
     *         are equal
     */
    public boolean equals(DNumber value)
    {
        if (this.toString().equals(value.toString())) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Compares the decimal values of two different DNumbers returns 0 if they are equal, -1 if the parameter
     * (other) is larger, and 1 if the parameter (other) is smaller
     * 
     * @param A DNumber separate from the one on which the method was called
     * @return 0 if they are equal, -1 if the parameter (other) is larger, and 
     *         1 if the parameter (other) is smaller
     */
    public int compareTo(DNumber other)
    {
        int otherVal = 0;
        if (other.getDecimalValue() < this.getDecimalValue()) {
            otherVal = 1;
        } else if (other.getDecimalValue() > this.getDecimalValue()) {
            otherVal = -1;
        }
        return otherVal;
    }
    
}
