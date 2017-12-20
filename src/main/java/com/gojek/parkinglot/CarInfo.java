/**
 * 
 */
package com.gojek.parkinglot;

/**
 * @author pracgupta
 *
 */
public class CarInfo {
	private String regNumber;
	private String color;

	public CarInfo(String regNumber, String color) {
		this.regNumber = regNumber;
		this.color = color;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
