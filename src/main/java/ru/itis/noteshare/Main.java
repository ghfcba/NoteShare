package ru.itis.noteshare;

import ru.itis.noteshare.dao.ScoreDao;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.utils.DataConnection;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ScoreDao scoreDao = new ScoreDao(DataConnection.getConnection());
        ScoreModel scoreModel = scoreDao.getScoreById(4L);

        System.out.println(scoreModel);

        scoreDao.deleteScore(scoreModel);
    }
}