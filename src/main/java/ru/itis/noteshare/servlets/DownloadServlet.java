package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.DownloadService;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private DownloadService downloadService;
    private ScoreService scoreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        downloadService = (DownloadService) config.getServletContext().getAttribute("downloadservice");
        scoreService = (ScoreService) config.getServletContext().getAttribute("scoreservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long scoreId = Validator.requireId(req.getParameter("scoreId"), "ID нот");

        ScoreModel score = scoreService.getScoreById(scoreId);

        if (score == null) {
            resp.sendError(404, "Ноты не найдены");
            return;
        }

        File file;
        try {
            file = downloadService.getFile(score.getFilePath());
        } catch (ValidationException e) {
            resp.sendError(404, e.getMessage());
            return;
        }

        Files.copy(file.toPath(), resp.getOutputStream());
    }
}
