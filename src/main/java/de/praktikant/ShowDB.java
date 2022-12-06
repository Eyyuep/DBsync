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
            String query = formatedStringSQL;

            ResultSet ergebnis = con.createStatement().executeQuery(query);
            
            while (ergebnis.next()) {
                String ergebnisIDundUsername = String.format("ID %1$s hat Username %2$s", ergebnis.getString("id"), ergebnis.getString("username"));;
                logger.info(ergebnisIDundUsername);
            }
            logger.info("");
      
        } catch (SQLException e) {
            e.getMessage();
        }
        
    }
    
}
