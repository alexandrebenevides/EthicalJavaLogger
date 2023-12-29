package com.mycompany.ethicaljavalogger.services;

import com.mycompany.ethicaljavalogger.EthicalJavaLogger;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiretoryService {
    
    public static String getApplicationPath() {
        try {
            URL jarUrl = EthicalJavaLogger.class.getProtectionDomain().getCodeSource().getLocation();
            return URLDecoder.decode(jarUrl.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DiretoryService.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    public static String getParentApplicationPath() {
        try {
            URL jarUrl = EthicalJavaLogger.class.getProtectionDomain().getCodeSource().getLocation();
            String decodedPath = URLDecoder.decode(jarUrl.getPath(), "UTF-8");
            return new File(decodedPath).getParent();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DiretoryService.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    public static String getJavawPath() {
        String javaHomePath = System.getProperty("java.home");
        return javaHomePath + "\\bin\\javaw.exe";
    }
}
