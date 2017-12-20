/**
 * 
 */
package com.gojek.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author pracgupta
 *
 */
public class ParkingLot {
	Map<Integer, CarInfo> parkingLotMap = new HashMap<Integer, CarInfo>();
	Map<String, Integer> regNoSlotMap = new HashMap<String, Integer>();
	Map<String, ArrayList<String>> colorRegNoListMap = new HashMap<String, ArrayList<String>>();
	ArrayList<Integer> availableSlots = new ArrayList<Integer>();
	int parkingLotSize;

	ParkingLot() {
		this.parkingLotSize = 0;
	}

	/**
	 * Create Parking slots with the number of slots. Should be called only
	 * once.
	 * 
	 * @param slots
	 * @return
	 */
	public void createParkingSlot(int slots) {

		if (parkingLotSize == 0) {
			for (int i = 1; i <= slots; i++) {
				availableSlots.add(i);
			}
			parkingLotSize = slots;
			System.out
					.println("Created a parking lot with " + slots + " slots");
		} else {
			System.out.println("Parking lot already created!");
		}
	}

	/**
	 * Park the vehicle at the given slot
	 * 
	 * @param carInfo
	 */
	public void parkVehicle(String regNo, String color) {

		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		if (availableSlots.isEmpty()) {
			System.out.println("Sorry,parking lot is full");
			return;
		}

		// Check if the car has already been allocated
		if (regNoSlotMap.containsKey(regNo)) {
			System.out.println("Car is already parked");
			return;
		}

		// Allocate the parking space nearest to the entrance
		Collections.sort(availableSlots);

		Integer selectedSlot = availableSlots.get(0);
		CarInfo carInfo = new CarInfo(regNo, color);
		ArrayList<String> regNolist;

		parkingLotMap.put(selectedSlot, carInfo);
		regNoSlotMap.put(regNo, selectedSlot);

		// Add to the existing regNoList or create a new one
		if (!colorRegNoListMap.containsKey(color))
			regNolist = new ArrayList<String>();
		else
			regNolist = colorRegNoListMap.get(color);

		regNolist.add(regNo);
		colorRegNoListMap.put(carInfo.getColor(), regNolist);

		System.out.println("Allocated slot number:" + selectedSlot);
		availableSlots.remove(0);
	}

	/**
	 * Called when car leaves the parking
	 * 
	 * @param slot
	 */
	public void leave(int slot) {
		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		// Remove entries from map
		CarInfo carInfo = parkingLotMap.get(slot);
		if (carInfo != null) {
			String regNo = carInfo.getRegNumber();
			String color = carInfo.getColor();

			regNoSlotMap.remove(regNo);
			ArrayList<String> regNoList = colorRegNoListMap.get(color);
			regNoList.remove(regNo);

			parkingLotMap.remove(slot);
		} else {
			System.out.println("No car parked in this slot");
			return;
		}

		// Free the particular slot
		if (!availableSlots.contains(slot)) {
			availableSlots.add(slot);

			System.out.println("Slot number " + slot + " is free");
		}
	}

	/**
	 * Get slot number for registration number
	 * 
	 * @param regNumber
	 */
	public void printSlotNoFromRegNumber(String regNumber) {
		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		Integer slot = regNoSlotMap.get(regNumber);
		if (slot != null)
			System.out.println(slot);
		else
			System.out.println("Not found");
	}

	/**
	 * get the registration numbers of all the vehicles with the same color
	 * 
	 * @param color
	 */
	public void printRegNoFromColor(String color) {
		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		ArrayList<String> regNoList = colorRegNoListMap.get(color);

		if (!regNoList.isEmpty()) {
			for (int i = 0; i < regNoList.size() - 1; i++) {
				System.out.print(regNoList.get(i) + ", ");
			}
			System.out.print(regNoList.get(regNoList.size() - 1));
			System.out.println();
		} else
			System.out.println("Color Not​ ​found");
	}

	/**
	 * Get the slot of all vehicles for a particular vehicle
	 * 
	 * @param color
	 */
	public void printSlotsFromColor(String color) {
		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		ArrayList<String> regNoList = colorRegNoListMap.get(color);

		if (!regNoList.isEmpty()) {
			int size = regNoList.size();
			for (int i = 0; i < size - 1; i++) {
				System.out.print(regNoSlotMap.get(regNoList.get(i)) + ", ");
			}
			System.out.print(regNoSlotMap.get(regNoList.get(size - 1)));
			System.out.println();
		} else
			System.out.println("Color Not ​found");

	}

	/**
	 * Status of the parking lot
	 */
	public void status() {
		if (parkingLotSize == 0) {
			System.out.println("Sorry,parking lot is not created");
			return;
		}

		if (availableSlots.isEmpty()) {
			System.out.println("No Slot parked");
			return;
		}

		System.out.println("Slot No\tRegistration No.\tColour");
		for (Entry<Integer, CarInfo> entry : parkingLotMap.entrySet()) {
			System.out.println(entry.getKey() + "\t"
					+ entry.getValue().getRegNumber() + "\t"
					+ entry.getValue().getColor());
		}
		System.out.println();
	}
}
