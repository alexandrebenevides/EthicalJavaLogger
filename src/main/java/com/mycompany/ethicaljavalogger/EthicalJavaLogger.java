/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ethicaljavalogger;

import com.mycompany.ethicaljavalogger.services.ConfigPropertiesService;
import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexb
 */
public class EthicalJavaLogger {

    public static void main(String[] args) {
        try {
            ConfigPropertiesService configPropertiesService = new ConfigPropertiesService();
            
            GoogleDriveService driveService = new GoogleDriveService(configPropertiesService.getGoogleDriveClientId(), configPropertiesService.getGoogleDriveClientSecret());
            System.out.println(driveService.createFolderIfNotExists("ethicaljavalogger"));
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(EthicalJavaLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
