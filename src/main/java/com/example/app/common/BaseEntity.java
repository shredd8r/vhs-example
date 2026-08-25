package com.example.app.common;

import com.example.app.user.User;
import io.vhs.meta.annotation.rule.VisibleWhen;
import io.vhs.meta.annotation.ui.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    @Title("ID")
    @EqualsAndHashCode.Include
    @VisibleWhen("false")
    private UUID id;

    @Column
    @Version
    @Title("Версия")
    @VisibleWhen("false")
    private Integer version;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    @Title("Создано")
    @VisibleWhen("false")
    private User userCreated;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @PastOrPresent
    @Title("Дата создания")
    @VisibleWhen("false")
    private OffsetDateTime dateCreated;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false)
    @Title("Изменено")
    @VisibleWhen("false")
    private User userModified;

    @LastModifiedDate
    @Column(insertable = false)
    @PastOrPresent
    @Title("Дата изменения")
    @VisibleWhen("false")
    private OffsetDateTime dateModified;

    @Override
    public String toString() {
        return id + "@" + getClass().getSimpleName();
    }
}
