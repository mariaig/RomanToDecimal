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
class InvalidNumber extends Exception {

    InvalidNumber() {

    }

    public static void showException() {
        System.err.println("The syntax of the roman number is not valid.");
    }
}
