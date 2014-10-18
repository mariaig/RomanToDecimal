package romantodecimal;

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
    }
}
