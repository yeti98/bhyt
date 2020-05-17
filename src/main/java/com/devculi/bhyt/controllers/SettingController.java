package com.devculi.bhyt.controllers;

import com.devculi.bhyt.services.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingController {
    final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public Object getSetting() {
        return settingService.getConfigAsObject();
    }
}
