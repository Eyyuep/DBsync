package de.praktikant;

import java.sql.*;
import org.slf4j.*;

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
