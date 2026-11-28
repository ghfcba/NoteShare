package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.ScoreService;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

import java.io.IOException;
@WebServlet("/score/page")
public class ScorePageServlet extends HttpServlet {
    private ScoreService scoreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        scoreService = (ScoreService) config.getServletContext().getAttribute("scoreservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long scoreId;
        try {
            scoreId = Validator.requireId(req.getParameter("id"), "ID нот");
        } catch (ValidationException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        ScoreModel scoreModel = scoreService.getScoreById(scoreId);

        req.setAttribute("score", scoreModel);

        req.getRequestDispatcher("/jsp/score-details.jsp").forward(req,resp);

    }

}
