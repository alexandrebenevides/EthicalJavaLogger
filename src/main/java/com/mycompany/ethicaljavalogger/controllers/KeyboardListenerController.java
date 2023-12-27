package com.mycompany.ethicaljavalogger.controllers;

import com.github.kwhat.jnativehook.NativeHookException;
import com.mycompany.ethicaljavalogger.services.KeyboardHookService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardListenerController {
    
    public void handleKeyboardListener() {
        try {
            KeyboardHookService keyboardHookService = new KeyboardHookService();
            keyboardHookService.startHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(KeyboardListenerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}