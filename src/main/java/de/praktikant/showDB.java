package de.praktikant;

import java.sql.*;
import org.slf4j.*;

public class showDB {
    
    public void myShowDB(Connection con, String table) throws SQLException {
        
        Logger logger = LoggerFactory.getLogger(Main.class);

        String formatedStringSQL = String.format("select * from %s", table);
        String query = formatedStringSQL;

        ResultSet ergebnis = con.createStatement().executeQuery(query);

        while (ergebnis.next()) {
            String ergebnisIDundUsername = String.format("ID %1$s hat Username %2$s", ergebnis.getString("id"), ergebnis.getString("username"));;
            logger.info(ergebnisIDundUsername);
            //logger.info(ergebnis.getString("id") + " - " + ergebnis.getString("username"));
        }
        logger.info("");

    }
}
