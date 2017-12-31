package com.gojek.model;

import java.util.ArrayList;
import java.util.List;

public class Parking {
	private List<Slot> parkinglist;
	private int nearestavailableslot;
	
	public Parking(int slotcount){
		parkinglist = new ArrayList<>(slotcount);
		nearestavailableslot=0;
	}
	
	public List<Slot> getParkinglist() {
		return parkinglist;
	}
	public void addToParkinglist(Slot slot) {
		this.parkinglist.add(slot);
	}
	
	public void removeFromParkinglist(int parkingSlotNum) {
		this.parkinglist.remove(parkingSlotNum);
	}
	
	public int getNearestavailableslot() {
		return nearestavailableslot;
	}
	public void setNearestavailableslot(int nearestavailableslot) {
		this.nearestavailableslot = nearestavailableslot;
	}
	
    public int size(){
    	return parkinglist.size();
    }

}
