package com.gojek.parkinglot;



import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommandMapTest {

	static CommandMap commandMap;
	
	@BeforeClass
	public static void setup() throws CommandException{
		commandMap = new CommandMap();
	}
	
	@Test
	public void testGetMethod(){
		Assert.assertFalse(commandMap.commandMap.isEmpty());
		Assert.assertNotNull(commandMap.getMethod(CommandMap.CREATE_PARKING_LOT_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.LEAVE_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.PARK_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.REG_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.SLOT_NUMBER_FOR_REG_NO_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND));
		Assert.assertNotNull(commandMap.getMethod(CommandMap.STATUS_COMMAND));
		Assert.assertNull(commandMap.getMethod("not_defined"));
	}
	
}
