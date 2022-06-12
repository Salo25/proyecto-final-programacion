package com.inmonook.properties;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.inmonook.SpainProvinces;
import com.inmonook.user.Proprietor;

public final class House extends Building {
	private String door;
	private boolean patio;
	
	
	
	//CONSTRUCTORS
	public House(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, String door, boolean patio) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos, condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints,
				pool, energyCertificate);
		this.door = door;
		this.patio = patio;
	}
	
	public House(SpainProvinces province, String cityTown, String street, short code, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos,
			BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate, byte numRooms, byte numBaths,
			byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints, boolean pool,
			char energyCertificate,  String door, boolean patio) {
		
		super(province, cityTown, street, code, title, description, area, rentable, precie, proprietor, photos, condition,
				terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool, energyCertificate);
		this.door = door;
		this.patio = patio;
	}
	
	public House(String country, String province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, String door, boolean patio) {
		
		super(country, province, cityTown, street, title, description, area, rentable, precie, proprietor,
				photos, condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints,
				pool, energyCertificate);
		this.door = door;
		this.patio = patio;
	}
	
	public House(SpainProvinces province, String cityTown, String street, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos,
			BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate, byte numRooms, byte numBaths,
			byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints, boolean pool,
			char energyCertificate,  String door, boolean patio) {
		
		super(province, cityTown, street, title, description, area, rentable, precie, proprietor, photos, condition,
				terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool, energyCertificate);
		this.door = door;
		this.patio = patio;
	}
	
	House(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, String condition, String terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, String door, boolean patio) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos, condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints,
				pool, energyCertificate);
		this.door = door;
		this.patio = patio;
	}
	
	
	
	//GETTERS SETTERS
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public boolean havePatio() {
		return patio;
	}
	public void setPatio(boolean patio) {
		this.patio = patio;
	}
	
	
	
	//TOSTRING
	@Override
	public String toString() {
		return super.toString() + "\n\tHouse [door=" + door + ", patio=" + patio + "]";
	}
	
	@Override
	public House clone() throws CloneNotSupportedException {
		House copy = (House)super.clone();
		return copy;
	}
	
	
}
