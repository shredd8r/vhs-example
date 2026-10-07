package com.example.app.ui.renderer;

import com.example.app.entity.File;
import io.vhs.meta.annotation.PrototypeScope;
import io.vhs.meta.property.LongProperty;
import io.vhs.ui.renderer.SpanCellRenderer;
import io.vhs.ui.util.DatatypeFormatter;
import org.springframework.stereotype.Component;

@Component
@PrototypeScope
public class FileSizeRenderer extends SpanCellRenderer<File, Long, LongProperty<File>> {

    public FileSizeRenderer(LongProperty<File> property) {
        super(property);
    }

    @Override
    public String format(Long bytes) {
        return DatatypeFormatter.formatFileSize(bytes);
    }
}
