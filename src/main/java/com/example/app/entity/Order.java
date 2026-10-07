package com.example.app.entity;

import io.vhs.meta.annotation.*;
import io.vhs.ui.field.RadioButtonGroupField;
import io.vhs.ui.util.DatatypeFormatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Title("Заказ")
@Entity
@Table(name = "ORDERS", indexes = {
        @Index(name = "IDX_ORDERS_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_ORDERS_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID"),
        @Index(name = "IDX_ORDERS_CLIENT_ID", columnList = "CLIENT_ID")
})
@GridColumns({"date", "client", "status", "totalCost"})
public class Order extends BaseEntity {

    @NotNull
    @JoinColumn(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Title("Клиент")
    private Client client;

    @NotNull
    @Column(updatable = false)
    @Title("Дата")
    private OffsetDateTime date;

    @VaadinField(RadioButtonGroupField.class)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Title("Статус")
    private OrderStatus status = OrderStatus.NEW;

    @Column(length = 2000)
    @Title("Комментарий")
    private String comment;

    @OnValueChange(expressionString = "updateTotalCost()", dependentProperties = "totalCost")
    @NotNull
    @NotEmpty
    @ElementCollection
    @CollectionTable(
            name = "ORDER_ITEMS",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            indexes = {
                    @Index(name = "IDX_ORDER_ITEMS_ORDER_ID", columnList = "ORDER_ID"),
                    @Index(name = "IDX_ORDER_ITEMS_PRODUCT_ID", columnList = "PRODUCT_ID")
            }
    )
    @MapKeyJoinColumn(name = "PRODUCT_ID")
    @Column(name = "QUANTITY")
    @Title("Состав")
    private Map<@Placeholder("Продукт") @NotNull Product,
            @Placeholder("Количество") @NotNull @Positive Integer> items;

    @HelperText("Сумма указана в рублях")
    @ReadOnlyWhen("true")
    @Title("Итог")
    private int totalCost;

    @SuppressWarnings("unused")
    public void updateTotalCost() {
        if (items == null) {
            totalCost = 0;
            return;
        }
        totalCost = items.entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .mapToInt(entry -> entry.getKey().getCost() * entry.getValue())
                .sum();
    }

    @InstanceName
    public String getTitle() {
        return "Заказ от " + DatatypeFormatter.format(date.toLocalDate());
    }
}
