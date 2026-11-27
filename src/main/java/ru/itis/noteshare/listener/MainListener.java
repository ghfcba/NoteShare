package ru.itis.noteshare.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
import ru.itis.noteshare.dao.*;
import ru.itis.noteshare.service.AuthService;
import ru.itis.noteshare.service.ComposerService;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.utils.DataConnection;

import java.sql.Connection;

@WebListener
public class MainListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext =  sce.getServletContext();
        Connection connection = DataConnection.getConnection();
        UserDao userDao = new UserDao(connection);
        DownloadDao downloadDao = new DownloadDao(connection);
        GenreDao genreDao = new GenreDao(connection);
        RatingDao ratingDao = new RatingDao(connection);
        ScoreDao scoreDao = new ScoreDao(connection);
        ScoreGenreDao scoreGenreDao = new ScoreGenreDao(connection);
        AuthService authService = new AuthService();
        ComposerService composerService = new ComposerService();
        ScoreService scoreService = new ScoreService();

        servletContext.setAttribute("userdao", userDao);
        servletContext.setAttribute("downloaddao", downloadDao);
        servletContext.setAttribute("genredao", genreDao);
        servletContext.setAttribute("ratingdao", ratingDao);
        servletContext.setAttribute("scoredao", scoreDao);
        servletContext.setAttribute("scoregenredao", scoreGenreDao);
        servletContext.setAttribute("composerservice", composerService);
        servletContext.setAttribute("scoreservice", scoreService);
        servletContext.setAttribute("authservice", authService);

    }
}

