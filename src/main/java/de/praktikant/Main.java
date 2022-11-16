package de.praktikant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Class.forName("org.h2.Driver");


        final String username = "";
        final String password = "";
        final String database1 = "";
        final String database2 = "";

        final String connectionKonnektor = "jdbc:postgresql://localhost/" + database1 + "?user="+ username + "&password=" + password;
        final String connectionPortal = "jdbc:postgresql://localhost/" + database2 + "?user="+ username + "&password=" + password;

        System.out.print("Verbindung zu DB1: ");
        Connection conKonnektor = getConnection(connectionKonnektor);
        System.out.print("Verbindung zu DB2: ");
        Connection conPortal = getConnection(connectionPortal);

        //createTable(conKonnektor);
        //createTable(conPortal);

        //createDataSet(conKonnektor);
        //createDataSet(conPortal);

        //showDB(conKonnektor);
        //showDB(conPortal);
       
        //editData(conPortal);
        
        //compareData(conKonnektor,conPortal);

        System.out.println("");
        System.out.println("Daten aus der deine DB: " + database1);
        showDB(conKonnektor);
        System.out.println("Daten aus der deine DB: " + database2);
        showDB(conPortal);
        
        //editData(conPortal);
        
        System.out.println("Vergleich Ergebnisse:");
        compareData(conKonnektor, conPortal);
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

    public static void showDB(Connection con) throws SQLException {

        String query = "select * from tbl_test;"; //<-- hier bitte wegen Tablename aufpassen! Unterschiede bei euch!
                                                  //    am besten einen Parameter hinzufügen und Tabelennamen dynamisch halten

        ResultSet ergebnis = con.createStatement().executeQuery(query);

        while (ergebnis.next()) {
            System.out.println(ergebnis.getString("id") + " - " + ergebnis.getString("username"));
        }
        System.out.println();
    }

    public static void compareData(Connection db1, Connection db2) throws SQLException {

        String query = "select * from tbl_test;"; //<-- hier gleiches Problem!!!

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

    // -------------------------------- Zurzeit nicht notwendige Methoden --------------------------------

    public static void createTable(Connection con) throws SQLException {

        // Identity wird zu Serial
        String query = "create table if not exists tabelle_1" +
                "(id IDENTITY," +
                "username varchar(50));";

        con.createStatement().execute(query);

    }

    public static void createDataSet(Connection con) throws SQLException {

        String query = "insert into tabelle_1(username)" +
                "values('Manfred');";

        con.createStatement().execute(query);

    }

    public static void editData(Connection con) throws SQLException {

        String query = "update tabelle_1 set username = 'ronaldinho' where id = 1";

        con.createStatement().executeUpdate(query);

    }
}