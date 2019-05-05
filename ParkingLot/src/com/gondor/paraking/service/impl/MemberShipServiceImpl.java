package com.gondor.paraking.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gondor.paraking.service.MemberShipService;
import com.gondor.parking.constant.VehicleType;
import com.gondor.parking.pojo.MemberShip;

public class MemberShipServiceImpl implements MemberShipService{
	
public static Map<String,MemberShip> vipMemberShipMap =new HashMap();
	
	public static Map<String,MemberShip> repeatCustomerMemberShipMap =new HashMap();
	
	public void createVIPMemberShipMap() {
		for(int i=1;i<=6;i++) {
			MemberShip member=new MemberShip();
			member.setName("Member"+"-"+i);
			member.setVehicleNumber("KA05-"+"A"+i);
			member.setVIP(true);
			member.setVehicleType(VehicleType.CAR);
			vipMemberShipMap.put(member.getVehicleNumber(), member);
		}
	}
	
	
	public void createRepeatCustomerMap() {
		for(int i=1;i<=4;i++) {
			MemberShip member=new MemberShip();
			member.setName("Member"+"-"+i);
			member.setVehicleNumber("KA06-"+"A"+i);
			member.setRepeatedCustomer(true);
			member.setVehicleType(VehicleType.CAR);
			repeatCustomerMemberShipMap.put(member.getVehicleNumber(), member);
		}
	}
	
	
	public void updateMemberShipMap(String vehicleRegistrationNumber,MemberShip member) {
		vipMemberShipMap.put(vehicleRegistrationNumber, member);
	}
	
	public Map<String,MemberShip> getMemberShipMap(){
		return vipMemberShipMap;
	}
	
	public boolean isVIPMember(String vehicleRegistrationNumber) {
		boolean isVIP =false;
		MemberShip member = vipMemberShipMap.get(vehicleRegistrationNumber);
		if(member!=null) {
			isVIP = member.isVIP();
		}
		return isVIP;
	}

	public boolean isRepeatedCustomer(String vehicleRegistrationNumber) {
		boolean isRepeatedCustomer =false;
		MemberShip member = repeatCustomerMemberShipMap.get(vehicleRegistrationNumber);
		if(member!=null) {
			isRepeatedCustomer = member.isRepeatedCustomer();
		}
		return isRepeatedCustomer;
	}
	

}
