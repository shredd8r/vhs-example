package com.example.app;

import com.example.app.demo.DemoEntity;
import com.example.app.file.FilesView;
import com.example.app.settings.SettingsView;
import com.example.app.user.UsersView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import io.vhs.ui.annotation.MenuItem;
import io.vhs.ui.annotation.Parameter;
import io.vhs.ui.annotation.SubMenuItem;
import io.vhs.ui.view.DataView;
import io.vhs.ui.view.VhsLayout;

@Route("")
@MenuItem(
        view = DataView.class,
        path = "demo",
        icon = VaadinIcon.CLUSTER,
        title = "Демо объекты",
        parameters = @Parameter(clazz = DemoEntity.class))
@MenuItem(
        icon = VaadinIcon.COG,
        title = "Система",
        subMenu = {
                @SubMenuItem(
                        view = UsersView.class,
                        path = "users",
                        icon = VaadinIcon.USER_CARD,
                        title = "Пользователи"),
                @SubMenuItem(
                        view = FilesView.class,
                        path = "files",
                        icon = VaadinIcon.FOLDER_OPEN_O,
                        title = "Хранилище"),
                @SubMenuItem(
                        view = SettingsView.class,
                        path = "settings",
                        icon = VaadinIcon.COGS,
                        title = "Настройки")
        })
public class MainView extends VhsLayout {
}
