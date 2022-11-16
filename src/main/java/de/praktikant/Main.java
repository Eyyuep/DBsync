package de.praktikant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Class.forName("org.h2.Driver");

        String connectionKonnektor = "jdbc:postgresql://localhost/db_connector?user=postgres&password=nassim21";
        String connectionPortal = "jdbc:postgresql://localhost/db_portal?user=postgres&password=nassim21";

        Connection conKonnektor = getConnection(connectionKonnektor);
        Connection conPortal = getConnection(connectionPortal);

        //createTable(conKonnektor);
        //createTable(conPortal);

        //createDataSet(conKonnektor);
        //createDataSet(conPortal);

        //showDB(conKonnektor);
        //showDB(conPortal);

        
        //editData(conPortal);
        
        //compareData(conKonnektor,conPortal);


    }

    public static Connection getConnection(String connectionString) {

        Connection con = null;

        try {
            con = DriverManager.getConnection(connectionString);
            System.out.println("Hat geklappt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

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

    public static void showDB(Connection con) throws SQLException {

        String query = "select * from tabelle_1;";

        ResultSet ergebnis = con.createStatement().executeQuery(query);

        while (ergebnis.next()) {
            System.out.println(ergebnis.getString("id") + " - " + ergebnis.getString("username"));
        }

    }

    public static void editData(Connection con) throws SQLException {

        String query = "update tabelle_1 set username = 'ronaldinho' where id = 1";

        con.createStatement().executeUpdate(query);

    }

    public static void compareData(Connection db1, Connection db2) throws SQLException {

        String query = "select * from tabelle_1;";

        ResultSet ergebnis1 = db1.createStatement().executeQuery(query);
        ResultSet ergebnis2 = db2.createStatement().executeQuery(query);

        while(ergebnis1.next()) {
            ergebnis2.next();
            if(!(ergebnis1.getString("id").equals(ergebnis2.getString("id")))) {
                System.out.println("ID nicht identisch " + ergebnis1.getString("id"));
            }
            if(ergebnis1.getString("username") != ergebnis2.getString("username")) {
                System.out.println("Username nicht identisch "  + ergebnis1.getString("username"));
            } 
        }
    }
}