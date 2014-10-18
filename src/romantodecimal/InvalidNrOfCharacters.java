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
class InvalidNrOfCharacters extends Exception {

    public InvalidNrOfCharacters() {

    }

    public static void showException() {
        System.err.println("More the 3 letters of the same kind.");
    }
}
