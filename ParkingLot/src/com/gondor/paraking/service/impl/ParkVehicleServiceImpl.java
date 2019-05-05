package com.gondor.paraking.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import com.gondor.paraking.service.ParkVehicleService;
import com.gondor.paraking.service.ParkingPriceService;
import com.gondor.paraking.service.SlotService;
import com.gondor.parking.Exception.ParkingLotException;
import com.gondor.parking.constant.Constant;
import com.gondor.parking.constant.ParkingLotError;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.Floor;
import com.gondor.parking.pojo.SlotInfo;

public class ParkVehicleServiceImpl implements ParkVehicleService{
	
	SlotService slotService =new SlotServiceImpl();
	ParkingPriceService parkingPrice =new ParkingPriceServiceImpl();
	Scanner sc=new Scanner(System.in);
	
	@Override
	public void performParkingOperation() {
		System.out.println("Enter VechileType here CAR/BIKE : ");
		String vehcileTypeString= sc.next();
		String vehcileType = vehcileTypeString.toUpperCase();  
		try {
			VehicleType vehicleType = VehicleType.valueOf(vehcileType);
			System.out.println("Enter Vechile Registration Number here : ");
			String vehicleRegestrationNo = sc.next();
			if(vehicleType!=null && vehicleRegestrationNo !=null ) {
				parkVehicle(vehicleType,vehicleRegestrationNo);

			}

		}
		catch (ParkingLotException e) {
			// TODO Auto-generated catch block
			System.out.println("=============================");
			System.err.println(e.getMessage());
			System.out.println("=============================");
		}
		catch(IllegalArgumentException e) {
			System.out.println("=====================================================================");
			System.err.println("parking for VecileType "+vehcileType +" is not avialable in parking Lot" );
			System.out.println("=====================================================================");
		}
		catch(Exception ex) {

		}
	}
	
	
	public void  parkVehicle(VehicleType vehicleType,String vehicleRegestrationNo) throws ParkingLotException
	{
		//Get the free slot nearest to entrance

		SlotInfo availableSlot= slotService.getAvailableSlotForParking(vehicleType,vehicleRegestrationNo);
		if(availableSlot==null) {
			checkParkingStatus();
		}
		LinkedHashMap<String,SlotInfo> vehicleSlotMap= slotService.getVehicleSlotMap();
		SlotInfo slotInfo = vehicleSlotMap.get(availableSlot.getSlotId());
		
		if(availableSlot != null) {
			
			if(availableSlot.getActualCapacityOfSlot()==0)
			{
			slotInfo.setActualCapacityOfSlot(1);
			slotInfo.setVehicleType(vehicleType);
			slotInfo.setRegistrationNoOfAllVechileOfCurrentSlot(new ArrayList(Arrays.asList(vehicleRegestrationNo)));
			if(VehicleType.CAR.equals(vehicleType)) {
				slotInfo.setMaxSlotCapacity(Constant.MAX_SLOT_CAPACITY_FOR_CAR);
			}
			if(VehicleType.BIKE.equals(vehicleType)) {
				slotInfo.setMaxSlotCapacity(Constant.MAX_SLOT_CAPACITY_FOR_BIKE);
			}
			
			if(slotInfo.getActualCapacityOfSlot() == Constant.MAX_SLOT_CAPACITY_FOR_BIKE && VehicleType.BIKE.equals(vehicleType)) {
				slotInfo.setSlotFull(true);
				slotService.removeSlotFromFreeSlot(slotInfo.getSlotOnTheFloor(),slotInfo.getSlotId());
			}
			if(slotInfo.getActualCapacityOfSlot()  == Constant.MAX_SLOT_CAPACITY_FOR_CAR && VehicleType.CAR.equals(vehicleType)) {
				slotInfo.setSlotFull(true);
				slotService.removeSlotFromFreeSlot(slotInfo.getSlotOnTheFloor(),slotInfo.getSlotId());
			}
			
			parkingPrice.addParkingPrice(vehicleRegestrationNo, vehicleType);
			slotService.addVehicleRegstirationNumber(vehicleRegestrationNo,slotInfo.getSlotId());
			slotService.updateVehicleSlotMap(slotInfo.getSlotId(),slotInfo);
			
		}
		
		else {
			if(VehicleType.CAR.equals(slotInfo.getVehicleType())){
				slotInfo.setActualCapacityOfSlot(slotInfo.getActualCapacityOfSlot()+1);	
				List<String> slotWiseRegistrationList =slotInfo.getRegistrationNoOfAllVechileOfCurrentSlot();
				slotWiseRegistrationList.add(vehicleRegestrationNo);
				int slotCapacity = slotInfo.getActualCapacityOfSlot();
				if(slotCapacity == Constant.MAX_SLOT_CAPACITY_FOR_CAR) {
					slotInfo.setSlotFull(true);
					slotService.removeSlotFromFreeSlot(slotInfo.getSlotOnTheFloor(),slotInfo.getSlotId());
				}
			}
			else if(VehicleType.BIKE.equals(slotInfo.getVehicleType())) {
				slotInfo.setActualCapacityOfSlot(slotInfo.getActualCapacityOfSlot()+1);	
				List<String> slotWiseRegistrationList =slotInfo.getRegistrationNoOfAllVechileOfCurrentSlot();
				slotWiseRegistrationList.add(vehicleRegestrationNo);
				int slotCapacity = slotInfo.getActualCapacityOfSlot();
				if(slotCapacity == Constant.MAX_SLOT_CAPACITY_FOR_BIKE) {
					slotInfo.setSlotFull(true);
					slotService.removeSlotFromFreeSlot(slotInfo.getSlotOnTheFloor(),slotInfo.getSlotId());
				}
			}
			parkingPrice.addParkingPrice(vehicleRegestrationNo, vehicleType);
			slotService.addVehicleRegstirationNumber(vehicleRegestrationNo,slotInfo.getSlotId());
			slotService.updateVehicleSlotMap(slotInfo.getSlotId(),slotInfo);
			
		}

    }
		System.out.println("====================================================================================================");
		System.out.println("Slot Id assign for New parking : "+slotInfo.getSlotId()+" on parking floor : "+slotInfo.getSlotOnTheFloor());
		System.out.println("------------------------------------------------------------------------------------------------------------");
  }
	
	
	private void checkParkingStatus() throws ParkingLotException {
		boolean isVIPSlotFull = isVIPslotFull();
		boolean isRegularSlotFull = isRegularSlotFull();
		if(isVIPSlotFull && isRegularSlotFull) {
			throw new ParkingLotException(ParkingLotError.PARKING_LOT_NOT_AVAILABLE_EXCEPTION);
		}
		else if(isVIPSlotFull) {
			throw new ParkingLotException(ParkingLotError.PARKING_LOT_IS_FULL_FOR_VIP);
		}
		else if(isRegularSlotFull) {
			throw new ParkingLotException(ParkingLotError.PARKING_LOT_IS_FULL_FOR_REGULAR_CUSTOMER);
		}
	}
	
	private boolean isVIPslotFull() {
		boolean isVipSlotFull = false;
		int maxFloor =  Floor.getMaxFloorNumber();
		HashSet<String> vipSlotSet= slotService.getVIPslots();

		TreeMap<Integer,List<String>> freeSlots = slotService.getFreeSlotMap();
		for(int i=1;i<=maxFloor;i++) {
			List<String> currentSlots = freeSlots.get(i);
			if(currentSlots!=null && currentSlots.isEmpty()) {
				isVipSlotFull = true;
			}
			
			for (String slotId : currentSlots) {
				if(currentSlots!=null && vipSlotSet!=null && !vipSlotSet.contains(slotId)) {
					
					isVipSlotFull = true;
				}
				else if(currentSlots!=null && vipSlotSet!=null && vipSlotSet.contains(slotId)) {
					return false;
				}
			}
			
			
			
		}
		
		return isVipSlotFull;
	}
	
	private boolean isRegularSlotFull() {
		boolean isRegularSlotFull = false;
		int maxFloor =  Floor.getMaxFloorNumber();
		HashSet<String> regularSlotSet= slotService.getRegularslots();
		//List<String> regularSlotList = new ArrayList<>(regularSlotSet);
		TreeMap<Integer,List<String>> freeSlots = slotService.getFreeSlotMap();
		for(int i=1;i<=maxFloor;i++) {
			List<String> currentSlots = freeSlots.get(i);
			if(currentSlots!=null && currentSlots.isEmpty()) {
				isRegularSlotFull = true;
			}
			for (String slotId : currentSlots) {
				if(currentSlots!=null && regularSlotSet!=null && !regularSlotSet.contains(slotId)) {
					isRegularSlotFull=true;
				}
				else if(currentSlots!=null && regularSlotSet!=null && regularSlotSet.contains(slotId)) {
					return false;
				}
			}
			
		}
		return isRegularSlotFull;
	}
	

}
