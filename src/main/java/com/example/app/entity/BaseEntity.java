package com.example.app.entity;

import io.vhs.meta.annotation.Title;
import io.vhs.meta.annotation.VisibleWhen;
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
    @EqualsAndHashCode.Include
    @VisibleWhen("false")
    @Title("ID")
    private UUID id;

    @Column
    @Version
    @VisibleWhen("false")
    @Title("Версия")
    private Integer version;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    @VisibleWhen("false")
    @Title("Создано")
    private User userCreated;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @PastOrPresent
    @VisibleWhen("false")
    @Title("Дата создания")
    private OffsetDateTime dateCreated;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false)
    @VisibleWhen("false")
    @Title("Изменено")
    private User userModified;

    @LastModifiedDate
    @Column(insertable = false)
    @PastOrPresent
    @VisibleWhen("false")
    @Title("Дата изменения")
    private OffsetDateTime dateModified;

    @Override
    public String toString() {
        return id + "@" + getClass().getSimpleName();
    }
}
