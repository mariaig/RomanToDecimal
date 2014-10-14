package romantodecimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Maria
 */
public class MainClass {
    
    public static void main(String[] args) {
        
       RomanToDecimal rtd=new RomanToDecimal();
       
       try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            System.out.println("Introdu numarul in cifre romane:");
            while((input=br.readLine())!=null){
                if(input.length()==0){
                    //end at a second enter
                    break;
                }
                
		System.out.println("In cifre zecimale este: " + rtd.parseInput(input));
            }
	}catch(IOException io){
		io.printStackTrace();
	}
    }
}
