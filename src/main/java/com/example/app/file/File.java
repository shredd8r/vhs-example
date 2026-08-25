package com.example.app.file;

import com.example.app.common.BaseEntity;
import io.vhs.meta.annotation.naming.InstanceName;
import io.vhs.meta.annotation.ui.GridColumns;
import io.vhs.meta.annotation.ui.Title;
import io.vhs.meta.annotation.vaadin.VaadinRenderer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Title("Файл")
@Table(name = "FILES", indexes = {
        @Index(name = "IDX_FILES_USER_CREATED_ID", columnList = "USER_CREATED_ID"),
        @Index(name = "IDX_FILES_USER_MODIFIED_ID", columnList = "USER_MODIFIED_ID")
})
@Entity(name = "vhs_File")
@GridColumns({"name", "mimeType", "size"})
@EntityListeners(FileEventListener.class)
public class File extends BaseEntity implements io.vhs.storage.File {

    @InstanceName
    @Column(updatable = false, nullable = false)
    @Title("Наименование")
    private String name;

    @Column(updatable = false, nullable = false)
    @Title("Путь")
    private String path;

    @Column(updatable = false, nullable = false)
    @Title("Тип контента")
    private String mimeType;

    @Positive
    @Column(updatable = false, nullable = false)
    @VaadinRenderer(FileSizeRenderer.class)
    @Title("Размер")
    private long size;
}
