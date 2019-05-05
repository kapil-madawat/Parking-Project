package com.gondor.paraking.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Vector;

import com.gondor.paraking.service.MemberShipService;
import com.gondor.paraking.service.ParkingLotService;
import com.gondor.paraking.service.SlotService;
import com.gondor.parking.constant.Constant;
import com.gondor.parking.constant.ParkingLotError;
import com.gondor.parking.pojo.Floor;
import com.gondor.parking.pojo.SlotInfo;
import com.gondor.parking.util.ParkingLotUtil;

public class ParkingLotServiceImpl implements ParkingLotService{


	SlotService slotService=new SlotServiceImpl();
	
	public void createParkingLot(int numberOfFloor) {
		try {
			if(numberOfFloor < 0) {
				System.out.println("===================================================");
				System.err.println(ParkingLotError.PARKING_FLOOR_NEGATIVE_ERROR);
				System.out.println("===================================================");
				return ;
			}
			if(numberOfFloor > 20) {
				System.out.println("===================================================");
				System.err.println(ParkingLotError.PARKING_FLOOR_MAX_REACH_ERROR);
				System.out.println("===================================================");
				return ; 
			}
			Floor floor = new Floor(numberOfFloor);
			MemberShipService memberShip = new MemberShipServiceImpl();
			memberShip.createVIPMemberShipMap();
			memberShip.createRepeatCustomerMap();
			int vipCount= 1;
			for(int i = 1; i <= numberOfFloor; i++)
			{
				slotService.updateFreeSlotList(i,new ArrayList());
				for(int j=1;j<=Constant.MAX_SLOT_ON_ONE_FLOOR; j++) {
					SlotInfo slotInfo = new SlotInfo();
					String slotId = ParkingLotUtil.generateSlotId(i, j);
					slotInfo.setSlotOnTheFloor(i);
					slotInfo.setSlotId(slotId);
					slotInfo.setMaxSlotCapacity(Constant.MAX_SLOT_CAPACITY_FOR_CAR);
					if(vipCount<=Constant.MAX_SLOT_FOR_VIP) {
						slotInfo.setReservedForVIP(true);
						slotService.updateVIPslots(slotId);
					}else {
						slotService.updateRegularslots(slotId);
					}
					vipCount++;
					slotService.updateVehicleSlotMap(slotId, slotInfo);
					TreeMap<Integer,List<String>> freeSlotList = slotService.getFreeSlotMap();
					List slotIdList = freeSlotList.get(i);
					slotIdList.add(slotId);
					slotService.updateFreeSlotList(i,slotIdList);
				}
			}

			System.out.println("parking lot is created with "+numberOfFloor+" no of floors");
			System.out.println("=========================================================================================");
		}
		catch(Exception ex) {
			System.out.println("====================================================");
			System.err.println(ParkingLotError.OPERATION_NOT_AVAILABLE_ERROR);
			System.out.println("=====================================================");
		}
	}


	 

	 


}
