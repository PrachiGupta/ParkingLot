package com.gojek.parkinglot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParkingLotTest {

	ParkingLot parkingLot = new ParkingLot();
	OutputStream outputStream = new ByteArrayOutputStream();

	public void setupStream() {
		System.setOut(new PrintStream(outputStream));
	}

	public void cleanStream() throws IOException {
		outputStream.close();
	}

	@Test
	public void testCreateParkingLot() {
		int slots = 6;
		parkingLot.createParkingSlot(slots);
		Assert.assertEquals(slots, parkingLot.availableSlots.size());
		Assert.assertEquals(parkingLot.parkingLotSize, slots);
	}

	@Test
	public void testParkVehicleParkingLotNotCreated() throws Exception {
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		Assert.assertEquals(0, parkingLot.parkingLotSize);
		Assert.assertEquals(0, parkingLot.availableSlots.size());
	}

	@Test
	public void testParkVehicleSuccess() throws Exception {
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		Assert.assertEquals(4, parkingLot.availableSlots.size());
		Assert.assertFalse(parkingLot.availableSlots.contains(1));
		Assert.assertFalse(parkingLot.availableSlots.contains(2));
	}

	@Test
	public void testLeave() throws Exception {
		parkingLot.leave(2);
		Assert.assertEquals(0, parkingLot.parkingLotSize);
		Assert.assertEquals(0, parkingLot.availableSlots.size());
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		parkingLot.leave(2);
		Assert.assertEquals(5, parkingLot.availableSlots.size());
		Assert.assertTrue(parkingLot.availableSlots.contains(2));
	}

	@Test
	public void testStatusNoParkingLotCreated() throws Exception {
		parkingLot.status();
		Assert.assertEquals(0, parkingLot.parkingLotSize);
		Assert.assertEquals(0, parkingLot.availableSlots.size());
	}

	@Test
	public void testStatusSuccess() throws Exception {
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		setupStream();
		parkingLot.status();
		for (Entry<Integer, CarInfo> entry : parkingLot.parkingLotMap
				.entrySet()) {
			Assert.assertTrue(outputStream
					.toString()
					.trim()
					.contains(
							entry.getKey() + "\t"
									+ entry.getValue().getRegNumber() + "\t"
									+ entry.getValue().getColor()));

		}
		cleanStream();
	}

	@Test
	public void testPrintRegNoFromColor() throws Exception {
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		parkingLot.parkVehicle("KA-01-HH-9998", "Red");
		setupStream();
		parkingLot.printRegNoFromColor("White");
		Assert.assertTrue(parkingLot.colorRegNoListMap.containsKey("White"));
		for (Map.Entry<String, ArrayList<String>> mapEntry : parkingLot.colorRegNoListMap
				.entrySet()) {
			if ("White".equals(mapEntry.getKey())) {
				Assert.assertEquals(2, mapEntry.getValue().size());
				Assert.assertEquals(mapEntry.getValue().get(0) + ","
						+ mapEntry.getValue().get(1), outputStream.toString()
						.trim().replace(" ", ""));
			}
		}
		cleanStream();
	}

	@Test
	public void testPrintSlotsFromColor() throws Exception {
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		parkingLot.parkVehicle("KA-01-HH-9998", "Red");
		setupStream();
		parkingLot.printSlotsFromColor("White");
		
		for (Map.Entry<String, ArrayList<String>> mapEntry : parkingLot.colorRegNoListMap
				.entrySet()) {
			if ("White".equals(mapEntry.getKey())) {
				Assert.assertEquals(parkingLot.regNoSlotMap.get(mapEntry.getValue().get(0)) + ","
						+ parkingLot.regNoSlotMap.get(mapEntry.getValue().get(1)), outputStream.toString()
						.trim().replace(" ", ""));
			}
		}
		
		cleanStream();
	}

	@Test
	public void testPrintSlotNumberFromRegNo() throws Exception {
		parkingLot.createParkingSlot(6);
		parkingLot.parkVehicle("KA-01-HH-1234", "White");
		parkingLot.parkVehicle("KA-01-HH-9999", "White");
		setupStream();
		parkingLot.printSlotNoFromRegNumber("KA-01-HH-1234");
		Assert.assertEquals(parkingLot.regNoSlotMap.get("KA-01-HH-1234")
				.toString(), outputStream.toString().trim().replace(" ", ""));
		cleanStream();
	}

}
