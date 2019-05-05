package com.gondor.parking.factory;

import com.gondor.paraking.service.ParkVehicleService;
import com.gondor.paraking.service.impl.ParkVehicleServiceImpl;
import com.gondor.paraking.service.impl.ReleaseParkingLotServiceImpl;


public class ParkingObjectFactory {
	
	public ParkingOperation getFactoryObject(int input) {
		if(1==input) {
			return new ParkVehicleServiceImpl();
		}
		else if(2==input) {
			return new ReleaseParkingLotServiceImpl();
		}
		else {
			return null;
		}
	}
	

}
