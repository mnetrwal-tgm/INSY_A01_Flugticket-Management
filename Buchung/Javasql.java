package flugbuchung;

import java.sql.*;
import javax.sql.*;

@SuppressWarnings("unused")
public class Javasql {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String urlSql;
    public Javasql(String uname,String pswd,String type,String sname,int port,String database,String driver){
    	//Connection con = null;
    	try {
    		System.out.println("Verbindung wird hergestellt!");
			Class.forName(driver).newInstance();//"org.gjt.mm.mysql.Driver"
			connect = DriverManager.getConnection("jdbc:mysql://loclalhost:3306/flightdata" ,uname,pswd);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Klasse für die Datenbank nicht gefunden\n " + e1.getMessage());
			//e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Verbindung fehlgeschlagen"+e.getMessage());
			//e.printStackTrace();
		}
    	
    	
    	if(type.equals("MySQL")){
    		urlSql = "jdbc:mysql://" + sname+":"+port+"/"+database;
    	
    	}
    	else if(type.equals("PostgreSQL")){
    		urlSql = "jdbc:postgres://" + sname+":"+port+"/"+database;
    	
    	}
    	else if(type.equals("Oracle")){
    		urlSql = "jdbc:oracle:thin:@" + sname+":"+port+":"+database;
    	
    	}
    	else if(type.equals("Derby")){
    		urlSql = "jdbc:derby:"+database;
    	
    	}
    	try {
    		connect = DriverManager.getConnection(urlSql,uname,pswd);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	try {
			if(!connect.isClosed()){
				System.out.println("Verbunden");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    public Javasql(String uname,String pswd,String type,String sname,int port,String database){
    	//Connection con = null;
    	try {
    		System.out.println("Verbindung wird hergestellt!");
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();//
			connect = DriverManager.getConnection("jdbc:mysql://loclalhost:3306/flightdata" ,uname,pswd);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Klasse für die Datenbank nicht gefunden\n " + e1.getMessage());
			//e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Verbindung fehlgeschlagen"+e.getMessage());
			//e.printStackTrace();
		}
    	
    	
    	if(type.equals("MySQL")){
    		urlSql = "jdbc:mysql://" + sname+":"+port+"/"+database;
    	
    	}
    	else if(type.equals("PostgreSQL")){
    		urlSql = "jdbc:postgres://" + sname+":"+port+"/"+database;
    	
    	}
    	else if(type.equals("Oracle")){
    		urlSql = "jdbc:oracle:thin:@" + sname+":"+port+":"+database;
    	
    	}
    	else if(type.equals("Derby")){
    		urlSql = "jdbc:derby:"+database;
    	
    	}
    	try {
    		connect = DriverManager.getConnection(urlSql,uname,pswd);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	try {
			if(!connect.isClosed()){
				System.out.println("Verbunden");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
	public Statement getStatement() {
		try {
			statement = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	public ResultSet getResultSet(String SELECT) {
		try {
			resultSet = getStatement().executeQuery(SELECT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	public void enterValues(String VALUES){
		try {
			connect.createStatement().executeUpdate(VALUES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void terminateconnection(){
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
