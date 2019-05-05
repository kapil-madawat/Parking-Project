package com.gondor.parking.pojo;

import java.util.TreeMap;

public class Floor {
	
	static int maxFloorNumber;
	
	public Floor(int floorNumber){
		maxFloorNumber= floorNumber;
	}
	
	public Floor(){
		
	}
	
	TreeMap<Integer,Boolean> floorInfo = new TreeMap();

	public static int getMaxFloorNumber() {
		return maxFloorNumber;
	}

	public void setMaxFloorNumber(int maxFloorNumber) {
		this.maxFloorNumber = maxFloorNumber;
	}

	public TreeMap<Integer, Boolean> getFloorInfo() {
		return floorInfo;
	}

	public void setFloorInfo(TreeMap<Integer, Boolean> floorInfo) {
		this.floorInfo = floorInfo;
	}
	
}
