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
	private void vergleichDBs() {

		Logger logger = LoggerFactory.getLogger(Start.class);
      
        Properties p = new Properties();
        FileReader reader = null;
    
        try {
            reader = new FileReader("src\\main\\java\\de\\praktikant\\db.config.properties");
            logger.info("Propertiesdatei Pfad wurde erkannt!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warn("Propertiesdatei Pfad konnte nicht gefunden werden!");
            System.exit(0);
        } 

        try {
            p.load(reader);    
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Propertiesdatei konnte nicht geladen werden!");
            System.exit(0);
        }

        String databaseConnector = p.getProperty("connector_db");
        String databasePortal = p.getProperty("portal_db");
        String tableConnector = p.getProperty("connector_tabelle");
        String tablePortal = p.getProperty("portal_vergleichstabelle");
        String username =  p.getProperty("username");
        String password = p.getProperty("password");

        String connectionToConnector = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", databaseConnector, username, password);
        String connectionToPortal = String.format("jdbc:postgresql://localhost/%1$s?user=%2$s&password=%3$s", databasePortal, username, password);
    

        logger.info("Verbindung zu dbConnector: ");
        ConnectionDB myConnectionDBconnector = new ConnectionDB();
        Connection conConnector = myConnectionDBconnector.getConnection(connectionToConnector);
        
        logger.info("Verbindung zu dbPortal: ");
        ConnectionDB myConnectionDBportal = new ConnectionDB();
        Connection conPortal = myConnectionDBportal.getConnection(connectionToPortal);

         /*

        logger.info("");
        String printDBconnector = String.format("Daten aus der deine DB: %s ", databaseConnector);
        logger.info(printDBconnector);
        ShowDB myShowDBconnector = new ShowDB();
        myShowDBconnector.myShowDB(conConnector, tableConnector);
        
        logger.info("");
        String printDBportal = String.format("Daten aus der deine DB: %s ", databasePortal);
        logger.info(printDBportal);
        ShowDB myShowDBportal = new ShowDB();
        myShowDBportal.myShowDB(conPortal, tablePortal);
        
         */

        logger.info("");
        logger.info("Vergleich Ergebnisse:");
        CompareData myCompareDBdata = new CompareData();
        myCompareDBdata.myCompareData(conConnector, conPortal, tableConnector, tablePortal);

	}
    
}