/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class ConexionDB {
    Connection conn = null;
    public Connection getConexion(String rutaCSVS) {
	try {
	    String url = "jdbc:relique:csv:" + rutaCSVS;
	    Class.forName("org.relique.jdbc.csv.CsvDriver");
	    conn = DriverManager.getConnection(url);
	}catch (Exception e) {
	    e.printStackTrace();
	} 
	return conn;
    }
    
    public Connection getConexion(String rutaCSVS, Properties props) {
	try {
	    String url = "jdbc:relique:csv:" + rutaCSVS;
	    Class.forName("org.relique.jdbc.csv.CsvDriver");
	    conn = DriverManager.getConnection(url,props);
	}catch (Exception e) {
	    e.printStackTrace();
	} 
	return conn;
    }
    
    public void conectar() {
	try {
	    
	    Class.forName("org.relique.jdbc.csv.CsvDriver");
	    String url = "jdbc:relique:csv:" + "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\tsim\\";
	    Connection conn = DriverManager.getConnection(url);
	    Statement stmt = conn.createStatement();

	    // Select the ID and NAME columns from sample.csv
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM DATA_GEN");
	    
	    System.out.println("Query: ");
	    while (resultSet.next()) {
		System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
	    }
	    System.out.println();
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }
    
    public void conectar(Properties props) {
	try {
	    
	    Class.forName("org.relique.jdbc.csv.CsvDriver");
	    String url = "jdbc:relique:csv:" + "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\tsim\\";
	    Connection conn = DriverManager.getConnection(url,props);
	    Statement stmt = conn.createStatement();

	    // Select the ID and NAME columns from sample.csv
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM DATA_GEN");
	    
	    System.out.println("Query: ");
	    while (resultSet.next()) {
		System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
	    }
	    System.out.println();
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }
}
