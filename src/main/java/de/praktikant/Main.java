package de.praktikant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Class.forName("org.h2.Driver");

        String connectionKonnektor = "jdbc:h2:./test/db1";
        String connectionPortal = "jdbc:h2:./test/db2";

        Connection con = getConnection(connectionKonnektor);
        Connection connection = getConnection(connectionPortal);

        createTable(con);
        createTable(connection);

        createDataSet(con);
        createDataSet(connection);

        showDB(con);
        showDB(connection);

        /*editData(con);
        editData(connection);*/


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

        String query = "update tabelle_1 set username = 'ronaldinho' where id = 2";

        con.createStatement().executeUpdate(query);

    }

    public static void compareData(Connection db1, Connection db2) throws SQLException {

        String query = "select db1.tabelle_1.*, db2.tabelle_1.* from db1.tabelle_1, db2.tabelle_1";

        db1.createStatement().executeUpdate(query);
        db2.createStatement().executeUpdate(query);

    }


}