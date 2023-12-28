package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.EthicalJavaLogger;
import com.mycompany.ethicaljavalogger.services.AutoStartService;
import java.io.File;

public class AutoStartController {
    
    public void configureAutoStart() {
        String jarPath = EthicalJavaLogger.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        AutoStartService autoStartService = new AutoStartService(new File(jarPath).getAbsolutePath());
        
        if (!autoStartService.checkRegistry()) {
            autoStartService.registerToStartWithWindows();
        }
    }
    
}
