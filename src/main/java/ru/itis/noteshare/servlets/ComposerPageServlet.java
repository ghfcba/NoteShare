package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.service.ComposerService;

import java.io.IOException;
@WebServlet("/composer/page")
public class ComposerPageServlet extends HttpServlet {
    ComposerService composerService = new ComposerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long compId = Long.valueOf(req.getParameter("id"));
        ComposerModel composerModel = composerService.getComposerById(compId);
        req.setAttribute("composer", composerModel);
        req.getRequestDispatcher("/jsp/composer-details.jsp").forward(req,resp);
    }
}
