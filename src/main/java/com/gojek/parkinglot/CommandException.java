/**
 * 
 */
package com.gojek.parkinglot;

/**
 * @author pracgupta
 *
 */
public class CommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CommandException(String message){
		super(message);
	}
}
