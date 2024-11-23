package com.nusrat.onlineBanking.controller.adminpart;

import com.nusrat.onlineBanking.entities.adminPart.Settings;
import com.nusrat.onlineBanking.service.adminPart.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/")
    public List<Settings> getAllSettings() {
        return settingsService.getAllSettings();
    }

    @GetMapping("/{key}")
    public Settings getSettingByKey(@PathVariable String key) {
        return settingsService.getSettingByKey(key);
    }

    @PutMapping("/{key}")
    public Settings updateSetting(
            @PathVariable String key,
            @RequestParam String value) {
        return settingsService.updateSetting(key, value);
    }
}
