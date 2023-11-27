/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ethicaljavalogger;

import com.mycompany.ethicaljavalogger.controllers.AutoStartController;
import com.mycompany.ethicaljavalogger.controllers.ScreenCaptureController;

/**
 *
 * @author alexb
 */
public class EthicalJavaLogger {

    public static void main(String[] args) {
        //new AutoStartController().configureAutoStart();
        new ScreenCaptureController().handleScreenCapture();
    }
}