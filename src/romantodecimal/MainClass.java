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
public class MainClass {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please introduce a roman number:");

        try {
            String romanNumber = br.readLine();
            System.out.println(romanNumber + " as decimal is: " + RomanToDecimal.parseInput(romanNumber));
            System.exit(0);
        } catch (InvalidCharacterException ex) {
            System.exit(1);
        } catch (InvalidNumber e) {
            System.exit(2);
        } catch (IOException io) {
            io.printStackTrace();
            System.exit(3);
        } catch (InvalidNrOfCharacters ex) {
            System.exit(4);
        }
        //String pathToFile="romanNumbers.txt";   //path to a file where you have the roman numbers
        //FileInputStream inputStream=null;  
        //Scanner scan=null;
        //try{
        //  inputStream=new FileInputStream(pathToFile);
        //  scan=new Scanner(inputStream,"UTF-8");
        //  while(scan.hasNextLine()){
        //      String line=scan.nextLine();
        //      if(line.length()>0){
        //          System.out.println(line+" to decimal is: "+RomanToDecimal.parseInput(line));
        //      }
        //   }
        //}catch(Exception e){
        //	e.printStackTrace();
        //}
    }
}
