package com.gondor.parking.constant;

public enum VehicleType {

	BIKE("BIKE"),
	CAR("CAR");
	
	
	private String value;;
	
	
	VehicleType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
