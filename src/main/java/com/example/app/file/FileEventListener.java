package com.example.app.file;

import io.vhs.storage.Files;
import jakarta.persistence.PostRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FileEventListener {

    private final Files files;

    @PostRemove
    public void onFileDeleted(File file) {
        files.delete(file);
    }
}