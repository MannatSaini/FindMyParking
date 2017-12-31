package com.gojek.model;

public class Car {
	private String registrationnumber;
	private String color;
	public String getRegistrationnumber() {
		return registrationnumber;
	}
	public void setRegistrationnumber(String registrationnumber) {
		this.registrationnumber = registrationnumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationnumber == null) ? 0 : registrationnumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		
		if (registrationnumber == null) {
			if (other.registrationnumber != null)
				return false;
		} else if (!registrationnumber.equals(other.registrationnumber))
			return false;
		return true;
	}

}
