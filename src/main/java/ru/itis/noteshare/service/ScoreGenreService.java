package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.ScoreGenreDao;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.models.ScoreGenreModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.utils.DataConnection;

public class ScoreGenreService {
    private ScoreGenreDao scoreGenreDao = new ScoreGenreDao(DataConnection.getConnection());

    public void addGenreToScore(ScoreModel scoreModel, GenreModel genreModel) {
        scoreGenreDao.addGenreToScore(ScoreGenreModel.builder()
                        .scoreId(scoreModel.getScoreId())
                        .genreId(genreModel.getGenreId())
                .build());

    }
}
