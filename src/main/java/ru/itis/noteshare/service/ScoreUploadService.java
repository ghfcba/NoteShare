package ru.itis.noteshare.service;

import jakarta.servlet.http.Part;
import ru.itis.noteshare.enums.Difficulity;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

/**
 * Сценарий загрузки нот: валидация, сохранение файла, запись в БД и привязка жанра.
 */
public class ScoreUploadService {
    private final ScoreService scoreService;
    private final ComposerService composerService;
    private final GenreService genreService;
    private final ScoreGenreService scoreGenreService;
    private final FileStorageService fileStorageService;

    public ScoreUploadService(ScoreService scoreService,
                              ComposerService composerService,
                              GenreService genreService,
                              ScoreGenreService scoreGenreService,
                              FileStorageService fileStorageService) {
        this.scoreService = scoreService;
        this.composerService = composerService;
        this.genreService = genreService;
        this.scoreGenreService = scoreGenreService;
        this.fileStorageService = fileStorageService;
    }

    public ScoreModel upload(UserModel user, String title, String difficulty,
                             String composerName, String genreName, Part part) {
        String cleanTitle = Validator.requireText(title, "Название произведения", 150);
        String cleanDifficulty = Difficulity.fromValue(difficulty).getValue();
        String cleanComposerName = Validator.requireText(composerName, "Композитор", 100);
        String cleanGenreName = Validator.requireText(genreName, "Жанр", 50);

        if (user == null) {
            throw new ValidationException("Необходимо войти в систему");
        }

        ComposerModel composer = composerService.getComposerByName(cleanComposerName);
        if (composer == null) {
            throw new ValidationException("Композитор \"" + cleanComposerName + "\" не найден");
        }

        GenreModel genre = genreService.getGenreModelByName(cleanGenreName);
        if (genre == null) {
            throw new ValidationException("Жанр \"" + cleanGenreName + "\" не найден");
        }

        String fileName = fileStorageService.savePdf(part);
        ScoreModel score = new ScoreModel(cleanTitle, fileName, cleanDifficulty);
        score = scoreService.addScore(user, score, composer);
        scoreGenreService.addGenreToScore(score, genre);

        return score;
    }
}
