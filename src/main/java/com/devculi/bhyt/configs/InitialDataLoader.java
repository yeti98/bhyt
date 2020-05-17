package com.devculi.bhyt.configs;

import com.devculi.bhyt.services.SettingService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final SettingService settingService;
    private boolean alreadySetup = false;

    public InitialDataLoader(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        settingService.readConfig();
        alreadySetup = true;
    }
}
