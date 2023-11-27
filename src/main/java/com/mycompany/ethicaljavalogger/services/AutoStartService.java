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
            Preferences prefs = Preferences.userRoot().node("com").node(this.registryKey);
            String valor = prefs.get("Autostart", null);
            return this.jarPath.equals(valor);
        } catch (Exception e) {
            return false;
        }
    }

    public void registrarParaIniciarComWindows() {
        try {
            Preferences prefs = Preferences.userRoot().node("com").node(this.registryKey);
            prefs.put("Autostart", this.jarPath);
        } catch (Exception e) {
            //
        }
    }
}
