package ru.itis.noteshare;

import ru.itis.noteshare.dao.UserDao;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.ComposerService;
import ru.itis.noteshare.utils.DataConnection;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        ComposerService composerService = new ComposerService();
        ComposerModel composer1 = new ComposerModel("Чайковчкий Петр Ильич","Россия");
        ComposerModel composer2 = new ComposerModel("Иоганн Себастьян Бах","Австрия");
        ComposerModel composer3 = new ComposerModel("Хуй Херович","Хуйляндия");
        if (composerService.saveComposer(composer1)) {
            System.out.println(1);
        }
        composerService.saveComposer(composer2);
        composerService.saveComposer(composer3);
        composerService.saveComposer(composer3);

    }
}
