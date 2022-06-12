package com.inmonook.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import com.inmonook.properties.Property;
import com.inmonook.properties.PropertyDAOImpl;
import com.inmonook.user.UserDAOTemp;
import com.inmonook.utils.PropertyDAO;

import javax.swing.JScrollPane;

public class PropertiesScreen extends JPanel {
	private Window window;
	private PropertyDAO properties;
	private UserDAOTemp loggedUser;
	private JTextField txtListaDeInmuebles;
	public PropertiesScreen(Window w, UserDAOTemp listUsers) {
		this.window = w;
		this.loggedUser = listUsers;
		this.loadProperties();
		
		
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		setLayout(new BorderLayout(0, 0));
		
		txtListaDeInmuebles = new JTextField();
		txtListaDeInmuebles.setText("LISTA DE INMUEBLES");
		add(txtListaDeInmuebles, BorderLayout.NORTH);
		txtListaDeInmuebles.setColumns(10);
		
		JScrollPane scrollPane = new ListPropertyPane( ( (PropertyDAOImpl)properties).getSortedByCode() );
		add(scrollPane, BorderLayout.CENTER);
		/*
		image = new ImageIcon( new ImageIcon("/com/media/004_PROPRIETOR_MOLLET_01.jpg").getImage().getScaledInstance(0, 0, Image.SCALE_DEFAULT) );
		setLayout(new BorderLayout(0, 0));
		
		//JLabel labPrueba = new JLabel(new ImageIcon(PropertiesScreen.class.getResource("/com/media/004_PROPRIETOR_MOLLET_01.jpg")));
		JLabel labPrueba = new JLabel();
		labPrueba.setIcon(image);
		
		//this.SetImageLabel(labPrueba);
		
		add(labPrueba, BorderLayout.CENTER);
		*/
		/*
		JLabel labPrueba = new JLabel();
	    BufferedImage image = null;
	    labPrueba.setBounds(0, 0, this.getWidth(), this.getHeight());
	    try 
	    {
	        image = ImageIO.read(new File("./target/classes/com/media/004_PROPRIETOR_MOLLET_01.jpg"));

	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	    ImageIcon imageIcon = new ImageIcon(fitimage(image, labPrueba.getWidth(), labPrueba.getHeight()));
	    labPrueba.setIcon(imageIcon);
		*/
		
		
	}
	
	/*
	public void SetImageLabel(JLabel jLable) {
		ImageIcon image = new ImageIcon("/com/media/004_PROPRIETOR_MOLLET_01.jpg");
		
		Icon icon = new ImageIcon(
					image.getImage().getScaledInstance(jLable.getWidth(), jLable.getHeight(), Image.SCALE_DEFAULT)
				);
		
		jLable.setIcon(icon);
		
		this.repaint();
	}
	*/

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	
	/*
	private Image fitimage(Image img , int w , int h)
	{
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	}
	*/
	
	private void loadProperties() {
		try {
			this.properties = new PropertyDAOImpl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(window, e.getMessage() + "\nVuelalo a intentar más tarde.", "Error de cargar de usuarios.", JOptionPane.ERROR_MESSAGE);
			this.properties = null;
			
			window.changeWindow(ScreenList.MAIN_SCREEN, null);
		}
		
	}
}
