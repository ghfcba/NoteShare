package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ComposerModel;
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
        String sql = """
            INSERT INTO scores
                (title, user_id, composer_id, file_path, difficulty, moderation_status, uploaded_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            RETURNING score_id
            """;

        Timestamp time = new Timestamp(System.currentTimeMillis());

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, score.getTitle());
            ps.setLong(2, score.getUserId());
            ps.setLong(3, score.getComposerId());
            ps.setString(4, score.getFilePath());
            ps.setString(5, score.getDifficulty());
            ps.setString(6, "pending");
            ps.setTimestamp(7, time);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Не удалось получить ID сохранённых нот");
                }

                score.setScoreId(rs.getLong("score_id"));
            }

            return score;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void deleteScore(ScoreModel scoreModel){
        String sql = "delete from scores where score_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, scoreModel.getScoreId());
            int deletedRows = ps.executeUpdate();
            System.out.println("scoreId=" + scoreModel.getScoreId() + ", deletedRows=" + deletedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateScoreStatus(ScoreModel scoreModel){
        String sql = "update scores set moderation_status = ? where score_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, scoreModel.getModerationStatus());
            ps.setLong(2, scoreModel.getScoreId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ScoreModel> getScoresByComposerId(Long composerId) {
        String sql = "select * from scores where composer_id = ?";
        List<ScoreModel> scoreList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, composerId);
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

    public ScoreModel getScoreById(Long scoreId) {
        String sql = "select * from scores where score_id = ?";
        ScoreModel scoreModel = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, scoreId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    scoreModel = new ScoreModel().builder()
                            .scoreId(rs.getLong("score_id"))
                            .title(rs.getString("title"))
                            .userId(rs.getLong("user_id"))
                            .composerId(rs.getLong("composer_id"))
                            .filePath(rs.getString("file_path"))
                            .difficulty(rs.getString("difficulty"))
                            .moderationStatus(rs.getString("moderation_status"))
                            .uploadedAt(rs.getTimestamp("uploaded_at")).build();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scoreModel;
    }


    public List<ScoreModel> getAllPendingScores() {
        String sql = "select * from scores where moderation_status = 'pending' ";
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
    public List<ScoreModel> getAllApprovedScores() {
        String sql = "select * from scores where moderation_status = 'approved' ";
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

}