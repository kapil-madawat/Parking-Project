package com.gondor.paraking.service;

import com.gondor.parking.Exception.ParkingLotException;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.factory.ParkingOperation;

public interface ParkVehicleService extends ParkingOperation
{
	void  parkVehicle(VehicleType vehicleType,String vehicleRegestrationNo) throws ParkingLotException;
	
	
}
