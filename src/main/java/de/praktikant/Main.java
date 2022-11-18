package de.praktikant;

import java.sql.*;
import java.util.*;  
import java.io.*;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException,Exception {


        FileReader reader = new FileReader("src\\main\\java\\de\\praktikant\\db.config.properties");  
        Properties p = new Properties();  
        p.load(reader);  
        /* Testdaten zum aufrufen von db.properties
        System.out.println(p.getProperty("username"));  
        System.out.println(p.getProperty("password")); 
        System.out.println(p.getProperty("database1"));  
        System.out.println(p.getProperty("database2")); 
        System.out.println(p.getProperty("db1_tbl")); 
        */

        String table = p.getProperty("db1_tbl");

        final String connectionKonnektor = "jdbc:postgresql://localhost/" + p.getProperty("database1") + "?user="+ p.getProperty("username") + "&password=" + p.getProperty("password");
        final String connectionPortal = "jdbc:postgresql://localhost/" + p.getProperty("database2") + "?user="+ p.getProperty("username") + "&password=" + p.getProperty("password");

        System.out.print("Verbindung zu DB1: ");
        Connection conKonnektor = getConnection(connectionKonnektor);
        System.out.print("Verbindung zu DB2: ");
        Connection conPortal = getConnection(connectionPortal);

        System.out.println("");
        System.out.println("Daten aus der deine DB: " + p.getProperty("database1"));
        showDB(conKonnektor, table);
        System.out.println("Daten aus der deine DB: " + p.getProperty("database2"));
        showDB(conPortal, table);
        
        
        System.out.println("Vergleich Ergebnisse:");
        compareData(conKonnektor, conPortal, p);
        System.out.println("");
    }
    
    public static Connection getConnection(String connectionString) {

        Connection con = null;
    
        try {
            con = DriverManager.getConnection(connectionString);
            System.out.println("Hat geklappt!");
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return con;
    }

    public static void showDB(Connection con, String table) throws SQLException {
    
        String query = String.format("select * from %S", table);

        ResultSet ergebnis = con.createStatement().executeQuery(query);

        while (ergebnis.next()) {
            System.out.println(ergebnis.getString("id") + " - " + ergebnis.getString("username"));
        }
        System.out.println();
    }

    public static void compareData(Connection db1, Connection db2, Properties p) throws SQLException {

        String query = "select * from " + p.getProperty("db1_tbl"); 

        ResultSet ergebnis1 = db1.createStatement().executeQuery(query);
        ResultSet ergebnis2 = db2.createStatement().executeQuery(query);

        while(ergebnis1.next()) {
            ergebnis2.next();
            if(!(ergebnis1.getString("id").equals(ergebnis2.getString("id")))) {
                System.out.println("ID " + ergebnis1.getString("id") + " ist nicht identisch mit " + ergebnis2.getString("id"));
            }
            if(!(ergebnis1.getString("username").equals(ergebnis2.getString("username")))) {
                System.out.println(ergebnis1.getString("username") + " ist nicht identisch mit "  + ergebnis2.getString("username"));
            } 
        }
    }
}