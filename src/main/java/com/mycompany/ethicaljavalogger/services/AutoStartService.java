package com.mycompany.ethicaljavalogger.services;

import java.util.prefs.Preferences;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class AutoStartService {
    private String registryKey;
    private String jarPath;
    
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
            System.out.println(this.jarPath);
            Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, this.registryKey, "EthicalJavaLogger", this.jarPath);
        } catch (Exception e) {
            //
        }
    }
}
