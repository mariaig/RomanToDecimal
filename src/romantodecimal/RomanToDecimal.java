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
   private HashMap<String,Integer> symbols=new HashMap();

    public RomanToDecimal() {
        symbols.put("I",1);
        symbols.put("V",5);
        symbols.put("X",10);
        symbols.put("L",50);
        symbols.put("C",100);
        symbols.put("D",500);
        symbols.put("M",1000);                
    }
   
   public int parseInput(String romanNumber){
       if(romanNumber.length()==1){
           //if the length is 1, you just have to return the value
           return symbols.get(romanNumber.charAt(0));
       }
       
       //the roman number will pe processed from right to left so
       //I have to save the previous letter in case of a substract operation
       char previousLetter=romanNumber.charAt(romanNumber.length()-1);
       int decimalNumber=symbols.get(previousLetter+"");
       
       int i=romanNumber.length()-2;
       while(i>=0){
           if(symbols.get(romanNumber.charAt(i)+"")<symbols.get(previousLetter+"")){
               //When smaller values precede larger values, 
               //the smaller values are subtracted from the larger values
               decimalNumber-=symbols.get(romanNumber.charAt(i)+"");
           }else{
               //otherwise, just add the values
               decimalNumber+=symbols.get(romanNumber.charAt(i)+"");
           }
           previousLetter=romanNumber.charAt(i);
           i--;
       }
       return decimalNumber;
   }
    
}
