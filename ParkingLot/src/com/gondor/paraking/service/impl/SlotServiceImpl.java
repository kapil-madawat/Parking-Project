package com.gondor.paraking.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gondor.paraking.service.MemberShipService;
import com.gondor.paraking.service.SlotService;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.Floor;
import com.gondor.parking.pojo.SlotInfo;

public class SlotServiceImpl implements SlotService{

	MemberShipService memberShipService = new MemberShipServiceImpl();
	
	static LinkedHashMap<String,SlotInfo> vehicleSlotMap = new LinkedHashMap<> ();
	//this is for per floor a list of slots
	static TreeMap<Integer,List<String>> freeSlots = new TreeMap<Integer,List<String>>();
	
    static Map<String, String> registrationNumberSlotMap = new HashMap<String,String>();
    
    static HashSet<String> vipSlots = new HashSet<String>();
    

    static HashSet<String>  regularSlots = new HashSet<String>();
	
	public void addVehicleRegstirationNumber(String vehicleRegestrationNo,String slotId) {
		registrationNumberSlotMap.put(vehicleRegestrationNo,slotId);
	}
	
	public void removeVehicleRegistrationNumber(String vehicleRegestrationNo,String slotId) {
		registrationNumberSlotMap.remove(vehicleRegestrationNo);
	}

	public SlotInfo getAvailableSlotForParking(VehicleType vehicleType,String vehicleRegestrationNo) {
		
		int maxFloor = Floor.getMaxFloorNumber();
		boolean isVipMember = memberShipService.isVIPMember(vehicleRegestrationNo);
		for(int i= 1; i<=maxFloor ; i++) {
			TreeMap<Integer,List<String>> freeSlotIdList1  = getFreeSlotMap();
			List<String> freeSlotIdList = freeSlots.get(i);
			if(freeSlotIdList!=null && !freeSlotIdList.isEmpty()) {
				for(String slotId : freeSlotIdList) {
					SlotInfo slotInfo = vehicleSlotMap.get(slotId);
					if(!slotInfo.isSlotFull() && vehicleType.equals(slotInfo.getVehicleType()) 
							&& slotInfo.getMaxSlotCapacity()> slotInfo.getActualCapacityOfSlot() && !slotInfo.isReservedForVIP() && !isVipMember) {
						// return slotId and slotFloor;
						return slotInfo;
					}
					else if(!slotInfo.isSlotFull() && slotInfo.getVehicleType()==null && 
							slotInfo.getMaxSlotCapacity()> slotInfo.getActualCapacityOfSlot() && !slotInfo.isReservedForVIP() && !isVipMember) {
						return slotInfo;
					}
					else if(!slotInfo.isSlotFull() && slotInfo.getVehicleType()==null && 
							slotInfo.getMaxSlotCapacity()> slotInfo.getActualCapacityOfSlot() && slotInfo.isReservedForVIP() && isVipMember) {
						return slotInfo;
					}
					else if(!slotInfo.isSlotFull() && vehicleType.equals(slotInfo.getVehicleType()) 
							&& slotInfo.getMaxSlotCapacity()> slotInfo.getActualCapacityOfSlot() && slotInfo.isReservedForVIP() && isVipMember) {
						// return slotId and slotFloor;
						return slotInfo;
					}
					
				}

			}

		}
		return null;
	}
	
	

	
	public boolean addSlotToFreeSlot(int floorNo, String slotId) {
		List<String> slotIdList = freeSlots.get(floorNo);
		boolean isAdded = slotIdList.add(slotId);
		freeSlots.put(floorNo, slotIdList);
		return isAdded;
	}
	
	public boolean removeSlotFromFreeSlot(int floorNo, String slotId) {
		List<String> slotIdList = freeSlots.get(floorNo);
		boolean isRemoved = slotIdList.remove(slotId);
		freeSlots.put(floorNo, slotIdList);	
		return isRemoved;
	}
	
	public  Map<String, String> getRegistrationNumberSlotMap() {
		return registrationNumberSlotMap;
	}
	
	public LinkedHashMap<String,SlotInfo>  getVehicleSlotMap(){
		return vehicleSlotMap;
	}
	
	public TreeMap<Integer,List<String>> getFreeSlotMap() {
		return freeSlots;
	}
	
	
	public void updateVehicleSlotMap(String slotId,SlotInfo slot) {
		vehicleSlotMap.put(slotId, slot);
	}
	
	
	public void updateFreeSlotList(int floor,List<String> slotIdList) {
		freeSlots.put(floor,slotIdList);
	}
	
	public void updateVIPslots(String slotId) {
		vipSlots.add(slotId);
	}
	
	public HashSet<String> getVIPslots() {
		return vipSlots;
	}
	
	public void updateRegularslots(String slotId) {
		regularSlots.add(slotId);
	}
	
	public HashSet<String> getRegularslots() {
		return regularSlots;
	}
	
	
	 public void printVechileSlot() {
		 LinkedHashMap<String,SlotInfo> slotMap= getVehicleSlotMap();
		 System.out.println("Vehicle  |maxSlot|currentCapcity|VIPresrved "
		 		+ " |slotFull |slotFloorNo|  slotId           |vehicleRegistrationNo");
		 System.out.println("--------------------------------------------------------------------");
		 for(Map.Entry<String, SlotInfo> entry : slotMap.entrySet()) {
			 SlotInfo slot = entry.getValue();
			 System.out.println(slot );
			 System.out.println("---------------------------------------------------------------");
		 }
		
	 }
	 
	 public void printFreeSlot() {
		 System.out.println("----- details of free slot each floor wise ---");
		 System.out.println("Floor     ||   Free slots ");
		 System.out.println("----------------------------");
		 TreeMap<Integer,List<String>> freeSlotMap = getFreeSlotMap();
		 for(Map.Entry<Integer, List<String>> entry : freeSlotMap.entrySet()) {
			 System.out.println(entry.getKey() + "         || "+ entry.getValue());
			
		 }
	 }
	
}
