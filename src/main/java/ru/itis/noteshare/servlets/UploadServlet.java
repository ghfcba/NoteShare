package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();
    private ComposerService composerService = new ComposerService();
    private GenreService genreService = new GenreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GenreModel> genreModelList = genreService.getAllGenres();
        req.setAttribute("genres",genreModelList);

        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String diff = req.getParameter("diff");
        String composerName = req.getParameter("composerName");

        ComposerModel composerModel = composerService.getComposerByName(composerName);

        Part part = req.getPart("scoreFile");

        String fileName = "хуй.pdf";

        ScoreModel score = new ScoreModel(title, fileName, diff );

        HttpSession session = req.getSession();
        UserModel userModel = (UserModel) session.getAttribute("user");

        scoreService.loadFile(part, fileName);
        scoreService.addScore(userModel, score, composerModel);

    }
}
