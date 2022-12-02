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
            logger.info("Hat geklappt!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
        
    }
    
}
