package de.praktikant;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
 
public class create_properties {
 
    public static void main(String[] args) {
        try {
            Properties unsereWebseiten = new Properties();
             
            unsereWebseiten.setProperty("youtube", "www.youtube.com");
            unsereWebseiten.setProperty("google", "www.google.com");
             
            FileOutputStream fos = new FileOutputStream("db_properties");
            unsereWebseiten.storeToXML(fos, "keinen Kommentar");
             
            Properties unsereWebseitenIn = new Properties();
            unsereWebseitenIn.loadFromXML(new FileInputStream("db.properties"));
            System.out.println("Webseiten: " + unsereWebseitenIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}