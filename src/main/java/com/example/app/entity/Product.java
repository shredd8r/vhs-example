package com.example.app.entity;

import io.vhs.meta.annotation.GridColumns;
import io.vhs.meta.annotation.HelperText;
import io.vhs.meta.annotation.InstanceName;
import io.vhs.meta.annotation.Title;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS", indexes = {
        @Index(name = "IDX_PRODUCTS_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_PRODUCTS_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID")
})
@GridColumns({"name", "description", "cost"})
public class Product extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    @Title("Наименование")
    @InstanceName
    private String name;

    @NotBlank
    @Column(nullable = false, length = 1000)
    @Title("Описание")
    private String description;

    @HelperText("Стоимость указана в рублях")
    @Title("Стоимость")
    private int cost;
}
