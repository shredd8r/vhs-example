package com.example.app.entity;

import io.vhs.meta.Metadata;
import io.vhs.meta.annotation.GridColumns;
import io.vhs.meta.annotation.InstanceName;
import io.vhs.meta.annotation.Title;
import io.vhs.meta.annotation.VisibleWhen;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEMS")
@GridColumns({"order", "product", "quantity"})
public class OrderItem {

    @EmbeddedId
    @VisibleWhen("id != null")
    @Title("ID")
    private OrderItemId id;

    @Title("Заказ")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Title("Продукт")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Title("Количество")
    @Column(name = "QUANTITY", nullable = false)
    @Positive
    private Integer quantity;

    @InstanceName
    public String getTitle(Metadata metadata) {
        return metadata.getInstanceName(order) + " (состав)";
    }

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @Title("Идентификатор")
    public static class OrderItemId {

        @Title("ID заказа")
        private UUID orderId;

        @Title("ID продукта")
        private UUID productId;
    }
}
