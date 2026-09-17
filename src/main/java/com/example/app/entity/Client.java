package com.example.app.entity;

import io.vhs.meta.annotation.naming.InstanceName;
import io.vhs.meta.annotation.rule.OnValueChange;
import io.vhs.meta.annotation.rule.VisibleWhen;
import io.vhs.meta.annotation.ui.GridColumns;
import io.vhs.meta.annotation.ui.Mask;
import io.vhs.meta.annotation.ui.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Title("Клиент")
@Entity
@Table(name = "CLIENTS", indexes = {
        @Index(name = "IDX_CLIENTS_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_CLIENTS_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID")
})
@GridColumns({"fullName", "phone", "email"})
public class Client extends BaseEntity {

    @InstanceName
    @VisibleWhen("false")
    @Title("ФИО")
    private String fullName;

    @OnValueChange(expressionString = "updateFullName()", dependentProperties = "fullName")
    @Title("Фамилия")
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @OnValueChange(expressionString = "updateFullName()", dependentProperties = "fullName")
    @Title("Имя")
    private String firstName;

    @OnValueChange(expressionString = "updateFullName()", dependentProperties = "fullName")
    @Title("Отчество")
    private String middleName;

    @NotBlank
    @Title("Телефон")
    @Mask("+{7} (000) 000-00-00")
    @Column(unique = true)
    private String phone;

    @Email
    @Title("Почта")
    private String email;

    @OrderBy("date")
    @GridColumns({"date", "status", "totalCost"})
    @OneToMany(mappedBy = "client")
    @Title("Заказы")
    private List<Order> orders;

    @SuppressWarnings("unused")
    public void updateFullName() {
        fullName = Stream.of(lastName, firstName, middleName)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
    }
}
