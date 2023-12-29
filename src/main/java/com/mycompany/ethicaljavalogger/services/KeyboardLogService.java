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
    private static String FILE_PATH;
    
    public KeyboardLogService() {
        FILE_PATH = DiretoryService.getParentApplicationPath() + "\\temp\\keyboard_logs.txt";
        this.writeHeader();
    }
    
    public void writeInputLog(String input) {
        String dateTimeText = this.getCurrentDateTimeText();
        this.writeInFile(dateTimeText + "     " + input);
    }
    
    private void writeHeader() {
        File keyboardLogFile = new File(FILE_PATH);
        System.out.println(FILE_PATH);
        if (!keyboardLogFile.exists()) {
            File parentFolder = keyboardLogFile.getParentFile();
            
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }
            
            this.writeInFile("Data e Hora             Tecla Pressionada");
            this.writeInFile("-------------------     -------------------");
        }
    }
    
    private void writeInFile(String textLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(textLine + "\n");
        } catch (IOException ex) {
            Logger.getLogger(KeyboardLogService.class.getName()).log(Level.SEVERE, null, ex);
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
