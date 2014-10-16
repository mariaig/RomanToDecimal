/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romantodecimal;

/**
 *
 * @author Maria
 */
public class InvalidCharacterException extends Exception{

    public InvalidCharacterException() {
        System.err.println("The string contains an invalid character");
    }
    
}
