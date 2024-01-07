package franxxcode.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String value = req.getParameter("value");

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");

        resp.addCookie(cookie);
        resp
                .getWriter()
                .println("Success add cookie: " + cookie.getName() + " - " + cookie.getValue());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {

                resp
                        .getWriter()
                        .println("cookie: " + cookie.getName() + " - " + cookie.getValue());
            }
        }
    }
}
