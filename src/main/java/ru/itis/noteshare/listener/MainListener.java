package ru.itis.noteshare.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.noteshare.dao.*;
import ru.itis.noteshare.service.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import ru.itis.noteshare.utils.DataConnection;

@WebListener
public class MainListener implements ServletContextListener {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        connection = DataConnection.getConnection();

        UserDao userDao = new UserDao(connection);
        GenreDao genreDao = new GenreDao(connection);
        ScoreDao scoreDao = new ScoreDao(connection);
        ScoreGenreDao scoreGenreDao = new ScoreGenreDao(connection);
        ComposerDao composerDao = new ComposerDao(connection);

        String scoresDir = servletContext.getRealPath("/assets/scores");
        if (scoresDir == null) {
            scoresDir = new File("assets/scores").getAbsolutePath();
        }

        FileStorageService fileStorageService = new FileStorageService(scoresDir);

        AuthService authService = new AuthService(userDao);
        ComposerService composerService = new ComposerService(composerDao);
        ScoreService scoreService = new ScoreService(scoreDao, composerService);
        GenreService genreService = new GenreService(genreDao);
        ScoreGenreService scoreGenreService = new ScoreGenreService(scoreGenreDao);
        DownloadService downloadService = new DownloadService(fileStorageService);
        ScoreUploadService scoreUploadService = new ScoreUploadService(
                scoreService, composerService, genreService, scoreGenreService, fileStorageService
        );

        servletContext.setAttribute("userdao", userDao);
        servletContext.setAttribute("genredao", genreDao);
        servletContext.setAttribute("scoredao", scoreDao);
        servletContext.setAttribute("scoregenredao", scoreGenreDao);
        servletContext.setAttribute("composerdao", composerDao);

        servletContext.setAttribute("authservice", authService);
        servletContext.setAttribute("composerservice", composerService);
        servletContext.setAttribute("scoreservice", scoreService);
        servletContext.setAttribute("genreservice", genreService);
        servletContext.setAttribute("scoregenreservice", scoreGenreService);
        servletContext.setAttribute("filestorageservice", fileStorageService);
        servletContext.setAttribute("downloadservice", downloadService);
        servletContext.setAttribute("scoreuploadservice", scoreUploadService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
