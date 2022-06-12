package com.inmonook;

public abstract class LocationElement {
	private String country, province, cityTown, street;

	
	
	//CONSTRUCTORS
	public LocationElement(String country, String province, String cityTown, String street) {
		super();
		this.country = country;
		this.province = province;
		this.cityTown = cityTown;
		this.street = street;
	}
	
	public LocationElement(SpainProvinces province, String cityTown, String street) {
		super();
		this.country = "SPAIN";
		this.province = province.getValue();
		this.cityTown = cityTown;
		this.street = street;
	}

	
	//GETTERS SETTERS
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityTown() {
		return cityTown;
	}
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return "LocationElement [country=" + country + ", province=" + province + ", cityTown=" + cityTown + ", street="
				+ street + "]";
	}
	
}
