package com.gondor.paraking.service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.SlotInfo;

public interface SlotService {
	
	public void addVehicleRegstirationNumber(String vehicleRegestrationNo,String slotId);
	
	public void removeVehicleRegistrationNumber(String vehicleRegestrationNo,String slotId) ;
	
	SlotInfo getAvailableSlotForParking(VehicleType vehicleType,String vehicleRegestrationNo);

	boolean addSlotToFreeSlot(int floorNo, String slotId);

	boolean removeSlotFromFreeSlot(int floorNo, String slotId);

	public  Map<String, String> getRegistrationNumberSlotMap();

	public LinkedHashMap<String,SlotInfo>  getVehicleSlotMap();

	public TreeMap<Integer,List<String>> getFreeSlotMap() ;

	public void updateVehicleSlotMap(String slotId,SlotInfo slot);

	public void updateFreeSlotList(int floor,List<String> slotIdList);

	public void updateVIPslots(String slotId);

	public HashSet<String> getVIPslots();

	public void updateRegularslots(String slotId);

	public HashSet<String> getRegularslots();

	public void printVechileSlot();

	public void printFreeSlot();
	
}
