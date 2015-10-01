package indledendeprogrammering_afleveringsopgave_1.Opgaver;

public class ISBN {
    
    public static void main(String[] args)
    {
        System.out.println(checkDigit("978027379333"));        
    }
    
    public static int checkDigit (String ISBN12)
    {
        if (!ISBN12.matches("[0-9]+") || ISBN12.length() != 12) 
            return -1;
        //Check for other things
        int theGoldenNumber = 0;
        for (int i = 0; i < ISBN12.length(); i++) {
            int cNumeric = Character.getNumericValue(ISBN12.charAt(i));
            theGoldenNumber += (i % 2 == 0) ? cNumeric * 1 : cNumeric * 3;
        }
        theGoldenNumber = 10 - (theGoldenNumber % 10);        
        return theGoldenNumber;        
    }
}
