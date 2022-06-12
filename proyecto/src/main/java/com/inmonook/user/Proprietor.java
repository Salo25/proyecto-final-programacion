package com.inmonook.user;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import com.inmonook.properties.Property;

public final class Proprietor extends User {
	private LinkedHashSet<Property> properties;

	
	
	//CONSTRUCTORS
	public Proprietor(String email, String dni, String name, String password, String phone, int money,
			LocalDate dateBirth, HashMap<Integer, Proprietor> favorites, LinkedHashSet<Property> properties) {
		super(email, dni, name, password, phone, money, dateBirth, favorites);
		this.properties = properties;
	}

	
	
	//GETTERS SETTERS
	public LinkedHashSet<Property> getProperties() {
		return properties;
	}
	public void setProperties(LinkedHashSet<Property> properties) {
		this.properties = properties;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return "Proprietor [properties=" + properties + "]";
	}
	
	
}
