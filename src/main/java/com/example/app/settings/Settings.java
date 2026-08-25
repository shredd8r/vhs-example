package com.example.app.settings;

import com.example.app.common.BaseEntity;
import io.vhs.meta.annotation.ui.Title;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Title("Настройки")
@Table(name = "SETTINGS", indexes = {
        @Index(name = "IDX_SETTINGS_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_SETTINGS_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID")
})
@Entity(name = "vhs_Settings")
public class Settings extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    @Title("Секретный ключ")
    private String apiKey;

    @PostConstruct
    void init() {
        this.apiKey = "-----";
    }
}
