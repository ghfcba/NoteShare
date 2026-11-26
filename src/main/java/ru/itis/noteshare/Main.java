package ru.itis.noteshare;

import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.ScoreService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ScoreService service = new ScoreService();

        List<ScoreModel> list = service.getAllScores();

        for (ScoreModel scoreModel : list) {
            System.out.println(scoreModel);
        }
    }
}