//package ru.itis.noteshare.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import ru.itis.noteshare.models.UserModel;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class AuthFilter extends HttpFilter {
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String path = req.getServletPath();
//        HttpSession session = req.getSession();
//        UserModel user = (UserModel) session.getAttribute("user");
//
//        if(path.equals("/composers")||path.equals("/register")||path.equals("/login")||path.equals("/score")||path.equals("/main")){
//            chain.doFilter(req, res);
//            return;
//        }
//        if(user == null){
//            res.sendRedirect(req.getContextPath()+"/register");
//            return;
//        }
//
//        if(!(user.getRole().equals("ADMIN"))&& path.equals("/admin")){
//            res.sendRedirect(req.getContextPath()+"/main");
//            return;
//        }
//
//
//        chain.doFilter(req,res);
//        return;
//    }
//}
