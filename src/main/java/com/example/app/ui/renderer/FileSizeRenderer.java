package com.example.app.ui.renderer;

import com.example.app.entity.File;
import io.vhs.meta.annotation.scope.PrototypeScope;
import io.vhs.meta.property.LongProperty;
import io.vhs.ui.component.renderer.SpanCellRenderer;
import io.vhs.ui.util.PluralForms;
import org.springframework.stereotype.Component;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@Component
@PrototypeScope
public class FileSizeRenderer extends SpanCellRenderer<File, Long, LongProperty<File>> {

    public FileSizeRenderer(LongProperty<File> property) {
        super(property);
    }

    @Override
    public String format(Long bytes) {
        bytes = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (bytes < 1024) {
            return bytes + " " + PluralForms.choose(bytes, "байт", "байта", "байт");
        }
        long value = bytes;
        CharacterIterator ci = new StringCharacterIterator("кмгтпэ");
        for (int i = 40; i >= 0 && bytes > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %cб", value / 1024.0, ci.current());
    }
}
