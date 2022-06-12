package com.inmonook.user;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.inmonook.ServiceOffice;
import com.inmonook.properties.Property;

public final class RealStateAgent extends User {
	private ServiceOffice workOffice;
	private boolean[] weeklyWork = new boolean[7];
	
	
	
	//CONSTRUCTORS
	public RealStateAgent(String email, String dni, String name, String password, String phone, int money,
			LocalDate dateBirth, HashMap<Integer, Proprietor> favorites, ServiceOffice workOffice,
			boolean[] weeklyWork) {
		super(email, dni, name, password, phone, money, dateBirth, favorites);
		this.workOffice = workOffice;
		this.weeklyWork = weeklyWork;
	}
	
	
	
	//GETTERS SETTERS
	public ServiceOffice getWorkOffice() {
		return workOffice;
	}
	public void setWorkOffice(ServiceOffice workOffice) {
		this.workOffice = workOffice;
	}
	public boolean[] getWeeklyWork() {
		return weeklyWork;
	}
	public void setWeeklyWork(boolean[] weeklyWork) {
		this.weeklyWork = weeklyWork;
	}
	
	
	
	//TOSTRING
	@Override
	public String toString() {
		return "RealStateAgent [workOffice=" + workOffice + ", weeklyWork=" + Arrays.toString(weeklyWork) + "]";
	}
	
}
