/**
 * This 
 */
package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author pracgupta
 *
 */
public class Main {

	private static final String QUITSTRING = "q";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int argslen = args.length;
		try {
			InputParser inputParser = new InputParser();
			if (argslen == 0) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Weclome to Parking Platform!!");
				System.out.println("Enter 'q' to quit");

				// Run a infinite loop till 'q' is encountered
				while (true) {
					String input = scan.nextLine();
					if (QUITSTRING.equals(input)) {
						System.out.println("Exiting the platform!!");
						break;
					}
					if (input.isEmpty())
						continue;
					inputParser.executeCommand(input);
				}
				scan.close();
				// In case file is passed as argument
			} else if (argslen == 1) {
				inputParser.executeFile(args[0]);
			} else {
				System.out.println("Invalid arguments passed! Exiting!");
			}
		} catch (FileException fe) {
			fe.printStackTrace();
		} catch (CommandException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
