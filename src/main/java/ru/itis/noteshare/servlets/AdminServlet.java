package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.ComposerService;
import ru.itis.noteshare.service.GenreService;
import ru.itis.noteshare.service.ScoreService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ScoreService scoreService;
    private GenreService genreService;
    private ComposerService composerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        scoreService = (ScoreService) config.getServletContext().getAttribute("scoreservice");
        genreService = (GenreService) config.getServletContext().getAttribute("genreservice");
        composerService= (ComposerService) config.getServletContext().getAttribute("composerservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ScoreModel> scorePending = scoreService.getPendingScores();
        List<GenreModel> allGenres = genreService.getAllGenres();
        req.setAttribute("scoresPending", scorePending);
        req.setAttribute("genres", allGenres);
        resp.setContentType("text/html;charset=UTF-8");

        req.getRequestDispatcher("/jsp/admin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        UserModel userModel = (UserModel)session.getAttribute("user");



        try {
            switch (action) {
                case "createScore":
                    Part part = req.getPart("scoreFile");
                    String title = req.getParameter("title");
                    String composer = req.getParameter("composer");
                    String lvl = req.getParameter("diff");
                    String genre = req.getParameter("genre");
                    ScoreModel scoreModel = new ScoreModel(title, lvl, genre);
                    ComposerModel composerModel = composerService.getComposerByName(composer);
                    scoreService.addScore(userModel,scoreModel, composerModel);
                    scoreService.loadFile(part, "noteshare.pdf");
                    break;
                case "deleteScore":
                    Long scoreId = Long.valueOf(req.getParameter("scoreId"));
                    scoreService.deleteScore(scoreService.getScoreById(scoreId));
                    break;
                case "updateStatus":
                    String status = req.getParameter("status");
                    ScoreModel scoreModel2 = new ScoreModel();
                    scoreModel2.setModerationStatus(status);
                    scoreService.updateScoreStatus(scoreModel2);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
