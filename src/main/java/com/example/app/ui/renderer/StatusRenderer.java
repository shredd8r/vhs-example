package com.example.app.ui.renderer;

import com.example.app.entity.Order;
import com.example.app.entity.OrderStatus;
import com.vaadin.flow.component.badge.Badge;
import com.vaadin.flow.component.badge.BadgeVariant;
import io.vhs.meta.annotation.PrototypeScope;
import io.vhs.meta.property.EnumProperty;
import io.vhs.ui.renderer.CellRenderer;
import org.springframework.stereotype.Component;

@Component
@PrototypeScope
public class StatusRenderer implements CellRenderer<Badge, OrderStatus> {

    public StatusRenderer(EnumProperty<Order, OrderStatus> ignored) {
    }

    @Override
    public Badge createComponent(OrderStatus status) {
        Badge badge = new Badge(status.getName());
        BadgeVariant variant = switch (status) {
            case NEW -> BadgeVariant.CONTRAST;
            case CANCELED -> BadgeVariant.ERROR;
            case PAID -> null;
            case DONE -> BadgeVariant.SUCCESS;
        };
        if (variant != null) {
            badge.addThemeVariants(variant);
        }
        return badge;
    }
}
