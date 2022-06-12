package com.inmonook.gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JTextPane;

import java.awt.Font;
import java.io.File;
import java.awt.Color;

public class OneResultPane extends JPanel {
	private String title;
	private File image;
	private double price;
	private boolean rentable;
	
	
	public OneResultPane(String title, File image, double price, boolean rentable) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel();
		add(lblNewLabel);
		
		JLabel lablImage = new JLabel("");
		lablImage.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(lablImage);
		
		JLabel labImage = new JLabel(title);
		add(labImage);
		
		JLabel labTitle = new JLabel(title);
		labTitle.setFont(new Font("SansSerif", Font.BOLD, 41));
		add(labTitle);
		
		JTextPane txtCuerpo = new JTextPane();
		txtCuerpo.setText("Precio: " + price + " € " + (rentable ? "/mes " : "" ) + "\n¿En alquiler?: " + (rentable ? "Sí" : "No") );
		add(txtCuerpo);
		
		JButton btnNewButton = new JButton("COMPRAR");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 204, 102));
		add(btnNewButton);
		
	}
}
