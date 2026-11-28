package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.dto.ScoreDto;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.ScoreService;

import java.io.IOException;
import java.util.List;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ScoreDto> list = scoreService.getDtoScores();

        req.setAttribute("list", list);
        req.getRequestDispatcher("/jsp/score.jsp").forward(req, resp);
    }
}
