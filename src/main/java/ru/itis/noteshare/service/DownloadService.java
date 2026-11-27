package ru.itis.noteshare.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DownloadService {
    public void downloadFile(String fileName, HttpServletResponse resp, boolean download)
            throws IOException {
        if (fileName == null || fileName.isBlank()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл отсутствует");
            return;
        }

        File baseDir = new File(
                "C:/Users/MSI/IdeaProjects/NoteShare/src/main/webapp/assets/scores"
        ).getCanonicalFile();
        File file = new File(baseDir, fileName).getCanonicalFile();

        if (!file.toPath().startsWith(baseDir.toPath()) || !file.isFile()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден");
            return;
        }

        resp.setContentType("application/pdf");
        resp.setContentLengthLong(file.length());

        String disposition = download ? "attachment" : "inline";
        resp.setHeader("Content-Disposition", disposition + "; filename=\"score.pdf\"");

        Files.copy(file.toPath(), resp.getOutputStream());
    }
}