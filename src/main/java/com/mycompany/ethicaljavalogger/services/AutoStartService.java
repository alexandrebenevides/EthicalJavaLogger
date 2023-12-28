package com.mycompany.ethicaljavalogger.services;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoStartService {
    private final String registryKey;
    private final String jarPath;
    
    public AutoStartService(String jarPath) {
        this.registryKey = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";
        this.jarPath = jarPath;
    }

    public boolean checkRegistry() {
        try {
            String registryValue = Advapi32Util.registryGetStringValue(WinReg.HKEY_CURRENT_USER, this.registryKey, "EthicalJavaLogger");
            return this.jarPath.equals(registryValue);
        } catch (Exception e) {
            return false;
        }
    }

    public void registerToStartWithWindows() {
        try {
            Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, this.registryKey, "EthicalJavaLogger", this.jarPath);
        } catch (Exception ex) {
            Logger.getLogger(AutoStartService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
