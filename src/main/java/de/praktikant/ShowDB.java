package de.praktikant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowDB {
    
    public void myShowDB(Connection con, String table) {
        
        try {
            Logger logger = LoggerFactory.getLogger(Start.class);

            String formatedStringSQL = String.format("select * from %s", table);

            ResultSet result = con.createStatement().executeQuery(formatedStringSQL);
            
            while (result.next()) {
                String fullResult = String.format("ID %1$s hat Username %2$s", result.getString("id"), result.getString("username"));;
                logger.info(fullResult);
            }
            logger.info("");
      
        } catch (SQLException e) {
            e.getMessage();
        }
        
    }
    
}
