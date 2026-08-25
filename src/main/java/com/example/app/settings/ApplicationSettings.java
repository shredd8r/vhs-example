package com.example.app.settings;

import io.vhs.data.Dao;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationSettings {

    private final Dao dao;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private volatile Settings settings;

    @PostConstruct
    void init() {
        if (dao.load(Settings.class).exists()) {
            reload();
        } else {
            settings = dao.newInstance(Settings.class);
            dao.create(settings);
        }
    }

    void reload() {
        settings = dao.load(Settings.class).joinAll().one();
    }

    public String getApiKey() {
        return settings.getApiKey();
    }
}