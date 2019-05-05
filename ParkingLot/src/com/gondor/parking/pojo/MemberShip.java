package com.gondor.parking.pojo;

import com.gondor.parking.constant.VehicleType;

public class MemberShip {
	
	private String name;

    private VehicleType vehicleType;

    private String memberShipId;
    
    private String VehicleNumber ;
    
    private boolean isVIP;
    
    private boolean isRepeatedCustomer ;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getMemberShipId() {
		return memberShipId;
	}

	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}

	public String getVehicleNumber() {
		return VehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		VehicleNumber = vehicleNumber;
	}

	public boolean isVIP() {
		return isVIP;
	}

	public void setVIP(boolean isVIP) {
		this.isVIP = isVIP;
	}

	public boolean isRepeatedCustomer() {
		return isRepeatedCustomer;
	}

	public void setRepeatedCustomer(boolean isRepeatedCustomer) {
		this.isRepeatedCustomer = isRepeatedCustomer;
	} 
}
