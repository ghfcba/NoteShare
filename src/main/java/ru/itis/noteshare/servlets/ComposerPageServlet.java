package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.ComposerService;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

import java.io.IOException;
import java.util.List;

@WebServlet("/composer/page")
public class ComposerPageServlet extends HttpServlet {
    private ComposerService composerService;
    private ScoreService scoreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        composerService = (ComposerService) config.getServletContext().getAttribute("composerservice");
        scoreService = (ScoreService) config.getServletContext().getAttribute("scoreservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long compId;
        try {
            compId = Validator.requireId(req.getParameter("id"), "ID композитора");
        } catch (ValidationException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        ComposerModel composerModel = composerService.getComposerById(compId);
        List<ScoreModel> scoreList = scoreService.getScoresByComposerId(compId);

        req.setAttribute("composer", composerModel);
        req.setAttribute("scoreList", scoreList);
        req.getRequestDispatcher("/jsp/composer-details.jsp").forward(req,resp);
    }
}
