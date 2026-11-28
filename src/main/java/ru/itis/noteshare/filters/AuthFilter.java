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
import java.util.Set;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/",
            "/main",
            "/score",
            "/score/page",
            "/composers",
            "/composer/page",
            "/login",
            "/register",
            "/logout",
            "/download"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String path = req.getServletPath();

        if (path.startsWith("/assets/") || PUBLIC_PATHS.contains(path)) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = req.getSession(false);
        UserModel user = session == null ? null : (UserModel) session.getAttribute("user");

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        chain.doFilter(req, res);
    }
}
