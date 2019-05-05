package com.gondor.paraking.service.impl;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import com.gondor.paraking.service.MemberShipService;
import com.gondor.paraking.service.ParkingPriceService;
import com.gondor.parking.constant.Constant;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.ParkingPrice;

public class ParkingPriceServiceImpl implements ParkingPriceService{

	static LinkedHashMap<String,ParkingPrice> parkingPriceMap = new LinkedHashMap<>();
	
	MemberShipService memberShipService =new MemberShipServiceImpl();
	
	public double calculateParkingPrice(String vehicleRegistrationNumber) {
		ParkingPrice priceObject =  parkingPriceMap.get(vehicleRegistrationNumber);
		long diff = LocalDateTime.now().getHour() - priceObject.getDate().getHour();
		double totalParkingPrice = 0.0;
		boolean isRepeatedCustomer= memberShipService.isRepeatedCustomer(vehicleRegistrationNumber);
        if (VehicleType.BIKE.equals(priceObject.getVehicleType())) {
            if (diff > 12) {
            	totalParkingPrice =  Constant.DEFAULT_PARKING_PRICE_FOR_BIKE + (diff - 12) * 30;
            } else {
            	totalParkingPrice = Constant.DEFAULT_PARKING_PRICE_FOR_BIKE;
            }
        }

        else  if (VehicleType.CAR.equals(priceObject.getVehicleType())) {
            if (diff > 12) {
            	totalParkingPrice = Constant.DEFAULT_PARKING_PRICE_FOR_CAR + (diff - 12) * 50;
            } else {
            	totalParkingPrice = Constant.DEFAULT_PARKING_PRICE_FOR_CAR;
            }
        }
        
		if(isRepeatedCustomer && totalParkingPrice > 0) {
			totalParkingPrice = totalParkingPrice - Constant.REPEATED_CUSTOMER_DISCOUNT;
		}
        
        return totalParkingPrice;
	}

	
	public LinkedHashMap<String,ParkingPrice> getParkingPriceMap(){
		return parkingPriceMap;
	}
	
	public void updatePicingMap(String registrationNo,ParkingPrice parkingPrice) {
		parkingPriceMap.put(registrationNo, parkingPrice);
	}
	
	public void addParkingPrice(String registrationNo,VehicleType vehicleType) {
		ParkingPrice parkingPrice = new ParkingPrice();
		parkingPrice.setVehicleType(vehicleType);
		parkingPrice.setVehicleRegestrationNo(registrationNo);
		parkingPrice.setDate(LocalDateTime.now().now());
		if(VehicleType.CAR.equals(vehicleType)) {
			parkingPrice.setPrice(Constant.DEFAULT_PARKING_PRICE_FOR_CAR);
		}
		else if(VehicleType.BIKE.equals(vehicleType)) {
			parkingPrice.setPrice(Constant.DEFAULT_PARKING_PRICE_FOR_BIKE);
		}
		updatePicingMap(registrationNo, parkingPrice);
	}
	
	public void removeRegistrationEntry(String registrationNo) {
		parkingPriceMap.remove(registrationNo);
	}
	
}
