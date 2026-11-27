package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ScoreGenreModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreGenreDao {
    private Connection connection;

    public ScoreGenreDao(Connection connection) {
        this.connection = connection;
    }

    public void addGenreToScore(ScoreGenreModel scoreGenreModel) {
        String sql = "insert into score_genres (score_id, genre_id) values (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, scoreGenreModel.getScoreId());
            ps.setLong(2, scoreGenreModel.getGenreId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
