package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.DownloadService;
import ru.itis.noteshare.service.ScoreService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet{
    private DownloadService downloadService = new DownloadService();
    private ScoreService scoreService = new ScoreService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        long scoreId;

        try {
            scoreId = Long.parseLong(req.getParameter("scoreId"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID нот");
            return;
        }

        ScoreModel score = scoreService.getScoreById(scoreId);

        if (score == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ноты не найдены");
            return;
        }

        boolean download = "true".equals(req.getParameter("download"));
        downloadService.downloadFile(score.getFilePath(), resp, download);
    }
}
