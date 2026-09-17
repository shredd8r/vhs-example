package com.example.app.ui;

import com.example.app.entity.Client;
import com.example.app.entity.Order;
import com.example.app.entity.OrderItem;
import com.example.app.entity.Product;
import com.example.app.ui.view.FilesView;
import com.example.app.ui.view.UsersView;
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
        path = "clients",
        icon = VaadinIcon.USER_STAR,
        title = "Клиенты",
        parameters = @Parameter(clazz = Client.class))
@MenuItem(
        view = DataView.class,
        path = "orders",
        icon = VaadinIcon.CALENDAR_BRIEFCASE,
        title = "Заказы",
        parameters = @Parameter(clazz = Order.class))
@MenuItem(
        view = DataView.class,
        path = "products",
        icon = VaadinIcon.SUITCASE,
        title = "Товары",
        parameters = @Parameter(clazz = Product.class))
@MenuItem(
        view = DataView.class,
        path = "order-items",
        icon = VaadinIcon.LIST,
        title = "Все покупки",
        parameters = @Parameter(clazz = OrderItem.class))
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
                        title = "Хранилище")
        })
public class MainView extends VhsLayout {
}
