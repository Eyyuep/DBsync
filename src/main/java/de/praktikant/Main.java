package de.praktikant;

import java.sql.*;
import java.util.*;  
import java.io.*;
import org.slf4j.*;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Hello World");
        
        

        FileReader reader = new FileReader("src\\main\\java\\de\\praktikant\\db.config.properties");  
        Properties p = new Properties();  
        p.load(reader);  

        String table1 = p.getProperty("db1_tbl");
        String table2 = p.getProperty("db2_tbl");
        String database1 = p.getProperty("database1");
        String database2 = p.getProperty("database2");

        //String connectionDB1 = String.format("jdbc:postgresql://localhost/ %1$s ?user= %2$s &password= %3$s", database1, p.getProperty("username"), p.getProperty("password"));
        //String connectionDB2 = String.format("jdbc:postgresql://localhost/ %1$s ?user= %2$s &password= %3$s", database2, p.getProperty("username"), p.getProperty("password"));

        final String connectionKonnektor = "jdbc:postgresql://localhost/" + database1 + "?user="+ p.getProperty("username") + "&password=" + p.getProperty("password");
        final String connectionPortal = "jdbc:postgresql://localhost/" + database2 + "?user="+ p.getProperty("username") + "&password=" + p.getProperty("password");

        System.out.print("Verbindung zu DB1: ");
        Connection conKonnektor = getConnection(connectionKonnektor);
        System.out.print("Verbindung zu DB2: ");
        Connection conPortal = getConnection(connectionPortal);

        System.out.println("");
        String printDB1 = String.format("Daten aus der deine DB: %s ", database1);
        System.out.println(printDB1);
        showDB(conKonnektor, table1);
        String printDB2 = String.format("Daten aus der deine DB: %s ", database2);
        System.out.println(printDB2);
        showDB(conPortal, table2);
        

        System.out.println("Vergleich Ergebnisse:");
        compareData(conKonnektor, conPortal, table1, table2);
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
    
        String formatedStringSQL = String.format("select * from %s", table);
        String query = formatedStringSQL;

        ResultSet ergebnis = con.createStatement().executeQuery(query);

        while (ergebnis.next()) {
            String ergebnisIDundUsername = String.format("ID is: %1$s und Username is: %2$s", ergebnis.getString("id"), ergebnis.getString("username"));;
            System.out.println(ergebnisIDundUsername);
            //System.out.println(ergebnis.getString("id") + " - " + ergebnis.getString("username"));
        }
        System.out.println();

    }

    public static void compareData(Connection db1, Connection db2, String tbl1, String tbl2) throws SQLException {
        
        String abfrageTB1 = String.format("select * from %s ", tbl1);
        String query1 = abfrageTB1;
        String abfrageTB2 = String.format("select * from %s ", tbl2);
        String query2 = abfrageTB2; 
 
        ResultSet ergebnis1 = db1.createStatement().executeQuery(query1);
        ResultSet ergebnis2 = db2.createStatement().executeQuery(query2);

        while(ergebnis1.next()) {
            ergebnis2.next();
            if(!(ergebnis1.getString("id").equals(ergebnis2.getString("id")))) {
                String printErgebnisID = String.format("ID %1$s ist nicht identisch mit %2$s", ergebnis1.getString("id"), ergebnis2.getString("id"));
                System.out.println(printErgebnisID);
                //System.out.println("ID " + ergebnis1.getString("id") + " ist nicht identisch mit " + ergebnis2.getString("id"));
            }
            if(!(ergebnis1.getString("username").equals(ergebnis2.getString("username")))) {
                String printErgebnisUsername = String.format("Username %1$s ist nicht identisch mit %2$s", ergebnis1.getString("username"), ergebnis2.getString("username"));
                System.out.println(printErgebnisUsername);
                //System.out.println(ergebnis1.getString("username") + " ist nicht identisch mit "  + ergebnis2.getString("username"));
            }
        }
    }
    
}