package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.ScoreGenreDao;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.models.ScoreGenreModel;
import ru.itis.noteshare.models.ScoreModel;

public class ScoreGenreService {
    private final ScoreGenreDao scoreGenreDao;

    public ScoreGenreService(ScoreGenreDao scoreGenreDao) {
        this.scoreGenreDao = scoreGenreDao;
    }

    public void addGenreToScore(ScoreModel scoreModel, GenreModel genreModel) {
        scoreGenreDao.addGenreToScore(ScoreGenreModel.builder()
                        .scoreId(scoreModel.getScoreId())
                        .genreId(genreModel.getGenreId())
                .build());

    }
}
