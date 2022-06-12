package com.inmonook;

public class ServiceOffice extends LocationElement {
	private String name;

	
	
	//CONSTRUCTORS
	public ServiceOffice(String country, String province, String cityTown, String street, String name) {
		super(country, province, cityTown, street);
		this.name = name;
	}

	public ServiceOffice(SpainProvinces province, String cityTown, String street, String name) {
		super(province, cityTown, street);
		this.name = name;
	}

	
	
	//GETTERS SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return "ServiceOffice [name=" + name + "]";
	}
	
}
