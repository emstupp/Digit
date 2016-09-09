
/**
 * A number representing a binary bit that can be run through a half or full adder
 * 
 * @author Eric Stuppard 
 * @version June 15, 2015
 */
public class Digit
{
    private int bit;

    /**
     * Constructor for objects of class Digit; takes an initial value of either
     * 1 or 0, prints an error otherwise
     */
    public Digit(int val)
    {        
        if ((val == 0 )||( val == 1)) {
            bit = val;
        } else {
            System.out.println("ERROR - Unknown value");
            bit = 0;
        }         
    }

    /**
     *Returns the value of the bit
     * 
     *@return  the value of the bit
     */
    public int getValue()
    {
        return bit;
    }
    
    //Sets a new value for the bit; if the value is not 0 or 1, method does nothing
    public void setValue(int value)
    {
        if ((value == 1) || (value == 0)) {
            bit = value;
        }
    }
    
    //Turns the bit into a string
    public String toString()
    {
        return "" + bit + "";
    }
    
    
    /** Executes binary arithmetic between two bits (Digits); if the value is 2,
     * returns a 1 representing the carry out
     * 
     * @param A single binary bit represented by a digit
     * @return The excess carry-out value from binary arithmetic
     */
    public Digit halfAdd(Digit b)
    {
        int digitB = b.getValue();
        Digit carryOut = new Digit(0);
        if (bit + digitB == 2) {
            bit = 0;
            b.setValue(0);
            carryOut = new Digit(1);
        }
        return carryOut;
    }
    
    /** Executes binary arithmetic between three bits (Digits); if the value is 2,
     * returns a 1 representing the carry out and sets bit to 0; 
     * 
     * If the value is 3, sets bit to 1 and returns a 1 representing the
     * carry out
     * 
     * @param Two bits represented as Digits
     * @return The excess carry-out value from binary arithmetic
     */
    public Digit fullAdd(Digit b, Digit carryIn)
    {
        Digit carryOut = new Digit(0);
        if (b.getValue() + carryIn.getValue() + bit == 2) {
            bit = 0;
            carryOut = new Digit(1);
        } else if(b.getValue() + carryIn.getValue() == 2 && bit == 1) {
            bit = 1;
            carryOut = new Digit(1);
        } else if (b.getValue()+carryIn.getValue() == 1 && bit == 0) {
            bit = 1;
            carryOut = new Digit(0);
        }
        return carryOut;
    }
}
