package com.gojek.controller;

import com.gojek.service.ParkingService;

/**
 * @author Sainis
 *
 */
public class FrontController {
    private ParkingService parkingservice;
    
	public void router(String... inputs){
		parkingservice = new ParkingService();
		
		switch(inputs[0]){
		case "Interactive" :
			parkingservice.processCommand();
		break;
		
		case "Batch" :
			parkingservice.processCommandFile(inputs[1]);
		break;
		
		}
		
	}
	
		
}	