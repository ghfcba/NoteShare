package ru.itis.noteshare.service;

import jakarta.servlet.http.Part;
import ru.itis.noteshare.validation.ValidationException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/**
 * Отвечает только за физическое хранение файлов нот.
 */
public class FileStorageService {
    private static final long MAX_FILE_SIZE_BYTES = 20L * 1024 * 1024;

    private final String scoresDir;

    public FileStorageService(String scoresDir) {
        this.scoresDir = scoresDir;
    }

    public String savePdf(Part part) {
        validatePdf(part);

        String fileName = UUID.randomUUID() + ".pdf";
        File directory = new File(scoresDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            part.write(new File(directory, fileName).getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileName;
    }

    public File getFile(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new ValidationException("Файл отсутствует");
        }

        try {
            File baseDir = new File(scoresDir).getCanonicalFile();
            File file = new File(baseDir, fileName).getCanonicalFile();

            if (!file.toPath().startsWith(baseDir.toPath()) || !file.isFile()) {
                throw new ValidationException("Файл не найден");
            }

            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validatePdf(Part part) {
        if (part == null || part.getSize() == 0) {
            throw new ValidationException("Файл нот не выбран");
        }

        if (part.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new ValidationException("Файл нот не должен превышать 20 МБ");
        }

        String submittedName = part.getSubmittedFileName();
        if (submittedName == null || !submittedName.toLowerCase(Locale.ROOT).endsWith(".pdf")) {
            throw new ValidationException("Можно загружать только PDF-файлы");
        }

        String contentType = part.getContentType();
        if (contentType != null && !contentType.isBlank()
                && !contentType.equalsIgnoreCase("application/pdf")
                && !contentType.equalsIgnoreCase("application/octet-stream")) {
            throw new ValidationException("Можно загружать только PDF-файлы");
        }
    }
}
