package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.itis.noteshare.enums.ModerationStatus;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.GenreService;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.service.ScoreUploadService;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
@MultipartConfig
public class AdminServlet extends HttpServlet {
    private ScoreService scoreService;
    private GenreService genreService;
    private ScoreUploadService scoreUploadService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        scoreService = (ScoreService) config.getServletContext().getAttribute("scoreservice");
        genreService = (GenreService) config.getServletContext().getAttribute("genreservice");
        scoreUploadService = (ScoreUploadService) config.getServletContext().getAttribute("scoreuploadservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ScoreModel> scorePending = scoreService.getPendingScores();
        List<GenreModel> allGenres = genreService.getAllGenres();
        req.setAttribute("scoresPending", scorePending);
        req.setAttribute("genres", allGenres);
        resp.setContentType("text/html;charset=UTF-8");

        req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            switch (action == null ? "" : action) {
                case "createScore" -> createScore(req);
                case "deleteScore" -> deleteScore(req);
                case "updateStatus" -> updateStatus(req);
                default -> throw new ValidationException("Неизвестное действие");
            }
            resp.sendRedirect(req.getContextPath() + "/admin");
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        } catch (RuntimeException e) {
            req.setAttribute("error", "Не удалось выполнить действие. Попробуйте позже.");
            doGet(req, resp);
        }
    }

    private void createScore(HttpServletRequest req) throws ServletException, IOException {
        UserModel userModel = (UserModel) req.getSession().getAttribute("user");

        scoreUploadService.upload(
                userModel,
                req.getParameter("title"),
                req.getParameter("diff"),
                req.getParameter("composer"),
                req.getParameterValues("genre"),
                req.getPart("scoreFile")
        );
    }

    private void deleteScore(HttpServletRequest req) {
        Long scoreId = Validator.requireId(req.getParameter("scoreId"), "ID нот");
        ScoreModel score = scoreService.getScoreById(scoreId);

        if (score == null) {
            throw new ValidationException("Ноты с указанным ID не найдены");
        }

        scoreService.deleteScore(score);
    }

    private void updateStatus(HttpServletRequest req) {
        Long scoreId = Validator.requireId(req.getParameter("scoreId"), "ID нот");
        ModerationStatus status = ModerationStatus.fromValue(req.getParameter("status"));

        ScoreModel score = scoreService.getScoreById(scoreId);
        if (score == null) {
            throw new ValidationException("Ноты с указанным ID не найдены");
        }

        if (status.equals("rejected")) {
            scoreService.deleteScore(score);
        }

        score.setModerationStatus(status.getValue());
        scoreService.updateScoreStatus(score);
    }
}
