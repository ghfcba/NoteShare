package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.itis.noteshare.enums.Difficulity;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.GenreService;
import ru.itis.noteshare.service.ScoreUploadService;
import ru.itis.noteshare.validation.ValidationException;

import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private ScoreUploadService scoreUploadService;
    private GenreService genreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        scoreUploadService = (ScoreUploadService) config.getServletContext().getAttribute("scoreuploadservice");
        genreService = (GenreService) config.getServletContext().getAttribute("genreservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", genreService.getAllGenres());
        req.setAttribute("difficulties", Difficulity.values());
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserModel userModel = (UserModel) session.getAttribute("user");

        scoreUploadService.upload(
                userModel,
                req.getParameter("title"),
                req.getParameter("diff"),
                req.getParameter("composerName"),
                req.getParameter("genre"),
                req.getPart("scoreFile")
        );

        resp.sendRedirect(req.getContextPath() + "/score");
    }
}
