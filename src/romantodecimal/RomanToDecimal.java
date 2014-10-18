package romantodecimal;

import java.util.HashMap;

/**
 *
 * @author Maria
 */
public class RomanToDecimal {
    private static final int nrOfSymbols=9;
    private static int[] letterCounter = new int[nrOfSymbols];
    private static boolean disableSyntax = false;

    //pairs such as (I,1),(V,5) etc will be saved in a hashmap where
    //          key is the roman number
    //          value is the decimal number 
    private static final HashMap<String, Float> symbols = new HashMap<String, Float>() {
        {
            put("M", 1000f);
            // put("E", 5000);
            put("D", 500f);
            put("C", 100f);
            put("L", 50f);
            put("X", 10f);
            put("V", 5f);
            put("I", 1f);
            
            put("S", 1 / 2f);
            put("*", 1 / 12f);

        }
    };

    public static void checkIfIsValid(String romanNumber) throws InvalidCharacterException, InvalidNrOfCharacters {
        for (int i=0;i<romanNumber.length();i++) {
            if (symbols.get(romanNumber.charAt(i)+"") == null) {
                throw new InvalidCharacterException();
            }
            checkNrOfOccurences(romanNumber.charAt(i));
        }  
    }

    private static void checkNrOfOccurences(char c) throws InvalidNrOfCharacters {
        letterCounter[index(c)]++;
        if (letterCounter[index(c)] > 3) {
            throw new InvalidNrOfCharacters();
        }
    }

    private static void checkValidSubstraction(String s1, String s2) throws InvalidNumber {
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

    public static void checkSubstractions(String romanNr) throws InvalidNumber{
        
        String prevLetter = romanNr.charAt(romanNr.length() - 1) + "";
        //Float decimalNr = symbols.get(prevLetter + "");
        String curLetter;
        int j = romanNr.length() - 2;
        while (j >= 0) {
            curLetter = romanNr.charAt(j) + "";
            if (symbols.get(curLetter) < symbols.get(prevLetter)) {
                //when smaller values precede larger values, 
                //the smaller values are subtracted from the larger values
                if (!disableSyntax) {
                    checkValidSubstraction(curLetter, prevLetter);
                }
            } 
            prevLetter = romanNr.charAt(j--) + "";
        }
    }
    public static void checkRomanNumber(String nr) throws InvalidNumber, InvalidNrOfCharacters {
        
        int[] vector = new int[symbols.size()];
        char currentLetter = nr.charAt(0);
        char nxtLetter = nr.charAt(1);

        Float maxLeter;
        if (symbols.get(currentLetter + "") < symbols.get(nxtLetter + "")) {
            maxLeter = symbols.get(nxtLetter + "");
            vector[index(nxtLetter)] = 1;
        } else {
            maxLeter = symbols.get(currentLetter + "");
        }

        currentLetter = nxtLetter;
        for (int j = 2; j < nr.length(); j++) {
            nxtLetter = nr.charAt(j);
            Float max2;
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

    public static Float parseInput(String romanNr) throws InvalidCharacterException, InvalidNumber, InvalidNrOfCharacters {
        if (romanNr.length() == 1) {
            //if the length is 1, you just have to return the value
            if (!disableSyntax) {
                checkIfIsValid(romanNr);
            }
            return symbols.get(romanNr);
        }
        
        if (!disableSyntax) {
            checkIfIsValid(romanNr);
            checkRomanNumber(romanNr);
            checkSubstractions(romanNr);
        }
        
        
        //the roman number will pe processed from right to left so
        //I have to save the previous letter in case of a subtract operation
        String prevLetter = romanNr.charAt(romanNr.length() - 1) + "";
        Float decimalNr = symbols.get(prevLetter + "");
        String curLetter;
        int j = romanNr.length() - 2;
        while (j >= 0) {
            curLetter = romanNr.charAt(j) + "";
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
        return decimalNr;
    }

    public static char convertFromString(String s) {
        // if (s.contains("E")) {
        //     return 'E';
        //}
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
        if (s.contains("S")) {
            return 'S';
        }
        if (s.contains("*")) {
            return '*';
        }
        return 'A';
    }

    private static int index(char c) {
        switch (c) {
            case '*':
                return 0;
            case 'S':
                return 1;
            case 'I':
                return 2;
            case 'V':
                return 3;
            case 'X':
                return 4;
            case 'L':
                return 5;
            case 'C':
                return 6;
            case 'D':
                return 7;
            case 'M':
                return 8;

            //case 'E':
            //    return 7;
        }
        return -1;
    }

    public static boolean getDisableSyntax() {
        return disableSyntax;
    }

    public static void setDisableSyntax(boolean disable) {
        RomanToDecimal.disableSyntax = disable;
    }
}
