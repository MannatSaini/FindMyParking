package com.gojek.service;

import java.util.List;
import java.util.Map;

import com.gojek.model.Car;
import com.gojek.model.Slot;

public class ReportingService {
	public void getParkingStatus(){
		
	}
	
	public void registrationNumberOfCarsByColor(String carcolor,Map<String,List<Car>> carreport){
		StringBuilder cars = new StringBuilder();
		if(carreport.get(carcolor).isEmpty()){
			System.out.println("Not found");
		} else {
			for (Car pcar:carreport.get(carcolor)){
				cars.append(pcar.getRegistrationnumber()).append(", ");
			}
			int lastcommaindex =cars.lastIndexOf(",");
			System.out.print(cars.toString().substring(0, lastcommaindex-1));
		}
		
	}
	
	public void slotNumbersForCarsByColor(String carcolor, Map<String,Slot> slotrptbyregnum, Map<String,List<Car>> carreport){
		if(carreport.get(carcolor).isEmpty()){
			System.out.println("Not found");
			return;
		}
		String regnum=null;
		StringBuilder slotnums= new StringBuilder();
		
		for (Car car :carreport.get(carcolor)){
			regnum=car.getRegistrationnumber();
			if (slotrptbyregnum.get(regnum) != null){
				slotnums.append(slotrptbyregnum.get(regnum).getSlotnumber()).append(", ");
			} else {
				continue;
			}
		}
		
		int lastcommaindex =slotnums.lastIndexOf(",");
		System.out.print(slotnums.toString().substring(0, lastcommaindex-1));
	}

	public void slotNumberByRegistrationNumber(String registrationNumber, Map<String,Slot> slotrptbyregnum){
		if (slotrptbyregnum.get(registrationNumber) != null){
			System.out.println(slotrptbyregnum.get(registrationNumber).getHoldingcar().getRegistrationnumber());
		} else {
			System.out.println("Not found");
		}
	}
	

}
