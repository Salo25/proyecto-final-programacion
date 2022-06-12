package com.inmonook.properties;

import java.io.File;
import java.util.ArrayList;

import com.inmonook.LocationElement;
import com.inmonook.SpainProvinces;
import com.inmonook.user.Proprietor;


public abstract class Property extends LocationElement implements Cloneable {
	private short code;
	private String title, description;
	private float area;
	private boolean rentable;
	private double precie;
	private Proprietor proprietor;
	private ArrayList<File> photos;
	
	
	
	//CONSTRUCTORS
	public Property(String country, String province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos) {
		super(country, province, cityTown, street);
		this.code = code;
		this.title = title;
		this.description = description;
		this.area = area;
		this.precie = precie;
		this.rentable = rentable;
		this.proprietor = proprietor;
		this.photos = photos;
	}
	
	public Property(SpainProvinces province, String cityTown, String street, short code, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos) {
		super(province, cityTown, street);
		this.code = code;
		this.title = title;
		this.description = description;
		this.area = area;
		this.precie = precie;
		this.rentable = rentable;
		this.proprietor = proprietor;
		this.photos = photos;
	}
	
	public Property(String country, String province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos) {
		super(country, province, cityTown, street);
		this.code = 0;
		this.title = title;
		this.description = description;
		this.area = area;
		this.precie = precie;
		this.rentable = rentable;
		this.proprietor = proprietor;
		this.photos = photos;
	}
	
	public Property(SpainProvinces province, String cityTown, String street, String title,
			String description, float area, boolean rentable, double precie, Proprietor proprietor,
			ArrayList<File> photos) {
		super(province, cityTown, street);
		this.code = 0;
		this.title = title;
		this.description = description;
		this.area = area;
		this.precie = precie;
		this.rentable = rentable;
		this.proprietor = proprietor;
		this.photos = photos;
	}
	


	//GETTERS SETTERS
	public short getCode() {
		return code;
	}
	@Deprecated
	public void setCode(short code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public double getPrecie() {
		return precie;
	}
	public void setPrecie(double precie) {
		this.precie = precie;
	}
	public boolean isRentable() {
		return rentable;
	}
	public void setRentability(boolean rentablility) {
		this.rentable = rentablility;
	}
	public Proprietor getProprietor() {
		return proprietor;
	}
	public void setProprietor(Proprietor proprietor) {
		this.proprietor = proprietor;
	}
	public ArrayList<File> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<File> photos) {
		this.photos = photos;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return "Property [code=" + code + ", title=" + title + ", description=" + description + ", area=" + area
				+ ", precie=" + precie + ", rentable=" + rentable + ", proprietor=" + proprietor.getDni() + proprietor.getName() + ", photos="
				+ photos + "]";
	}
	
	
	public Property clone() throws CloneNotSupportedException {
		Property copy = (Property)super.clone();
		return copy;
	}
	
}
