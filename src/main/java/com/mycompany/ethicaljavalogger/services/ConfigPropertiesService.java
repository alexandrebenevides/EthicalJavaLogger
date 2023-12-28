package com.mycompany.ethicaljavalogger.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigPropertiesService {

    private final String configFile;
    private final Properties properties;

    public ConfigPropertiesService() {
        this.configFile = "config.properties";
        this.properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                return null;
            }

            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(ConfigPropertiesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return prop;
    }

    public String getGoogleDriveClientId() {
        return getProperty("googledrive.client.id");
    }

    public String getGoogleDriveClientSecret() {
        return getProperty("googledrive.client.secret");
    }

    private String getProperty(String key) {
        if (properties != null) {
            return properties.getProperty(key);
        }
        
        return null;
    }
}