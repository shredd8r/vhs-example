package com.example.app.file;

import io.vhs.meta.annotation.scope.PrototypeScope;
import io.vhs.meta.field.LongMetaField;
import io.vhs.ui.component.renderer.SpanCellRenderer;
import io.vhs.ui.util.FileSizeFormatter;
import org.springframework.stereotype.Component;

@Component
@PrototypeScope
class FileSizeRenderer extends SpanCellRenderer<File, Long, LongMetaField<File>> {

    public FileSizeRenderer(LongMetaField<File> field) {
        super(field);
    }

    @Override
    public String format(Long bytes) {
        return FileSizeFormatter.formatBytes(bytes);
    }
}
