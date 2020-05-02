package ec.edu.ups.mysql.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContextJDBC {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/practica01";
	private static final String USER = "monty";
	private static final String PASS = "montypassword";
	private static ContextJDBC jdbc1 = null;
	private static ContextJDBC jdbc2 = null;
	private Statement statement = null;
	
	public ContextJDBC() {
		this.connect();
	}
	
	public void connect() {
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL, USER, PASS);
			this.statement = connection.createStatement();
		}catch (ClassNotFoundException e) {
			System.out.println("Problemas con el driver!");
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println("Problemas con la base de datos!");
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet query(String sql) {
		try {
			return this.statement.executeQuery(sql);
		}catch (SQLException e) {
			System.out.println("Problemas al momento de hacer la consulta!");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean update(String sql) {
		try {
			this.statement.executeUpdate(sql);
			return true;
		}catch(SQLException e) {
			System.out.println("Problemas al momento de hacer la actualizacion!");
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	protected static ContextJDBC getJDBC1() {
		if(jdbc1 == null) {
			jdbc1 = new ContextJDBC();
		}
		return jdbc1;
	}
	
	protected static ContextJDBC getJDBC2() {
		if(jdbc2 == null) {
			jdbc2 = new ContextJDBC();
		}
		return jdbc2;
	}
	
	
}
