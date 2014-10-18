package romantodecimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria
 */
public class ManageComandLineArguments {

    public static void manageArguments(String[] args) {
        if (args.length == 0) {
            return;
        }
        //Add a command line switch to disable strict syntax checking
        //-t, --type <STRICT | RELAXED> - default value is strict
        if (args[0].equals("-t") || args[0].equals("--type")) {
            if (args.length == 1) {
                //default=strict
                RomanToDecimal.setDisableSyntax(false);
            } else if (args.length == 2) {
                if (args[1].equals("STRICT")) {
                    RomanToDecimal.setDisableSyntax(false);
                } else if (args[1].equals("RELAXED")) {
                    RomanToDecimal.setDisableSyntax(true);
                } else {
                    System.err.println("Usage: -t, --type <STRICT | RELAXED>");
                }
            } else {
                System.err.println("Usage: -t, --type <STRICT | RELAXED>");
            }
        }

        if (args[0].equals("-c") || args[0].equals("--check")) {
            if (args.length == 1) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please introduce a roman number:");
                try {
                    String romanNumber = br.readLine();
                    RomanToDecimal.checkIfIsValid(romanNumber);
                    RomanToDecimal.checkRomanNumber(romanNumber);
                    if(romanNumber.length()>1){
                        RomanToDecimal.checkSubstractions(romanNumber);
                    }
                    System.out.println("valid");
                    System.exit(0);
                } catch (InvalidCharacterException | InvalidNrOfCharacters | InvalidNumber ex) {
                    System.out.println("invalid");
                    System.exit(0);
                }  catch (IOException io) {
                    //io.printStackTrace();
                    System.exit(1);
                } 
            } else {
                System.err.println("Usage: -c, --check ");
            }
        }

    }
}
