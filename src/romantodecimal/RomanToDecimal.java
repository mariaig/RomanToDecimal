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
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
            put("E", 5000);
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

    private static void checkRomanNumber(String number) throws InvalidNumber {
        int[] vector = new int[symbols.size()];
        char currentLetter = number.charAt(0);
        char nextLetter = number.charAt(1);

        int maxLetter;
        if (symbols.get(currentLetter + "") < symbols.get(nextLetter + "")) {
            maxLetter = symbols.get(nextLetter+"");
            vector[index(nextLetter)] = 1;
        } else {
            maxLetter = symbols.get(currentLetter+"");
        }

        currentLetter = nextLetter;
        for (int i = 2; i < number.length(); i++) {
            nextLetter = number.charAt(i);
            int max2;
            if (symbols.get(currentLetter + "") < symbols.get(nextLetter+"")) {
                max2 = symbols.get(nextLetter+"");
                if (vector[index(nextLetter)] == 1) {
                    throw new InvalidNumber();
                }
                vector[index(nextLetter)] = 1;
            } else {
                max2 = symbols.get(currentLetter+"");
            }
            if (max2 > maxLetter) {
                throw new InvalidNumber();
            }
            currentLetter = nextLetter;
            maxLetter = max2;
        }

    }
    /*
     Static because I want to work with this function 
     without having an instance of RomanToDecimal class
     */

    public static int parseInput(String romanNumber) throws InvalidCharacterException, InvalidNumber {

        if (romanNumber.length() == 1) {
            //if the length is 1, you just have to return the value
            checkIfIsValid(romanNumber);
            return symbols.get(romanNumber);
        }

        //the roman number will pe processed from right to left so
        //I have to save the previous letter in case of a subtract operation
        String previousLetter = romanNumber.charAt(romanNumber.length() - 1) + "";
        checkIfIsValid(previousLetter);

        int decimalNumber = symbols.get(previousLetter + "");

        String currentLetter;
        int i = romanNumber.length() - 2;
        while (i >= 0) {

            currentLetter = romanNumber.charAt(i) + "";
            checkIfIsValid(currentLetter);

            if (symbols.get(currentLetter) < symbols.get(previousLetter)) {
                //when smaller values precede larger values, 
                //the smaller values are subtracted from the larger values
                decimalNumber -= symbols.get(currentLetter);
            } else {
                //otherwise, they are added to the total
                decimalNumber += symbols.get(currentLetter);
            }
            previousLetter = romanNumber.charAt(i) + "";
            i--;
        }
        checkRomanNumber(romanNumber);
        return decimalNumber;
    }

}
