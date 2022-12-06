package de.praktikant;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Start {

    public static void main(String[] args) {

    	 SpringApplication.run(Start.class, args);

    }
    
    @EventListener(classes = ApplicationReadyEvent.class)
	private void vergleichDBs() throws IOException {

		Logger logger = LoggerFactory.getLogger(Start.class);
      
        Properties p = new Properties(); 
        try {
            FileReader reader = new FileReader("src\\main\\java\\de\\praktikant\\db.config.properties");
            p.load(reader); 
            logger.info("Propertiesdatei Pfad wurde erkannt!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warn("Propertiesdatei Pfad konnte nicht gefunden werden");
            System.exit(0);
        } 

        String database1 = p.getProperty("connector_db");
        String database2 = p.getProperty("portal_db");
        String table1 = p.getProperty("connector_tabelle");
        String table2 = p.getProperty("portal_vergleichstabelle");

        String connectionDB1 = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", database1, p.getProperty("username"), p.getProperty("password"));
        String connectionDB2 = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", database2, p.getProperty("username"), p.getProperty("password"));
    

        logger.info("Verbindung zu DB1: ");
        ConnectionDB myConnectionDBKonnektor = new ConnectionDB();
        Connection conKonnektor = myConnectionDBKonnektor.getConnection(connectionDB1);
        
        logger.info("Verbindung zu DB2: ");
        ConnectionDB myConnectionDBPortal = new ConnectionDB();
        Connection conPortal = myConnectionDBPortal.getConnection(connectionDB2);

        /* 
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
        */

        logger.info("");
        logger.info("Vergleich Ergebnisse:");
        CompareData myCompareDBData = new CompareData();
        myCompareDBData.myCompareData(conKonnektor, conPortal, table1, table2);

	}
    
}