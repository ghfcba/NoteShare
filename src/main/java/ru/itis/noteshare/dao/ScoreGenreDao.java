package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ScoreGenreModel;

import java.sql.Connection;

public class ScoreGenreDao {
    private Connection connection;

    public ScoreGenreDao(Connection connection) {
        this.connection = connection;
    }

    public void addGenreToScore(ScoreGenreModel scoreGenreModel) {

    }
}
