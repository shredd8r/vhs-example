package com.example.app.entity.listener;

import com.example.app.entity.File;
import io.vhs.storage.Files;
import jakarta.persistence.PostRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileEventListener {

    private final Files files;

    @PostRemove
    public void onFileDeleted(File file) {
        files.delete(file);
    }
}