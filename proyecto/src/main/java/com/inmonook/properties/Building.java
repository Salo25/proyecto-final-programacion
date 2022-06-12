package com.inmonook.properties;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.inmonook.SpainProvinces;
import com.inmonook.user.Proprietor;

public abstract class Building extends Property {
	private String condition;
	private String terrain;
	private LocalDateTime lastReformDate;
	private byte numRooms, numBaths, numBedrooms;
	private ParkingGarage includedPG;
	private ArrayList<File> blueprints;
	private boolean pool;
	private char energyCertificate;
	
	
	//CONSTRUCTORS
	public Building(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos);
		this.condition = condition.toString();
		this.terrain = (terrain==null ? null :  terrain.toString() );
		this.lastReformDate = lastReformDate;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		this.numBedrooms = numBedrooms;
		this.includedPG = includedPG;
		this.blueprints = blueprints;
		this.pool = pool;
		this.energyCertificate = energyCertificate;
	}

	public Building(SpainProvinces province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate) {
		
		super(province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos);
		this.condition = condition.toString();
		this.terrain = (terrain==null ? null :  terrain.toString() );
		this.lastReformDate = lastReformDate;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		this.numBedrooms = numBedrooms;
		this.includedPG = includedPG;
		this.blueprints = blueprints;
		this.pool = pool;
		this.energyCertificate = energyCertificate;
	}
	
	public Building(String country, String province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate) {
		
		super(country, province, cityTown, street, title, description, area, rentable, precie, proprietor,
				photos);
		this.condition = condition.toString();
		this.terrain = (terrain==null ? null :  terrain.toString() );
		this.lastReformDate = lastReformDate;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		this.numBedrooms = numBedrooms;
		this.includedPG = includedPG;
		this.blueprints = blueprints;
		this.pool = pool;
		this.energyCertificate = energyCertificate;
	}

	public Building(SpainProvinces province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, BuildCondition condition, Terrain terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate) {
		
		super(province, cityTown, street, title, description, area, rentable, precie, proprietor,
				photos);
		this.condition = condition.toString();
		this.terrain = (terrain==null ? null :  terrain.toString() );
		this.lastReformDate = lastReformDate;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		this.numBedrooms = numBedrooms;
		this.includedPG = includedPG;
		this.blueprints = blueprints;
		this.pool = pool;
		this.energyCertificate = energyCertificate;
	}
	
	Building(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos, String condition, String terrain, LocalDateTime lastReformDate,
			byte numRooms, byte numBaths, byte numBedrooms, ParkingGarage includedPG, ArrayList<File> blueprints,
			boolean pool, char energyCertificate) {
		
		super(country, province, cityTown, street, code, title, description, area, rentable, precie, proprietor,
				photos);
		this.condition = condition;
		this.terrain = terrain;
		this.lastReformDate = lastReformDate;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		this.numBedrooms = numBedrooms;
		this.includedPG = includedPG;
		this.blueprints = blueprints;
		this.pool = pool;
		this.energyCertificate = energyCertificate;
	}



	//SETTERS GETTTERS
	public String getCondition() {
		return condition;
	}
	public void setCondition(BuildCondition condition) {
		this.condition = condition.toString();
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain.toString();
	}
	public LocalDateTime getLastReformDate() {
		return lastReformDate;
	}
	public void setLastReformDate(LocalDateTime lastReformDate) {
		this.lastReformDate = lastReformDate;
	}
	public byte getNumRooms() {
		return numRooms;
	}
	public void setNumRooms(byte numRooms) {
		this.numRooms = numRooms;
	}
	public byte getNumBaths() {
		return numBaths;
	}
	public void setNumBaths(byte numBaths) {
		this.numBaths = numBaths;
	}
	public byte getNumBedrooms() {
		return numBedrooms;
	}
	public void setNumBedrooms(byte numBedrooms) {
		this.numBedrooms = numBedrooms;
	}
	public ParkingGarage getIncludedPG() {
		return includedPG;
	}
	public void setIncludedPG(ParkingGarage includedPG) {
		this.includedPG = includedPG;
	}
	public ArrayList<File> getBlueprints() {
		return blueprints;
	}
	public void setBlueprints(ArrayList<File> blueprints) {
		this.blueprints = blueprints;
	}
	public boolean havePool() {
		return pool;
	}
	public void setPool(boolean pool) {
		this.pool = pool;
	}
	public char getEnergyCertificate() {
		return energyCertificate;
	}
	public void setEnergyCertificate(char energyCertificate) {
		this.energyCertificate = energyCertificate;
	}



	//TOSTRING
	@Override
	public String toString() {
		return  super.toString() + "\n\tBuilding [condition=" + condition + ", terrain=" + terrain + ", lastReformDate=" + lastReformDate
				+ ", numRooms=" + numRooms + ", numBaths=" + numBaths + ", numBedrooms=" + numBedrooms + ", includedPG="
				+ ( includedPG != null ? "[" + includedPG.getCode() + "] - " + includedPG.getTitle() : null ) + ", blueprints=" + blueprints + ", pool=" + pool
				+ ", energyCertificate=" + energyCertificate + "]";
	}
	
	
}
