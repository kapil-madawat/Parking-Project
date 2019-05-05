package com.gondor.paraking.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.gondor.paraking.service.MemberShipService;
import com.gondor.paraking.service.ParkingPriceService;
import com.gondor.paraking.service.ReleaseParkingLotService;
import com.gondor.paraking.service.SlotService;
import com.gondor.parking.Exception.ParkingLotException;
import com.gondor.parking.constant.Constant;
import com.gondor.parking.constant.ParkingLotError;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.factory.ParkingOperation;
import com.gondor.parking.pojo.SlotInfo;

public class ReleaseParkingLotServiceImpl implements ReleaseParkingLotService {
	
	
	SlotService slotService =new SlotServiceImpl();
	
	ParkingPriceService parkParkingService =new ParkingPriceServiceImpl();
	
	MemberShipService memberShip = new MemberShipServiceImpl();
	
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
		    		removeVehicleFromParking(vehicleType,vehicleRegestrationNo);
		    }
		}
		catch(ParkingLotException ex) {
			System.out.println("===============================");
			System.err.println(ex.getMessage());
			System.out.println("===============================");
		}
		catch(IllegalArgumentException e) {
			System.out.println("=====================================================================");
			System.err.println("parking for VecileType "+vehcileType +" is not avialable in parking Lot" );
			System.out.println("=====================================================================");
		}
		catch(Exception ex) {
			System.out.println();
		}
		
			
		
	}
	
	public void removeVehicleFromParking(VehicleType vehicleType,String vehicleRegestrationNo) throws ParkingLotException
	{
		Map<String, String> registrationNumberSlotMap = slotService.getRegistrationNumberSlotMap();
		LinkedHashMap<String,SlotInfo> vechileSlotMap= slotService.getVehicleSlotMap();
		String slotId = registrationNumberSlotMap.get(vehicleRegestrationNo);
		SlotInfo slotInfo = vechileSlotMap.get(slotId);
		if(slotInfo==null || !vehicleType.equals(slotInfo.getVehicleType())) {
			throw new ParkingLotException(ParkingLotError.NO_VECHILE_WITH_THIS_REGISTRATION_NO);
		}
		
		int floorNumber = slotInfo.getSlotOnTheFloor();
		
		if(VehicleType.CAR.equals(slotInfo.getVehicleType())){
			slotInfo.setActualCapacityOfSlot(slotInfo.getActualCapacityOfSlot()-1);
			List<String> slotWiseRegistrationList =slotInfo.getRegistrationNoOfAllVechileOfCurrentSlot();
			slotWiseRegistrationList.remove(vehicleRegestrationNo);
			if(slotInfo.getActualCapacityOfSlot() > 0 && Constant.MAX_SLOT_CAPACITY_FOR_CAR > slotInfo.getActualCapacityOfSlot()) {
				slotInfo.setSlotFull(false);
			}
			
			else if( slotInfo.getActualCapacityOfSlot() == 0) {
				slotInfo.setMaxSlotCapacity(Constant.MAX_SLOT_CAPACITY_FOR_CAR);
				slotInfo.setSlotFull(false);
				slotInfo.setVehicleType(null);
				
			}
		}
		else if(VehicleType.BIKE.equals(slotInfo.getVehicleType())) {
			slotInfo.setActualCapacityOfSlot(slotInfo.getActualCapacityOfSlot()-1);	
			int slotCapacity = slotInfo.getActualCapacityOfSlot();
			List<String> slotWiseRegistrationList =slotInfo.getRegistrationNoOfAllVechileOfCurrentSlot();
			slotWiseRegistrationList.remove(vehicleRegestrationNo);
			if(slotInfo.getActualCapacityOfSlot() > 0 && Constant.MAX_SLOT_CAPACITY_FOR_BIKE >slotInfo.getActualCapacityOfSlot()) {
				slotInfo.setSlotFull(false);
			}
			else if( slotInfo.getActualCapacityOfSlot() == 0) {
				slotInfo.setMaxSlotCapacity(Constant.MAX_SLOT_CAPACITY_FOR_CAR);
				slotInfo.setSlotFull(false);
				slotInfo.setVehicleType(null);
			}
			
		}
		
		double totalParkingPrice = parkParkingService.calculateParkingPrice(vehicleRegestrationNo);
		slotService.updateVehicleSlotMap(slotInfo.getSlotId(),slotInfo);
		slotService.addSlotToFreeSlot(floorNumber,slotInfo.getSlotId());
		slotService.removeVehicleRegistrationNumber(vehicleRegestrationNo,slotInfo.getSlotId());
		boolean isRepeatedCustomer = memberShip.isRepeatedCustomer(vehicleRegestrationNo);
		parkParkingService.removeRegistrationEntry(vehicleRegestrationNo);
		if(isRepeatedCustomer) {
			System.out.println("============================================================================================================================================");
			System.out.println(" Releasing slotId "+slotInfo.getSlotId()+" On Floor "+ floorNumber);
			System.out.println(" As you are our repeated Customer, so you get 30rs discount in parking charges so your total parking Charge is : "+totalParkingPrice);
			System.out.println("============================================================================================================================================");
		}
		else {
			System.out.println("============================================================================================================================================");
			System.out.println(" Releasing slotId : "+slotInfo.getSlotId()+" On Floor : "+ floorNumber + " and total parking price is : "+totalParkingPrice);
			
			
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}


}
