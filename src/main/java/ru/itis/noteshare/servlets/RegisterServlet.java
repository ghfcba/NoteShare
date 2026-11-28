package ru.itis.noteshare.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.service.AuthService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        authService = (AuthService) config.getServletContext().getAttribute("authservice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password");
        String password2 = req.getParameter("passwordConfirm");

        if(authService.checkPass(password1,password2)){
            authService.registerUser(new UserModel(username, email, password1));
        }

        resp.sendRedirect(req.getContextPath()+"/main");
    }
}
