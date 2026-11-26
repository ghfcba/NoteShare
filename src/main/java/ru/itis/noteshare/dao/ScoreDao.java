package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ScoreModel;

import java.sql.*;
import java.util.Date;

public class ScoreDao {
    private Connection connection;

    public ScoreDao(Connection connection) {
        this.connection = connection;
    }

    public ScoreModel saveScore(ScoreModel score) {
        String sql = "insert into scores (title, user_id, composer_id, file_path, difficulty, moderation_status, uploaded_at) values (?, ?, ?, ?, ?, ?, ?, ?)";
        java.util.Date date = new Date();

        Timestamp time = new Timestamp(date.getTime());
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, score.getTitle());
            ps.setLong(2, score.getUserId());
            ps.setLong(3, score.getComposerId());
            ps.setString(4, score.getFilePath());
            ps.setString(5, score.getDifficulty());
            ps.setString(6, "pending");
            ps.setTimestamp(7, time);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return score;
    }
}