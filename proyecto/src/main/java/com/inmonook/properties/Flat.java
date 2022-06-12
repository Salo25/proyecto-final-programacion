package com.inmonook.properties;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.inmonook.SpainProvinces;
import com.inmonook.user.Proprietor;

public final class Flat extends Building {
	private byte block, ladder;
	private short floor;
	private String door;
	
	
	
	//CONSTRUCTORS
	public Flat(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, byte block, byte ladder, short floor, String door) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor, photos,
				condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool,
				energyCertificate);
		this.block = block;
		this.ladder = ladder;
		this.floor = floor;
		this.door = door;
	}
	
	public Flat(SpainProvinces province, String cityTown, String street, short code, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos,
			BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate, byte numRooms, byte numBaths,
			byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints, boolean pool,
			char energyCertificate, byte block, byte ladder, short floor, String door) {
		
		super(province, cityTown, street, code, title, description, area, rentable, precie, proprietor, photos, condition,
				terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool,
				energyCertificate);
		this.block = block;
		this.ladder = ladder;
		this.floor = floor;
		this.door = door;
	}
	
	public Flat(String country, String province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, byte block, byte ladder, short floor, String door) {
		
		super(country, province, cityTown, street, title, description, area, rentable, precie, proprietor, photos,
				condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool,
				energyCertificate);
		this.block = block;
		this.ladder = ladder;
		this.floor = floor;
		this.door = door;
	}
	
	public Flat(SpainProvinces province, String cityTown, String street, String title, String description,
			float area, boolean rentable, double precie, Proprietor proprietor, ArrayList<File> photos,
			BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate, byte numRooms, byte numBaths,
			byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints, boolean pool,
			char energyCertificate, byte block, byte ladder, short floor, String door) {
		
		super(province, cityTown, street, title, description, area, rentable, precie, proprietor, photos, condition,
				terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool,
				energyCertificate);
		this.block = block;
		this.ladder = ladder;
		this.floor = floor;
		this.door = door;
	}
	
	Flat(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, String condition, String terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate, byte block, byte ladder, short floor, String door) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor, photos,
				condition, terrain, lastReformDate, numRooms, numBaths, numBedrooms, includedPG, blueprints, pool,
				energyCertificate);
		this.block = block;
		this.ladder = ladder;
		this.floor = floor;
		this.door = door;
	}

	
	
	//GETTERS SETTERS
	public byte getBlock() {
		return block;
	}
	public void setBlock(byte block) {
		this.block = block;
	}
	public byte getLadder() {
		return ladder;
	}
	public void setLadder(byte ladder) {
		this.ladder = ladder;
	}
	public short getFloor() {
		return floor;
	}
	public void setFloor(short floor) {
		this.floor = floor;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return super.toString() + "\n\tFlat [block=" + block + ", ladder=" + ladder + ", floor=" + floor + ", door=" + door + "]";
	}

	@Override
	public Flat clone() throws CloneNotSupportedException {
		Flat copy = (Flat)super.clone();
		return copy;
	}
	
}
