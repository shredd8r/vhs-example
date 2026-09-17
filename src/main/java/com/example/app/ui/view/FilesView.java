package com.example.app.ui.view;

import com.example.app.entity.File;
import io.vhs.ui.view.DataView;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public class FilesView extends DataView<File> {

    public FilesView() {
        super(File.class);
    }
}
