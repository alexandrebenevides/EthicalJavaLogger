package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.EthicalJavaLogger;
import com.mycompany.ethicaljavalogger.services.AutoStartService;
import java.io.File;

public class AutoStartController {
    
    public void configureAutoStart() {
        String registryKey = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";
        String jarPath = EthicalJavaLogger.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        AutoStartService autoStartService = new AutoStartService(new File(jarPath).getAbsolutePath());
        
        if (!autoStartService.checkRegistry()) {
            autoStartService.registerToStartWithWindows();
            System.out.println("Registrado para iniciar com o Windows.");
        } else {
            System.out.println("Já registrado para iniciar com o Windows.");
        }
    }
    
}
