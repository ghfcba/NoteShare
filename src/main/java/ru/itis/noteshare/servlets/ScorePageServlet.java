package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.ScoreService;

import java.io.IOException;
@WebServlet("/score/page")
public class ScorePageServlet extends HttpServlet {
    ScoreService scoreService = new ScoreService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long scoreId = Long.valueOf(req.getParameter("id"));

        ScoreModel scoreModel = scoreService.getScoreById(scoreId);

        req.setAttribute("score", scoreModel);

        req.getRequestDispatcher("/jsp/score-details.jsp").forward(req,resp);

    }

}
