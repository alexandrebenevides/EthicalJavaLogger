/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ethicaljavalogger;

import com.mycompany.ethicaljavalogger.controllers.ScreenCaptureController;
import com.mycompany.ethicaljavalogger.services.GoogleDriveService;
import com.mycompany.ethicaljavalogger.services.ScreenCaptureService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexb
 */
public class EthicalJavaLogger {

    public static void main(String[] args) {
        new ScreenCaptureController().handleScreenCapture();
    }
}