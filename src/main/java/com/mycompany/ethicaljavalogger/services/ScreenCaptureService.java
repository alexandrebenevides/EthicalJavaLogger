package com.mycompany.ethicaljavalogger.services;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;

public class ScreenCaptureService {

    public String capture() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            File tempFile = new File("temp/tempImage");
            tempFile.mkdirs();
            ImageIO.write(screenCapture, "png", tempFile);

            return tempFile.getAbsolutePath();
        } catch (AWTException | IOException ex) {
            System.err.println("Erro ao capturar a tela: " + ex.getMessage());
        }
        
        return null;
    }
    
    public String generateFileName() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        return "screencapture_" + currentDateTime.format(formatter) + ".png";
    }
}
