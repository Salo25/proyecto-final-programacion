package com.inmonook.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import com.inmonook.connections.NoConexion;
import com.inmonook.properties.InvalidProperty;
import com.inmonook.properties.Property;

public interface PropertyDAO {
	//public Collection<Property> getAll();
	public Property getByCode(short code);
	public List<Property> getList();
	public List<Property> getSortedByCode();
	public List<Property> getSortedByTitle();
	public List<Property> getSortedByArea();
	public List<Property> getSortedByPrice();
	
	public int upload(Property property) throws InvalidProperty, SQLException;
	public int update(Property propery) throws NoConexion, SQLException, InvalidProperty;
	public int delete(short propertyCode) throws InvalidProperty;
	public void refresh() throws SQLException;
}
