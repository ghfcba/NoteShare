package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.service.ComposerService;

import java.io.IOException;
import java.util.List;

@WebServlet("/composers")
public class ComposerServlet extends HttpServlet {
    private ComposerService composerService = new ComposerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ComposerModel> composers = composerService.getAllComposers();
        req.setAttribute("composers", composers);
        req.getRequestDispatcher("/jsp/composer.jsp").forward(req, resp);
    }
}
