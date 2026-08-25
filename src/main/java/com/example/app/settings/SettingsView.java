package com.example.app.settings;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.vhs.data.Dao;
import io.vhs.ui.Actions;
import io.vhs.ui.Forms;
import io.vhs.ui.Notifications;
import io.vhs.ui.component.action.Action;
import io.vhs.ui.component.form.Form;
import io.vhs.ui.util.VaadinUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public class SettingsView extends VerticalLayout {

    @Autowired
    private Forms forms;
    @Autowired
    private Actions actions;
    @Autowired
    private Dao dao;
    @Autowired
    private Notifications notifications;
    @Autowired
    private ApplicationSettings applicationSettings;

    private Form<Settings> form;
    private Action saveAction;

    @PostConstruct
    void init() {
        form = forms.edit(applicationSettings.getSettings()).build();
        saveAction = actions.createSaveAction(this::onSaveButtonClick);

        setClassName("vhs-card");
        add(new H3("Настройки"), form, saveAction.button());
    }

    private void onSaveButtonClick(ComponentEvent<?> event) {
        if (form.isValid()) {
            dao.save(form.getSaveContext());
            applicationSettings.reload();
            form.setValue(applicationSettings.getSettings());
            notifications.info("Сохранено!", "Настройки успешно обновлены");
        } else {
            VaadinUtils.shakeComponent(event.getSource());
        }
    }
}
