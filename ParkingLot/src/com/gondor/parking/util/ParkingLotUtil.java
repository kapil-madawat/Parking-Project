package com.gondor.parking.util;

public class ParkingLotUtil {
	
	static String units[] = {" ","First","Second","Third","Fourth","Fifth","Sixth","Seventh","Eight","Ninth","Tenth"
			,"Eleventh","Twelveth","Thirteenth","FourTeenth","Fifteenth","Sixteenth","Seventeenth","Eighteethn","Nignteenth","Twenty"};
	
	 static final String slotName = "Slot";
	 public static String generateSlotId(int floorNo,int slotNo) {
		 String floorName = units[floorNo];
		 return floorName+"Floor-"+slotName+slotNo;
	 }

}
