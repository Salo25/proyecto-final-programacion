package com.inmonook.properties;

import java.io.File;
import java.util.ArrayList;

import com.inmonook.SpainProvinces;
import com.inmonook.user.Proprietor;

public final class ParkingGarage extends Property {
	private String place;

	
	
	//CONSTRUCTORS
	public ParkingGarage(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, String place) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos);
		this.place = place;
	}

	public ParkingGarage(SpainProvinces province, String cityTown, String street, short code, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos, String place) {
		
		super(province, cityTown, street, code, title, description, area, rentable, precie, proprietor, photos);
		this.place = place;
	}
	
	public ParkingGarage(String country, String province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, String place) {
		
		super(country, province, cityTown, street, title, description, area, rentable, precie, proprietor,
				photos);
		this.place = place;
	}

	public ParkingGarage(SpainProvinces province, String cityTown, String street, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos, String place) {
		
		super(province, cityTown, street, title, description, area, rentable, precie, proprietor, photos);
		this.place = place;
	}



	//GETTERS SETTERS
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}



	//TOSTRING
	@Override
	public String toString() {
		return super.toString() + "\n\tParkingGarage [place=" + place + "]";
	}
	
	@Override
	public ParkingGarage clone() throws CloneNotSupportedException {
		ParkingGarage copy = (ParkingGarage)super.clone();
		return copy;
	}
	
	
}
