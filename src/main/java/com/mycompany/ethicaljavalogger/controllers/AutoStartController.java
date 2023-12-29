package com.mycompany.ethicaljavalogger.controllers;

import com.mycompany.ethicaljavalogger.services.AutoStartService;
import com.mycompany.ethicaljavalogger.services.DiretoryService;
import java.io.File;

public class AutoStartController {
    
    public void configureAutoStart() {
        AutoStartService autoStartService = new AutoStartService(new File(DiretoryService.getApplicationPath()).getAbsolutePath());
        
        if (!autoStartService.checkRegistry()) {
            autoStartService.registerToStartWithWindows();
        }
    }
    
}
