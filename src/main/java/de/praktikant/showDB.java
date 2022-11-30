package de.praktikant;

import java.sql.*;
import org.slf4j.*;

public class ShowDB {
    
    public void myShowDB(Connection con, String table) throws SQLException {
        
        try {
            Logger logger = LoggerFactory.getLogger(Start.class);

            String formatedStringSQL = String.format("select * from %s", table);
            String query = formatedStringSQL;

            ResultSet ergebnis = con.createStatement().executeQuery(query);
            
            while (ergebnis.next()) {
                String ergebnisIDundUsername = String.format("ID %1$s hat Username %2$s", ergebnis.getString("id"), ergebnis.getString("username"));;
                logger.info(ergebnisIDundUsername);
            }
            logger.info("");
      
        } catch (Exception e) {
            e.getMessage();
        }
        
    }
    
}