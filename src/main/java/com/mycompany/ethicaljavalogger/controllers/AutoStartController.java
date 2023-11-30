package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.EthicalJavaLogger;
import com.mycompany.ethicaljavalogger.services.AutoStartService;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoStartController {
    
    public void configureAutoStart() {
        String registryKey = "Software\\Microsoft\\Windows\\CurrentVersion\\Run\\EthicalJavaLogger";
        String jarPath = EthicalJavaLogger.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(jarPath);
        System.out.println(new File(jarPath).getAbsolutePath());
        AutoStartService autoStartService = new AutoStartService(registryKey, new File(jarPath).getAbsolutePath());
        if (!autoStartService.verificarRegistro()) {
            autoStartService.registrarParaIniciarComWindows();
            System.out.println("Registrado para iniciar com o Windows.");
        } else {
            System.out.println("Já registrado para iniciar com o Windows.");
        }
    }
    
}
