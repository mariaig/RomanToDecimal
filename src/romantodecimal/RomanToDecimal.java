package romantodecimal;

import java.util.HashMap;

/**
 *
 * @author Maria
 */
public class RomanToDecimal {

    private static int[] letterCounter = new int[8];
    private static boolean disableSyntax = false;

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

    private static void checkIfIsValid(String character) throws InvalidCharacterException, InvalidNrOfCharacters {
        if (symbols.get(character) == null) {
            throw new InvalidCharacterException();
        }
        checkNrOfOccurences(convertFromString(character));
    }

    private static void checkNrOfOccurences(char c) throws InvalidNrOfCharacters {
        letterCounter[index(c)]++;
        if (letterCounter[index(c)] > 3) {
            throw new InvalidNrOfCharacters();
        }
    }

    public static char convertFromString(String s) {
        if (s.contains("E")) {
            return 'E';
        }
        if (s.contains("M")) {
            return 'M';
        }
        if (s.contains("D")) {
            return 'D';
        }
        if (s.contains("C")) {
            return 'C';
        }
        if (s.contains("L")) {
            return 'L';
        }
        if (s.contains("X")) {
            return 'X';
        }
        if (s.contains("V")) {
            return 'V';
        }
        if (s.contains("I")) {
            return 'I';
        }
        return 'A';
    }

    public static void checkValidSubstraction(String s1, String s2) throws InvalidNumber {
        int indexPrevChar = index(convertFromString(s2));
        int indexCurChar = index(convertFromString(s1));

        if ((indexPrevChar % 2) == 0) {
            if ((indexPrevChar - indexCurChar) > 2) {
                throw new InvalidNumber();
            }
        } else {
            if ((indexPrevChar - indexCurChar) > 1) {
                throw new InvalidNumber();
            }
        }
    }

    private static void checkRomanNumber(String nr) throws InvalidNumber, InvalidNrOfCharacters {
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
            } else {
                max2 = symbols.get(currentLetter + "");
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

    public static int parseInput(String romanNr) throws InvalidCharacterException, InvalidNumber, InvalidNrOfCharacters {

        if (romanNr.length() == 1) {
            //if the length is 1, you just have to return the value
            if (!disableSyntax) {
                checkIfIsValid(romanNr);
            }
            return symbols.get(romanNr);
        }

        //the roman number will pe processed from right to left so
        //I have to save the previous letter in case of a subtract operation
        String prevLetter = romanNr.charAt(romanNr.length() - 1) + "";
        if (!disableSyntax) {
            checkIfIsValid(prevLetter);
        }
        int decimalNr = symbols.get(prevLetter + "");

        String curLetter;
        int j = romanNr.length() - 2;
        while (j >= 0) {

            curLetter = romanNr.charAt(j) + "";
            if (!disableSyntax) {
                checkIfIsValid(curLetter);
            }
            if (symbols.get(curLetter) < symbols.get(prevLetter)) {
                //when smaller values precede larger values, 
                //the smaller values are subtracted from the larger values
                if (!disableSyntax) {
                    checkValidSubstraction(curLetter, prevLetter);
                }
                
                decimalNr -= symbols.get(curLetter);
            } else {
                //otherwise, they are added to the total
                decimalNr += symbols.get(curLetter);
            }
            prevLetter = romanNr.charAt(j--) + "";
        }
        if (!disableSyntax) {
            checkRomanNumber(romanNr);
        }
        return decimalNr;
    }

    public static boolean getDisableSyntax() {
        return disableSyntax;
    }

    public static void setDisableSyntax(boolean disable) {
        RomanToDecimal.disableSyntax = disable;
    }
}
