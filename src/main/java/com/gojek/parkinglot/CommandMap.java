/**
 * 
 */
package com.gojek.parkinglot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.gojek.parkinglot.ParkingLot;

/**
 * @author pracgupta
 *
 */
public class CommandMap {

	HashMap<String, Method> commandMap = new HashMap<String, Method>();
	static final String CREATE_PARKING_LOT_COMMAND = "create_parking_lot";
	static final String PARK_COMMAND = "park";
	static final String LEAVE_COMMAND = "leave";
	static final String STATUS_COMMAND = "status";
	static final String REG_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND = "registration_numbers_for_cars_with_colour";
	static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND = "slot_numbers_for_cars_with_colour";
	static final String SLOT_NUMBER_FOR_REG_NO_COMMAND = "slot_number_for_registration_number";

	public CommandMap() throws CommandException {
		// Populate the command map
		try {
			populateCommandMap();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new CommandException("Invalid method");
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new CommandException("Permission denied to access method");
		}
	}

	public Method getMethod(String commandName) {
		return commandMap.get(commandName);
	}

	private void populateCommandMap() throws NoSuchMethodException,
			SecurityException {
		commandMap.put(CREATE_PARKING_LOT_COMMAND,
				ParkingLot.class.getMethod("createParkingSlot", Integer.TYPE));
		commandMap.put(PARK_COMMAND, ParkingLot.class.getMethod("parkVehicle",
				String.class, String.class));
		commandMap.put(LEAVE_COMMAND,
				ParkingLot.class.getMethod("leave", Integer.TYPE));
		commandMap.put(STATUS_COMMAND, ParkingLot.class.getMethod("status"));
		commandMap.put(REG_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND,
				ParkingLot.class.getMethod("printRegNoFromColor", String.class));
		commandMap.put(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND,
				ParkingLot.class.getMethod("printSlotsFromColor", String.class));
		commandMap.put(SLOT_NUMBER_FOR_REG_NO_COMMAND, ParkingLot.class
				.getMethod("printSlotNoFromRegNumber", String.class));
	}

}
