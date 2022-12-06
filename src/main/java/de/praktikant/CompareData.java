package de.praktikant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompareData {

    public void myCompareData(Connection dbConnector, Connection dbPortal, String connectorTable, String portalTable) {

        try {
            Logger logger = LoggerFactory.getLogger(Start.class);

            String QueryConnectorTable = String.format("select * from %s ", connectorTable);
            
            String QueryPortalTable = String.format("select * from %s ", portalTable);
    
            ResultSet resultUsername = dbConnector.createStatement().executeQuery(QueryConnectorTable);
            ResultSet resultEmail = dbPortal.createStatement().executeQuery(QueryPortalTable);

            while(resultUsername.next()) {
                resultEmail.next();
                if(!(resultUsername.getString("id").equals(resultEmail.getString("id")))) {
                    String printResultID = String.format("ID %1$s ist nicht identisch mit %2$s", resultUsername.getString("id"), resultEmail.getString("id"));
                    logger.warn(printResultID);  
                }
                if(!(resultUsername.getString("username").equals(resultEmail.getString("username")))) {
                    String printResultUsername = String.format("Username %1$s ist nicht identisch mit %2$s", resultUsername.getString("username"), resultEmail.getString("username"));
                    logger.warn(printResultUsername);
                }
            }

        } catch (SQLException e) {
            e.getMessage();
        }

    }
    
}
