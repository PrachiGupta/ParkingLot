package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InputParser {
	CommandMap commandMap;
	ParkingLot parkingLot;

	public InputParser() throws CommandException {
		parkingLot = new ParkingLot();
		commandMap = new CommandMap();
	}

	public void executeCommand(String input) throws CommandException {

		if (input == null || input.isEmpty())
			throw new CommandException("Command is empty");

		String commandInput[] = input.split("\\s+");

		try {
			Method m = this.commandMap.getMethod(commandInput[0]);

			if (m != null) {
				Class[] parameters = m.getParameterTypes();

				if (parameters.length == commandInput.length - 1) {
					switch (parameters.length) {
					case 0:
						m.invoke(parkingLot);
						break;
					case 1:
						if (parameters[0] == int.class)
							m.invoke(parkingLot,
									Integer.parseInt(commandInput[1]));
						else
							m.invoke(parkingLot, commandInput[1]);
						break;
					case 2:
						m.invoke(parkingLot, commandInput[1], commandInput[2]);
						break;
					default:
						System.out
								.println("Invalid arguments passed for the command");
						break;
					}
				} else
					System.out
							.println("Invalid arguments passed for the command");
			} else
				System.out.println("No Method found!");

		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException occurred!" + e);
			throw new CommandException("Invalid Command");
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException occurred!" + e);
			throw new CommandException("Invalid Command");
		} catch (InvocationTargetException e) {
			System.out.println("InvocationTargetException occurred!" + e);
			throw new CommandException("Invalid Command");
		}
	}

	/**
	 * Execute the commands in file line by line
	 * 
	 * @param fileName
	 * @throws FileException
	 * @throws CommandException
	 */
	public void executeFile(String fileName) throws FileException,
			CommandException {
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String fileLine;

			fileLine = bufferedReader.readLine();

			// Read all the commands line by line
			while (fileLine != null) {
				executeCommand(fileLine);
				fileLine = bufferedReader.readLine();
			}
			fileReader.close();
		} catch (FileNotFoundException fe) {
			System.out.println("FileNotFoundException occurred!" + fe);
			throw new FileException("File not found!");
		} catch (IOException e) {
			System.out.println("IOException occurred!" + e);
			throw new FileException("Error in reading file!");
		}
	}

}
