package flugbuchung;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;

import javax.print.attribute.standard.JobName;
import javax.sql.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;
@SuppressWarnings("unused")
public class Buchung implements ActionListener{
	private static  ResultSet rss = null;
	private static ResultSet arss = null;
	private static int standardPort = 3306;
	private static String standardUser = "list";
	private static String standardPswd = "3512";
	private static String standardDB = "flightdata";
	private static String standardDBType = "MySQL";
	private static String standardDBServer = "localhost";
	private static String standardDriver = "org.gjt.mm.mysql.Driver";
	private static String airline="";
	private static String flightnr="";
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		
		
		//TextAreas
		JTextArea word1 = new JTextArea();
		word1.setEditable(false);
		
		//Buttons
		JButton login = new JButton("login");
		JButton commence = new JButton("commence");
		JButton Buchen = new JButton("Buchen");
		
		
		//TextFields
		JTextField username = new JTextField(10);
		username.setText("root");
		JTextField database = new JTextField(10);
		database.setText("flightdata");
		JTextField port = new JTextField(10);
		port.setText("3306");
		JTextField server = new JTextField(10);
		server.setText("localhost");
		JTextField Message = new JTextField();
		Message.setEditable(false);
		Message.setText("Flug ist: \"Verfügbarkeit\"");
		JTextField firstname = new JTextField(10);
		firstname.setText("Vorname");
		JTextField lastname = new JTextField(10);
		lastname.setText("Nachname");
		JTextField rownr = new JTextField(10);
		rownr.setText("Reihe");
		JTextField seatposition = new JTextField(10);
		seatposition.setText("Sitzposition");
		//JTextField treiber = new JTextField(10);
		//treiber.setText("org.gjt.mm.mysql.Driver");
		
		//PasswordFields
		JPasswordField password = new JPasswordField(10);
		//ComboBoxes
		JComboBox<String> List1 = new JComboBox<String>();
		JComboBox<String> List2 = new JComboBox<String>();
		JComboBox<String> List3 = new JComboBox<String>();
		JComboBox<String> List4 = new JComboBox<String>();
		JComboBox<String> List5 = new JComboBox<String>();
		JComboBox<String> listy = new JComboBox<String>();
		
		//List of SQLs
		List1.addItem("MySQL");
		List1.addItem("PostgreSQL");
		List1.addItem("Oracle");
		List1.addItem("Derby");
		listy.addItem("org.gjt.mm.mysql.Driver");
		listy.addItem("org.postgresql.Driver");
		listy.addItem("oracle.jdbc.driver.OracleDriver");
		listy.addItem("org.apache.derby.jdbc.EmbeddedDriver");
		
		Object[] loginPane = {
				"Server: ",server,
				"DBType: ",List1,
				"Username: ",username,
				"Password: ",password,
				"Database: ",database,
				"Driver: ", listy,
				"Port: ",port
			};
		JOptionPane.showConfirmDialog(null, loginPane);
		standardDBServer = server.getText();
		standardUser = username.getText();
		standardPswd = password.getText();
		standardDB = database.getText();
		standardPort = Integer.parseInt(port.getText());
		standardDBType = (String)List1.getSelectedItem();
		standardDriver = (String)listy.getSelectedItem();
		
		Javasql sql = new Javasql(standardUser, standardPswd, standardDBType, standardDBServer, standardPort, standardDB,standardDriver);
		//JFrame and JPanel
		JFrame Frame = new JFrame();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		
		//Setting Variables
		rss = sql.getResultSet("SELECT distinct country FROM airports GROUP BY 1 ASC");
		try {
			do{
				try{
				List4.addItem(rss.getString("country"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e){
					System.out.println(e.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List4.setSelectedItem("AT");
		rss = sql.getResultSet("SELECT distinct country FROM airports GROUP BY 1 ASC");
		try {
			do{
				try{
				List5.addItem(rss.getString("country"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e){
					System.out.println(e.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List5.setSelectedItem("DE");
		rss = sql.getResultSet("SELECT name FROM airports WHERE country = '" + List4.getSelectedItem() +"' GROUP BY 1 ASC");
		try {
			do{
				try{
				List2.addItem(rss.getString("name"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e){
					System.out.println(e.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rss = sql.getResultSet("SELECT name FROM airports WHERE country = '" + List5.getSelectedItem() +"' GROUP BY 1 ASC");
		try {
			do{
				try{
				List3.addItem(rss.getString("name"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e){
					System.out.println(e.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ActionListener AL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List2.removeAllItems();
				List3.removeAllItems();
				 rss = sql.getResultSet("SELECT name FROM airports WHERE country = '" + List4.getSelectedItem() +"' GROUP BY 1 ASC");
		try {
			do{
				try{
				List2.addItem(rss.getString("name"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e1){
					System.out.println(e1.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rss = sql.getResultSet("SELECT name FROM airports WHERE country = '" + List5.getSelectedItem() +"' GROUP BY 1 ASC");
		try {
			do{
				try{
				List3.addItem(rss.getString("name"));
				//System.out.println("Added: "+ rss.getString("name"));
				}
				catch(SQLException e1){
					System.out.println(e1.getErrorCode());
				}
			}
			while(rss.next());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rss.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Test");
		Frame.repaint();
				 
			}
		};
		ActionListener AL2 = new ActionListener() {
			String Airport1 = "";
			String Airport2 = "";
			@Override
			public void actionPerformed(ActionEvent e) {
				rss = sql.getResultSet("SELECT airportcode FROM airports WHERE name='"+List2.getSelectedItem()+"'");
				//System.out.println("SELECT airportcode FROM airports WHERE name='"+List2.getSelectedItem()+"'");
				try {
					rss.first();
					Airport1=rss.getString(1);
				} catch (SQLException e4) {
					System.out.println(e4.getErrorCode());
					e4.printStackTrace();
				}
				try {
					rss.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				rss = sql.getResultSet("SELECT airportcode FROM airports WHERE name='"+List3.getSelectedItem()+"'");
				//System.out.println("SELECT airportcode FROM airports WHERE name='"+List3.getSelectedItem()+"'");
				try {
					rss.first();
					Airport2=rss.getString(1);
				} catch (SQLException e21) {
					// TODO Auto-generated catch block
					System.out.println(e21.getErrorCode());
					e21.printStackTrace();
				}
				try {
					rss.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				rss = sql.getResultSet("SELECT * FROM flights WHERE departure_airport ='"+Airport1+"' and destination_airport = '"+Airport2+"'");
				arss = rss;
				
				try {
					airline = arss.getString("airline");
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				try {
					flightnr = arss.getString("flightnr");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				try {
					if(rss.first()){
						Message.setText("Flug ist verfügbar");
						panel9.setVisible(true);
						Buchen.setVisible(true);
					}
					else{
						Message.setText("Flug ist nicht verfügbar");
						panel9.setVisible(false);
						Buchen.setVisible(false);
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					rss.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		commence.addActionListener(AL2);
		List4.addActionListener(AL);
		List5.addActionListener(AL);
		//List3.addItem("TestAirport");
		panel2.add(server);
		server.setText("localhost");
		panel2.add(username);
		username.setText("list");
		panel2.add(password);
		password.setText("3512");
		panel2.add(List1);
		panel2.add(login);
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
		panel6.add(panel7);
		panel6.add(panel8);
		panel2.setBackground(new Color(128, 128, 128));
		panel3.setBackground(new Color(128, 128, 128));
		panel4.setBackground(new Color(128, 128, 128));
		panel5.setBackground(new Color(128, 128, 128));
		panel6.setBackground(new Color(128, 128, 128));
		panel7.setBackground(new Color(128, 128, 128));
		panel1.setLayout(new BorderLayout());
		//panel1.add(panel2, BorderLayout.NORTH);
		panel1.add(panel3, BorderLayout.EAST);
		panel1.add(panel4, BorderLayout.SOUTH);
		panel1.add(panel5, BorderLayout.WEST);
		panel1.add(panel6, BorderLayout.CENTER);
		panel7.add(List4);
		panel7.add(List2);
		panel7.add(commence);
		word1.setText(" nach ");
		word1.setBackground(new Color(128, 128, 128));
		//List2.addItem("TestAirport");
		panel7.add(word1);
		Buchen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sql.enterValues("INSERT INTO passengers VALUES ("+(int)(Math.random()*499+1)+","+firstname.getText()+","+lastname.getText()+","+airline+","+flightnr+","+rownr.getText()+","+seatposition.getText()+")");
				System.out.println("INSERT INTO passengers VALUES ("+(int)(Math.random()*499+1)+","+firstname.getText()+","+lastname.getText()+","+airline+","+flightnr+","+rownr.getText()+","+seatposition.getText()+")");
			}
		});
		panel7.add(List5);
		panel7.add(List3);
		login.setSize(40, 20);
		login.setText("OK");
		panel7.add(commence);
		panel7.add(Message);
		//panel8.add(comp);
		//panel4.add(panel9);
		panel4.add(Buchen);
		panel9.add(firstname);
		panel9.add(lastname);
		panel9.add(rownr);
		panel9.add(seatposition);
		panel9.setBackground(new Color(128,128, 128));
		panel6.add(panel9);
		//panel7.add(panel9);
		commence.setSize(40, 20);
		commence.setText("Commence");
		Frame.setContentPane(panel1);
		Frame.setSize(1000, 400);
		Frame.setAlwaysOnTop(true);
		Frame.setLocationRelativeTo(null);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Frame.pack();
		panel9.setVisible(false);
		Buchen.setVisible(false);
		Frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Test");
	}
	

}
