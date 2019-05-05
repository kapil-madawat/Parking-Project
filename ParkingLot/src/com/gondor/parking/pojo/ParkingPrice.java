package com.gondor.parking.pojo;

import java.time.LocalDateTime;

import com.gondor.parking.constant.VehicleType;

public class ParkingPrice {
	
	private double price;
	
	private VehicleType vehicleType;
	
	private LocalDateTime date;
	
	private String vehicleRegestrationNo;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getVehicleRegestrationNo() {
		return vehicleRegestrationNo;
	}

	public void setVehicleRegestrationNo(String vehicleRegestrationNo) {
		this.vehicleRegestrationNo = vehicleRegestrationNo;
	}

}
