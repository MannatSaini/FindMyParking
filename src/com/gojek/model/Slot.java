package com.gojek.model;

public class Slot {
	private int slotnumber;
	private Car holdingcar;
	
	public int getSlotnumber() {
		return slotnumber;
	}
	public void setSlotnumber(int slotnumber) {
		this.slotnumber = slotnumber;
	}
	public Car getHoldingcar() {
		return holdingcar;
	}
	public void setHoldingcar(Car holdingcar) {
		this.holdingcar = holdingcar;
	}
}
