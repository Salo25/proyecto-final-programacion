package com.inmonook.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.inmonook.user.InvalidaPassException;
import com.inmonook.user.UserDAOTemp;
import com.inmonook.user.UserNotFoundException;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LoginScreen extends JPanel {
	private UserDAOTemp users;
	private Window window;
	private JTextField dniField;
	private JLabel tagDNI;
	private JLabel lblNewLabel_2;
	private JButton buttLogin;
	private JButton butSignIn;
	private JPasswordField passwordField;
	public LoginScreen(final Window w) {
		this.window = w;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{52, 62, 101, 145, 124, 100, 82, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 33, 42, 28, 39, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labTitle = new JLabel("INMONOOK");
		labTitle.setFont(new Font("Arial", Font.BOLD, 35));
		GridBagConstraints gbc_labTitle = new GridBagConstraints();
		gbc_labTitle.gridwidth = 2;
		gbc_labTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labTitle.gridx = 3;
		gbc_labTitle.gridy = 2;
		add(labTitle, gbc_labTitle);
		
		tagDNI = new JLabel("DNI:");
		tagDNI.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_tagDNI = new GridBagConstraints();
		gbc_tagDNI.insets = new Insets(0, 0, 5, 5);
		gbc_tagDNI.anchor = GridBagConstraints.SOUTHWEST;
		gbc_tagDNI.gridx = 3;
		gbc_tagDNI.gridy = 3;
		add(tagDNI, gbc_tagDNI);
		
		dniField = new JTextField();
		dniField.setFont(new Font("Lucida Fax", Font.PLAIN, 12));
		dniField.setText("Escriba su dni...");
		GridBagConstraints gbc_dniField = new GridBagConstraints();
		gbc_dniField.anchor = GridBagConstraints.NORTH;
		gbc_dniField.gridwidth = 2;
		gbc_dniField.insets = new Insets(0, 0, 5, 5);
		gbc_dniField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniField.gridx = 3;
		gbc_dniField.gridy = 4;
		add(dniField, gbc_dniField);
		dniField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);
		
		butSignIn = new JButton("Registrarse");
		GridBagConstraints gbc_butSignIn = new GridBagConstraints();
		gbc_butSignIn.insets = new Insets(0, 0, 5, 5);
		gbc_butSignIn.gridx = 3;
		gbc_butSignIn.gridy = 7;
		add(butSignIn, gbc_butSignIn);
		
		buttLogin = new JButton("Iniciar Sesión");
		GridBagConstraints gbc_buttLogin = new GridBagConstraints();
		gbc_buttLogin.insets = new Insets(0, 0, 5, 5);
		gbc_buttLogin.gridx = 4;
		gbc_buttLogin.gridy = 7;
		add(buttLogin, gbc_buttLogin);
		
		
		
		//ACCIONES
		butSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				w.changeWindow(ScreenList.SIGN_IN_SCREEN, null);
			}
		});
		
		buttLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dni = dniField.getText();
				String pass = "";
				for (char i : passwordField.getPassword()) {
					pass += i;
				}
				try {
					users = new UserDAOTemp(dni, pass);
					window.changeWindow(ScreenList.PROPERTIES_SCREEN, users);
				} catch (InvalidaPassException e1) {
					JOptionPane.showMessageDialog(window, "La contraseña no es correcta.", "Contraseña incorrecta", JOptionPane.ERROR_MESSAGE);
					System.out.println("La contraseña no es correcta.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(window, "Ha ocurrido un a la hora de cargar la base de datos.", "Error de Base de datos", JOptionPane.ERROR_MESSAGE);
					System.out.println("Ha ocurrido un a la hora de cargar la base de datos.");
				} catch (UserNotFoundException e1) {
					JOptionPane.showMessageDialog(window, "El usuario que has introducido no es correcto.", "Usuuario incorrecto", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
