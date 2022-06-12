package com.inmonook.gui;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JScrollPane;

import com.inmonook.properties.Property;
import javax.swing.ScrollPaneConstants;

public class ListPropertyPane extends JScrollPane {
	private ArrayList<Property> orderedProperties;
	
	
	public ListPropertyPane(ArrayList<Property> listProperties) {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		for (Property i : listProperties) {
			
			this.add(
					new OneResultPane(i.getTitle(), i.getPhotos().get(0), i.getPrecie(), i.isRentable() )
					);
		}
		
	}
}
