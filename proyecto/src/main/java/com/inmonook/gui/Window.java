package com.inmonook.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.inmonook.user.UserDAO;
import com.inmonook.user.UserDAOTemp;


public class Window extends JFrame {
	private JPanel actualDisplay;
	
	public Window() {
		actualDisplay = new LoginScreen(this);
		
		Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(900, dimensions.height - 80);
		this.setTitle("Inmobiliarias - INMONOOK");
		this.setIconImage(new ImageIcon("./icons/icono.jpg").getImage() );
		this.setCursor(new Cursor(Cursor.HAND_CURSOR) );
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(actualDisplay);
		this.setVisible(true);
		
	}

	public void changeWindow(ScreenList screen, Object o) {
		this.setVisible(false);
		this.actualDisplay = null;
		switch ( screen.getValue() ) {
			case 0:
				actualDisplay = new LoginScreen(this);
				break;
			case 1:
				this.actualDisplay = new SignInScreen(this);
				break;
			case 2:
				this.actualDisplay = new PropertiesScreen(this, ( o.getClass() == UserDAOTemp.class ? (UserDAOTemp) o : null) );
				break;
		}
		
		this.setContentPane(this.actualDisplay);
		this.setVisible(true);
	}
}
