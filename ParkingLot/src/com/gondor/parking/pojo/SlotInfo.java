package com.gondor.parking.pojo;

import java.util.List;

import com.gondor.parking.constant.VehicleType;

public class SlotInfo {

	private String slotId;
	private VehicleType vehicleType;
	private boolean isSlotFull;
	private int maxSlotCapacity;
	private int actualCapacityOfSlot;
	private int slotOnTheFloor;
	private List<String> registrationNoOfAllVechileOfCurrentSlot ;
	private boolean isReservedForVIP;
	
	
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public boolean isSlotFull() {
		return isSlotFull;
	}
	public void setSlotFull(boolean isSlotFull) {
		this.isSlotFull = isSlotFull;
	}
	public int getMaxSlotCapacity() {
		return maxSlotCapacity;
	}
	public void setMaxSlotCapacity(int maxSlotCapacity) {
		this.maxSlotCapacity = maxSlotCapacity;
	}
	public int getActualCapacityOfSlot() {
		return actualCapacityOfSlot;
	}
	public void setActualCapacityOfSlot(int actualCapacityOfSlot) {
		this.actualCapacityOfSlot = actualCapacityOfSlot;
	}
	public int getSlotOnTheFloor() {
		return slotOnTheFloor;
	}
	public void setSlotOnTheFloor(int slotOnTheFloor) {
		this.slotOnTheFloor = slotOnTheFloor;
	}
	public List<String> getRegistrationNoOfAllVechileOfCurrentSlot() {
		return registrationNoOfAllVechileOfCurrentSlot;
	}
	public void setRegistrationNoOfAllVechileOfCurrentSlot(List<String> registrationNoOfAllVechileOfCurrentSlot) {
		this.registrationNoOfAllVechileOfCurrentSlot = registrationNoOfAllVechileOfCurrentSlot;
	}
	public boolean isReservedForVIP() {
		return isReservedForVIP;
	}
	public void setReservedForVIP(boolean isReservedForVIP) {
		this.isReservedForVIP = isReservedForVIP;
	}
	@Override
	public String toString() {
		
		   return  vehicleType + "      | " + maxSlotCapacity + "     | "+ actualCapacityOfSlot + "            | "+ 
		   isReservedForVIP + "      | "+  isSlotFull +"  |   " + slotOnTheFloor + "         | "+slotId +"   | "+registrationNoOfAllVechileOfCurrentSlot;
		/*return "SlotInfo [slotId=" + slotId + ", vehicleType=" + vehicleType + ", isSlotFull=" + isSlotFull
				+ ", maxSlotCapacity=" + maxSlotCapacity + ", actualCapacityOfSlot=" + actualCapacityOfSlot
				+ ", slotOnTheFloor=" + slotOnTheFloor + ", registrationNoOfAllVechileOfCurrentSlot="
				+ registrationNoOfAllVechileOfCurrentSlot + ", isReservedForVIP=" + isReservedForVIP + "]";*/
	}
	
	
	
	
	
	



}
