package com.gondor.parking.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.gondor.paraking.service.ParkingLotService;
import com.gondor.paraking.service.SlotService;
import com.gondor.paraking.service.impl.ParkingLotServiceImpl;
import com.gondor.paraking.service.impl.SlotServiceImpl;
import com.gondor.parking.constant.ParkingLotError;
import com.gondor.parking.factory.ParkingObjectFactory;
import com.gondor.parking.factory.ParkingOperation;

public class ParkingLot {
	
	Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		ParkingLot main =new ParkingLot();
		main.menu();
	}
	
	
	private void menu() {
		String quit = "no";
		System.out.println("******************* Welcome to Parking Lot Application ************");
		System.out.println("Enter No of Floor New parking Lot : ");
		try {
			int numberOfFloor = sc.nextInt();
			ParkingLotService impl = new ParkingLotServiceImpl();
			impl.createParkingLot(numberOfFloor);
			while(!"N".equalsIgnoreCase(quit))
			{
				menuItem();
				System.out.println("Would you want to continue Y/N ? : ");
				System.out.println("---------------------------------------------");
				quit = sc.next();
			}
		}
		catch(InputMismatchException ex) {
			System.out.println("=======================================");
			System.err.println(ParkingLotError.OPERATION_NOT_AVAILABLE_ERROR);
			System.out.println("=======================================");
		}
		
		
		
		
	}
	
	
	private void menuItem() {
		System.out.println("Enter 1 for park the vechile ");
		System.out.println("Enter 2 to realse the vechile from Parking Lot  ");
		System.out.println("Enter 3 for slot information of all the floor  ");
		System.out.println("Enter 4 for all free slot information ");
		System.out.print("Please Enter your choice : ");
		try {
			int userInput = sc.nextInt();
			ParkingObjectFactory objectFactory = new ParkingObjectFactory();
			System.out.println("-------------------------------------------");
			if(userInput>0 && userInput<3) {
				ParkingOperation parkingOperation= objectFactory.getFactoryObject(userInput);
				parkingOperation.performParkingOperation();
			}
			else if(userInput>2 && userInput<=4) {
				printInformation(userInput);
			}
			else {
				System.out.println("Please Select correct choice from menu");
			}
		}
		catch(InputMismatchException ex) {
			System.out.println("=======================================");
			System.err.println(ParkingLotError.WRONG_USER_INPUT_ERROR);
			System.out.println("=======================================");
		}
		catch(Exception ex) {
			System.out.println("=======================================");
			System.err.println(ParkingLotError.OPERATION_NOT_AVAILABLE_ERROR);
			System.out.println("=======================================");
		}
		
		
	}
	
	public void printInformation(int userInput) {
		SlotService slotService = new SlotServiceImpl();
		if(userInput==3) {
			slotService.printVechileSlot();
		}
		else if(userInput==4) {
			slotService.printFreeSlot();
		}
	}
	
}
