package com.example.app.entity;

import com.example.app.ui.renderer.StatusRenderer;
import io.vhs.meta.annotation.InstanceName;
import io.vhs.meta.annotation.VaadinRenderer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@VaadinRenderer(StatusRenderer.class)
public enum OrderStatus {

    NEW("Новый"),
    CANCELED("Отменен"),
    PAID("Оплачен"),
    DONE("Завершен");

    @InstanceName
    private final String name;
}
