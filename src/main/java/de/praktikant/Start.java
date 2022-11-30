package de.praktikant;

import java.sql.*;
import java.util.*;  
import java.io.*;
import org.slf4j.*;


public class Start {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        
        Logger logger = LoggerFactory.getLogger(Start.class);
    

        FileReader reader = new FileReader("src\\main\\java\\de\\praktikant\\db.config.properties");  
        Properties p = new Properties();  
        p.load(reader); 


        String table1 = p.getProperty("db1_tbl");
        String table2 = p.getProperty("db2_tbl");
        String database1 = p.getProperty("database1");
        String database2 = p.getProperty("database2");

        String connectionDB1 = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", database1, p.getProperty("username"), p.getProperty("password"));
        String connectionDB2 = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", database2, p.getProperty("username"), p.getProperty("password"));

        
        logger.info("Verbindung zu DB1: ");
        ConnectionDB myConnectionDBKonnektor = new ConnectionDB();
        Connection conKonnektor = myConnectionDBKonnektor.getConnection(connectionDB1);
        

        logger.info("Verbindung zu DB2: ");
        ConnectionDB myConnectionDBPortal = new ConnectionDB();
        Connection conPortal = myConnectionDBPortal.getConnection(connectionDB2);


        logger.info("");
        String printDB1 = String.format("Daten aus der deine DB: %s ", database1);
        logger.info(printDB1);
        ShowDB myShowDBKonnektor = new ShowDB();
        myShowDBKonnektor.myShowDB(conKonnektor, table1);
        
        
        logger.info("");
        String printDB2 = String.format("Daten aus der deine DB: %s ", database2);
        logger.info(printDB2);
        ShowDB myShowDBPortal = new ShowDB();
        myShowDBPortal.myShowDB(conPortal, table2);
        

        logger.info("");
        logger.info("Vergleich Ergebnisse:");
        CompareData myCompareDBData = new CompareData();
        myCompareDBData.myCompareData(conKonnektor, conPortal, table1, table2);

    }
    
}