package com.mycompany.ethicaljavalogger.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesService {

    private final String configFile = "config.properties";
    private Properties properties;

    public ConfigPropertiesService() {
        this.properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                System.err.println("Desculpe, o arquivo de configuração '" + configFile + "' não foi encontrado.");
                return null;
            }

            prop.load(input);
        } catch (IOException ex) {
            
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