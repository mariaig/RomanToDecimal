package romantodecimal;

import java.util.HashMap;

/**
 *
 * @author Maria
 */
public class RomanToDecimal {

    //pairs such as (I,1),(V,5) etc will be saved in a hashmap where
    //          key is the roman number
    //          value is the decimal number 
    private static final HashMap<String, Integer> symbols = new HashMap<String, Integer>() {
        {
            put("M", 1000);
            put("E", 5000);
            put("D", 500);
            put("C", 100);
            put("L", 50);
            put("X", 10);
            put("V", 5);
            put("I", 1);

        }
    };

    private static int index(char c) {
        switch (c) {
            case 'I':
                return 0;
            case 'V':
                return 1;
            case 'X':
                return 2;
            case 'L':
                return 3;
            case 'C':
                return 4;
            case 'D':
                return 5;
            case 'M':
                return 6;
            case 'E':
                return 7;
        }
        return -1;
    }

    private static void checkIfIsValid(String character) throws InvalidCharacterException {
        if (symbols.get(character) == null) {
            throw new InvalidCharacterException();
        }
    }

    private static void checkRomanNumber(String nr) throws InvalidNumber {
        int[] vector = new int[symbols.size()];
        char currentLetter = nr.charAt(0);
        char nxtLetter = nr.charAt(1);

        int maxLeter;
        if (symbols.get(currentLetter + "") < symbols.get(nxtLetter + "")) {
            maxLeter = symbols.get(nxtLetter + "");
            vector[index(nxtLetter)] = 1;
        } else {
            maxLeter = symbols.get(currentLetter + "");
        }

        currentLetter = nxtLetter;
        for (int j = 2; j < nr.length(); j++) {
            nxtLetter = nr.charAt(j);
            int max2 = 0;
            if (symbols.get(currentLetter + "") < symbols.get(nxtLetter + "")) {
                max2 = symbols.get(nxtLetter + "");
                if (vector[index(nxtLetter)] == 1) {
                    throw new InvalidNumber();
                }
                vector[index(nxtLetter)] = 1;
            }else{
                max2=symbols.get(currentLetter+"");
            }
            if (max2 > maxLeter) {
                throw new InvalidNumber();
            }
            currentLetter = nxtLetter;
            maxLeter = max2;
        }

    }
    /*
     Static because I want to work with this function 
     without having an instance of RomanToDecimal class
     */

    public static int parseInput(String romanNr) throws InvalidCharacterException, InvalidNumber {

        if (romanNr.length() == 1) {
            //if the length is 1, you just have to return the value
            checkIfIsValid(romanNr);
            return symbols.get(romanNr);
        }

        //the roman number will pe processed from right to left so
        //I have to save the previous letter in case of a subtract operation
        String prevLetter = romanNr.charAt(romanNr.length() - 1) + "";
        checkIfIsValid(prevLetter);

        int decimalNr = symbols.get(prevLetter + "");

        String curLetter;
        int j = romanNr.length() - 2;
        while (j >= 0) {

            curLetter = romanNr.charAt(j) + "";
            checkIfIsValid(curLetter);

            if (symbols.get(curLetter) < symbols.get(prevLetter)) {
                //when smaller values precede larger values, 
                //the smaller values are subtracted from the larger values
                decimalNr -= symbols.get(curLetter);
            } else {
                //otherwise, they are added to the total
                decimalNr += symbols.get(curLetter);
            }
            prevLetter = romanNr.charAt(j--) + "";
        }
        checkRomanNumber(romanNr);
        return decimalNr;
    }

}
