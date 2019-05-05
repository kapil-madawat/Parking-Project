package com.gondor.paraking.service;

import java.util.LinkedHashMap;

import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.ParkingPrice;

public interface ParkingPriceService {
    
	double calculateParkingPrice(String vehicleRegistrationNumber) ;
	
	LinkedHashMap<String,ParkingPrice> getParkingPriceMap();
	 
    void updatePicingMap(String registrationNo,ParkingPrice parkingPrice);
     
    void addParkingPrice(String registrationNo,VehicleType vehicleType);
    
    void removeRegistrationEntry(String registrationNo);
}
