package de.praktikant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionDB {

    public Connection getConnection(String connectionString) {

        Connection con = null;

        Logger logger = LoggerFactory.getLogger(Start.class);
    
        try {
            con = DriverManager.getConnection(connectionString);
            logger.info("Verbindung ist besteht!");
            
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Verbindung konnte nicht hergestellt werden!");
            System.exit(0);
        }
        return con;
        
    }
    
}
