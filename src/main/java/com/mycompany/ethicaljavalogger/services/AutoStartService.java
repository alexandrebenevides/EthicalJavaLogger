package com.mycompany.ethicaljavalogger.services;

import java.util.prefs.Preferences;

public class AutoStartService {
    private String registryKey;
    private String jarPath;
    
    public AutoStartService(String registryKey, String jarPath) {
        this.registryKey = registryKey;
        this.jarPath = jarPath;
    }

    public boolean verificarRegistro() {
        try {
            Preferences prefs = Preferences.userRoot().node(this.registryKey);
            String valor = prefs.get("EthicalJavaLogger", null);
            return this.jarPath.equals(valor);
        } catch (Exception e) {
            return false;
        }
    }

    public void registrarParaIniciarComWindows() {
        try {
            Preferences prefs = Preferences.userRoot().node(this.registryKey);
            prefs.put("EthicalJavaLogger", this.jarPath);
        } catch (Exception e) {
            //
        }
    }
}
