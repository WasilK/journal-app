package com.wasil.journal_app.app_cache;

import com.wasil.journal_app.models.ConfigJournalApp;
import com.wasil.journal_app.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        for(ConfigJournalApp configJournalApp : configJournalAppRepository.findAll()){
            appCache.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
