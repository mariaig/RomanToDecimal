package romantodecimal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Maria
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        String pathToFile="romanNumbers.txt";   //path to a file where you have the roman numbers
        FileInputStream inputStream=null;       
        Scanner scan=null;   
        
        try{
            inputStream=new FileInputStream(pathToFile);
            scan=new Scanner(inputStream,"UTF-8");
            while(scan.hasNextLine()){
                String line=scan.nextLine();
                if(line.length()>0){
                    System.out.println(line+" to decimal is: "+RomanToDecimal.parseInput(line));
                }
            }
	}catch(IOException io){
		io.printStackTrace();
	}
    }
}
