package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ScoreModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreDao {
    private Connection connection;

    public ScoreDao(Connection connection) {
        this.connection = connection;
    }

    public ScoreModel saveScore(ScoreModel score) {
        String sql = "insert into scores (title, user_id, composer_id, file_path, difficulty, moderation_status, uploaded_at) values (?, ?, ?, ?, ?, ?, ?)";
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

    public List<ScoreModel> getAllNote() {
        String sql = "select * from scores";
        List<ScoreModel> scoreList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ScoreModel scoreModel = ScoreModel.builder()
                            .scoreId(rs.getLong("score_id"))
                            .title(rs.getString("title"))
                            .userId(rs.getLong("user_id"))
                            .composerId(rs.getLong("composer_id"))
                            .filePath(rs.getString("file_path"))
                            .difficulty(rs.getString("difficulty"))
                            .moderationStatus(rs.getString("moderation_status"))
                            .uploadedAt(rs.getTimestamp("uploaded_at")).build();
                    scoreList.add(scoreModel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scoreList;
    }

    public List<ScoreModel> getTitleLike(String title) {
        String sql = "select * from scores where title = ?";
        List<ScoreModel> scoreLikeTitle = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ScoreModel scoreModel = ScoreModel.builder()
                            .scoreId(rs.getLong("score_id"))
                            .title(rs.getString("title"))
                            .userId(rs.getLong("user_id"))
                            .composerId(rs.getLong("composer_id"))
                            .filePath(rs.getString("file_path"))
                            .difficulty(rs.getString("difficulty"))
                            .moderationStatus(rs.getString("moderation_status"))
                            .uploadedAt(rs.getTimestamp("uploaded_at")).build();
                    scoreLikeTitle.add(scoreModel);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return scoreLikeTitle;
    }
}