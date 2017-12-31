package com.gojek.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.gojek.model.Car;
import com.gojek.model.Parking;
import com.gojek.model.Slot;
import com.gojek.util.Utils;

public class ParkingService {

	private static Parking parkinglot;
	private static Map<String, List <Car>> bycolorcarreport;
	private static Map<String, Slot> byregnumcarreport;
	private static int availablecount;
	private static int lotsize;
	private static int nearestslot;
	
	
	public void processCommandFile(String commandfile){
		BufferedReader br = null;
		FileReader fr = null;
		String sCurrentLine;

		try {
			fr = new FileReader(commandfile);
			br = new BufferedReader(fr);
			while ((sCurrentLine = br.readLine()) != null) {
				processCmd(sCurrentLine);
			}

		} catch (IOException ioe) {
			System.out.println("Exception :: IO Exception file reading command file"+ioe.getMessage() );
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				System.out.println("Exception :: IO Exception while closing streams"+ex.getMessage() );
			}
		}
	}
	
	public void processCommand(){
		while(true){
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String inputcmd = in.nextLine().trim();	
			processCmd(inputcmd);
		}
	}
	
	private void processCmd(String inputcmd){
		ReportingService reportingserivce= new ReportingService();
		if(!Utils.isStringNullOrEmpty(inputcmd)){
			String [] cmd =inputcmd.split(" ");
			
			if (Utils.isStringNullOrEmpty(cmd[1])){
				 System.out.println("Missing input parameter. Please check the usage.");
				 return;
			 }
			
			switch (cmd[0]){
			case "create_parking_lot":
				this.createParkingLot(Integer.parseInt(cmd[1]));
				break;
			case "park":
				
				if (!Utils.isStringNullOrEmpty(cmd[2])){
					this.parkTheCar(cmd[1],cmd[2]);
				 } else {
					 System.out.println("Missing input parameter. Please check the usage.");
				 }
				
				break;
			case "leave":
				this.unparkTheCar(Integer.parseInt(cmd[1]));
				break;
			case "status":
				this.parkingStatus();
				break;
			case "registration_numbers_for_cars_with_colour":
				reportingserivce.registrationNumberOfCarsByColor(cmd[1],bycolorcarreport);
				break;
			case "slot_numbers_for_cars_with_colour":
				reportingserivce.slotNumbersForCarsByColor(cmd[1],byregnumcarreport,bycolorcarreport);
				break;
			case "slot_number_for_registration_number":
				reportingserivce.slotNumberByRegistrationNumber(cmd[1],byregnumcarreport);
				break;
			default :
				System.out.println("Not a valid command. Please try again.");
			}
			
		} else {
			System.out.println("Please enter a valid command or kill the session by CTRL+C");
		}
		
	}
	
	private void parkingStatus(){
		System.out.println("Slot No	Registration No.	Colour");
		for (Slot slot:parkinglot.getParkinglist()){
			if (slot == null){
				continue;
			} else {	
				System.out.println(slot.getSlotnumber()+"	"+slot.getHoldingcar().getRegistrationnumber()+"	"+slot.getHoldingcar().getColor());
			}	
		}
	}
	
	private void createParkingLot(int lotcount){
		if (parkinglot==null){
			parkinglot = new Parking(lotcount);
			availablecount=lotcount;
			lotsize=lotcount;
			bycolorcarreport = new HashMap<String,List<Car>>();
			byregnumcarreport = new HashMap<String,Slot>();
			System.out.println("Created a parking lot with "+lotcount+" slots");
		} else {
			System.out.println("Warning :: Cannot create Parking lot. Parking is already created");
		}
	}
	
	private void parkTheCar(String registrationnum, String color){
		if (parkinglot == null){
				this.createParkingLot(10);
		}
		 		 
		if (!isParkingAvailable()){
			 System.out.println("Sorry, parking lot is full");
			 return;
	    } else {
	    	Car pcar= new Car();
	    	pcar.setColor(color);
	    	pcar.setRegistrationnumber(registrationnum);
	    	
	    	Slot slot = new Slot();
	    	slot.setHoldingcar(pcar);
	        slot.setSlotnumber(parkinglot.getNearestavailableslot());
	    	parkinglot.addToParkinglist(slot);
	    	availablecount--;
	    	parkinglot.setNearestavailableslot(parkinglot.size()+1);
	    	
	    	if (bycolorcarreport.get(color).isEmpty()){
	    		List <Car> bycolorcarlist = new ArrayList<>();
	    		bycolorcarlist.add(pcar);
	    	} else {
	    		bycolorcarreport.get(color).add(pcar);
	    	}
	    	
	    	byregnumcarreport.put(registrationnum, slot);
	    	
	    	
	    }
		
	}
	
	private void unparkTheCar(int slot){
		int tempnearestslot=0;
		if (parkinglot.size()==0){
			System.out.println("Warning :: No car is parked.Nothing to un-park.");
			return;
		}
		
		if (lotsize > availablecount ){
			availablecount++;
			
			Car cartoberemoved=parkinglot.getParkinglist().get(slot).getHoldingcar();
			List<Car> carrptlisttobecleaned= bycolorcarreport.get(cartoberemoved.getColor());
			
			if (!carrptlisttobecleaned.isEmpty()){
				carrptlisttobecleaned.remove(cartoberemoved);
	    	} 
			
	    	byregnumcarreport.remove(cartoberemoved.getRegistrationnumber());
	    	
			parkinglot.removeFromParkinglist(slot);
			tempnearestslot=parkinglot.getNearestavailableslot();
			if (slot <= tempnearestslot){
				parkinglot.setNearestavailableslot(slot);	
			} else {
				parkinglot.setNearestavailableslot(tempnearestslot);
			}
			
		}
		
	}
	
	private Boolean isParkingAvailable(){
		if (availablecount>0)
			return Boolean.TRUE;
		else
		    return Boolean.FALSE;
	}
}
