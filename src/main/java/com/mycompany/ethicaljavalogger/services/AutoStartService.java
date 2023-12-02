package com.mycompany.ethicaljavalogger.services;

import java.util.prefs.Preferences;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class AutoStartService {
    private String registryKey;
    private String jarPath;
    
    public AutoStartService(String registryKey, String jarPath) {
        this.registryKey = registryKey;
        this.jarPath = jarPath;
    }

    public boolean checkRegistry() {
        try {
            Preferences prefs = Preferences.userRoot().node(this.registryKey);
            String valor = prefs.get("EthicalJavaLogger", null);
            return this.jarPath.equals(valor);
        } catch (Exception e) {
            return false;
        }
    }

    public void registerToStartWithWindows() {
        try {
            System.out.println(this.jarPath);
            Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, this.registryKey, "EthicalJavaLogger", this.jarPath);
        } catch (Exception e) {
            //
        }
    }
}
