package ru.itis.noteshare.service;

import java.io.File;

public class DownloadService {
    private final FileStorageService fileStorageService;

    public DownloadService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public File getFile(String fileName) {
        return fileStorageService.getFile(fileName);
    }
}
