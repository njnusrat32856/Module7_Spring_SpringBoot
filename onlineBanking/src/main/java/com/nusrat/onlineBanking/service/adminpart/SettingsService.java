package com.nusrat.onlineBanking.service.adminPart;

import com.nusrat.onlineBanking.entities.adminPart.Settings;
import com.nusrat.onlineBanking.repository.adminpart.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public List<Settings> getAllSettings() {
        return settingsRepository.findAll();
    }

//    public Settings updateSetting(String key, String value) {
//        Settings setting = settingsRepository.findByKey(key)
//                .orElseThrow(() -> new RuntimeException("Setting not found"));
//        setting.setValue(value);
//        return settingsRepository.save(setting);
//    }

    public Settings getSettingByKey(String key) {
        return settingsRepository.findByKey(key)
                .orElseThrow(() -> new RuntimeException("Setting not found for key: " + key));
    }

    public Settings updateSetting(String key, String newValue) {
        Settings setting = getSettingByKey(key);
        if (!setting.isEditable()) {
            throw new RuntimeException("Setting with key " + key + " is not editable");
        }
        setting.setValue(newValue);
        return settingsRepository.save(setting);
    }

    public List<Settings> getAllEditableSettings() {
        return settingsRepository.findAllEditableSettings();
    }

}
