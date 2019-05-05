package com.gondor.paraking.service;


import java.util.Map;

import com.gondor.parking.pojo.MemberShip;

public interface MemberShipService {
	
	void createVIPMemberShipMap();
	
	void createRepeatCustomerMap();
	
	void updateMemberShipMap(String vehicleRegistrationNumber,MemberShip member);
	
	Map<String,MemberShip> getMemberShipMap();
	
	boolean isVIPMember(String vehicleRegistrationNumber);
	
    boolean isRepeatedCustomer(String vehicleRegistrationNumber);
	
	
	
}
