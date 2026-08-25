package com.example.app.user;

import com.example.app.common.BaseEntity;
import com.example.app.file.File;
import io.vhs.data.HasAvatar;
import io.vhs.data.HasZoneId;
import io.vhs.meta.annotation.naming.InstanceName;
import io.vhs.meta.annotation.rule.ReadOnlyWhen;
import io.vhs.meta.annotation.ui.GridColumns;
import io.vhs.meta.annotation.ui.Password;
import io.vhs.meta.annotation.ui.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Title("Пользователь")
@Table(name = "USERS", indexes = {
        @Index(name = "IDX_USERS_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_USERS_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID")
})
@Entity(name = "vhs_User")
@GridColumns({"name", "username", "role"})
public class User extends BaseEntity implements UserDetails, HasZoneId, HasAvatar {

    @InstanceName
    @Column(nullable = false)
    @NotBlank
    @Title("Имя")
    private String name;

    @Column(unique = true, updatable = false, nullable = false)
    @NotBlank
    @Title("Логин")
    private String username;

    @Password
    @Column(nullable = false)
    @Size(min = 5)
    @Title("Пароль")
    private String password;

    @Column
    @Title("Активен")
    @ReadOnlyWhen("\"admin\".equals(username)")
    private boolean enabled = true;

    @Column
    @Title("Часовой пояс")
    private ZoneId zoneId;

    @Column
    @Enumerated(EnumType.STRING)
    @Title("Роль")
    private UserRole role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Title("Аватар")
    private File avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role != null
                ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()))
                : List.of();
    }
}
