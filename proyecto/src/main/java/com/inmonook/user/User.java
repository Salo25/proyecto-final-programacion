package com.inmonook.user;

import java.time.LocalDate;
import java.util.HashMap;

public class User {
	private String email, dni, name, password, phone;
	private int money;
	private LocalDate dateBirth;
	private HashMap<Integer, Proprietor> favorites;
	
	
	
	//CONSTRUCTORS
	public User(String email, String dni, String name, String password, String phone, int money, LocalDate dateBirth,
			HashMap<Integer, Proprietor> favorites) {
		super();
		this.email = email;
		this.dni = dni;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.money = money;
		this.dateBirth = dateBirth;
		this.favorites = favorites;
	}
	
	
	
	//GETTERS SETTERS
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public LocalDate getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}
	public HashMap<Integer, Proprietor> getFavorites() {
		return favorites;
	}
	public void setFavorites(HashMap<Integer, Proprietor> favorites) {
		this.favorites = favorites;
	}


	
	
	//TOSTRING
	@Override
	public String toString() {
		return "User [email=" + email + ", dni=" + dni + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", money=" + money + ", dateBirth=" + dateBirth + ", favorites=" + favorites + "]";
	}
	
	
}
