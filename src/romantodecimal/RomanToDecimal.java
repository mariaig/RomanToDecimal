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
   private static final HashMap<String,Integer> symbols=new HashMap<String,Integer>(){
       {
        put("I",1);
        put("V",5);
        put("X",10);
        put("L",50);
        put("C",100);
        put("D",500);
        put("M",1000);
       }    
   };
   
   
   
   /*
        Static because I want to work with this function 
        without having an instance of RomanToDecimal class
   */
   public static int parseInput(String romanNumber){
       
       if(romanNumber.length()==1){
           //if the length is 1, you just have to return the value
           return symbols.get(romanNumber);
       }
       
       //the roman number will pe processed from right to left so
       //I have to save the previous letter in case of a subtract operation
       char previousLetter=romanNumber.charAt(romanNumber.length()-1);
       int decimalNumber=symbols.get(previousLetter+"");
       
       int i=romanNumber.length()-2;
       while(i>=0){
           if(symbols.get(romanNumber.charAt(i)+"")<symbols.get(previousLetter+"")){
               //when smaller values precede larger values, 
               //the smaller values are subtracted from the larger values
               decimalNumber-=symbols.get(romanNumber.charAt(i)+"");
           }else{
               //otherwise, they are added to the total
               decimalNumber+=symbols.get(romanNumber.charAt(i)+"");
           }
           previousLetter=romanNumber.charAt(i);
           i--;
       }
       return decimalNumber;
   }
    
}
