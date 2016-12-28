package app;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	Connection connection = null;
	public static Connection getConnection(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:res/University.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return connection;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}
