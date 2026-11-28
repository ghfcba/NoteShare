package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.AuthService;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = (AuthService) config.getServletContext().getAttribute("authservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        try {
            String cleanEmail = Validator.requireEmail(email);
            String password = req.getParameter("password");

            if (password == null || password.isEmpty()) {
                throw new ValidationException("Пароль не может быть пустым");
            }

            if (authService.loginUser(cleanEmail, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", authService.getUserByEmail(cleanEmail));
                resp.sendRedirect(req.getContextPath() + "/main");
                return;
            }

            req.setAttribute("error", "Неверный email или пароль");
            req.setAttribute("email", email);
        } catch (ValidationException e) {
            req.setAttribute("email", email);
        }

        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
