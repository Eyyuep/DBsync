package de.praktikant;

import java.sql.*;
import org.slf4j.*;

public class compareData {
    public void myCompareData(Connection db1, Connection db2, String tbl1, String tbl2) throws SQLException {
        
        Logger logger = LoggerFactory.getLogger(Main.class);

        String abfrageTB1 = String.format("select * from %s ", tbl1);
        String query1 = abfrageTB1;
        String abfrageTB2 = String.format("select * from %s ", tbl2);
        String query2 = abfrageTB2; 
 
        ResultSet ergebnis1 = db1.createStatement().executeQuery(query1);
        ResultSet ergebnis2 = db2.createStatement().executeQuery(query2);

        while(ergebnis1.next()) {
            ergebnis2.next();
            if(!(ergebnis1.getString("id").equals(ergebnis2.getString("id")))) {
                String printErgebnisID = String.format("ID %1$s ist nicht identisch mit %2$s", ergebnis1.getString("id"), ergebnis2.getString("id"));
                logger.warn(printErgebnisID);
                //logger.info("ID " + ergebnis1.getString("id") + " ist nicht identisch mit " + ergebnis2.getString("id"));
            }
            if(!(ergebnis1.getString("username").equals(ergebnis2.getString("username")))) {
                String printErgebnisUsername = String.format("Username %1$s ist nicht identisch mit %2$s", ergebnis1.getString("username"), ergebnis2.getString("username"));
                logger.warn(printErgebnisUsername);
                //logger.info(ergebnis1.getString("username") + " ist nicht identisch mit "  + ergebnis2.getString("username"));
            }
        }
    }
}