package ru.itis.noteshare.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.noteshare.models.UserModel;

import java.io.IOException;


@WebFilter("/admin")
public class AdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        UserModel user = session == null ? null : (UserModel) session.getAttribute("user");

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (user.getRole() == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            res.sendError(403, "Ухади, это не для таких как ты епта");
            return;
        }

        chain.doFilter(req, res);
    }
}
