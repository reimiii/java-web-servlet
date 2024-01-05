package franxxcode.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/form")
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String readString = Files.readString(
                Path.of(FormServlet.class.getResource("/html/form.html").getPath())
        );

        resp.getWriter().println(readString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var firstName = req.getParameter("first_name");
        var lastName = req.getParameter("last_name");
        var fullName = firstName + lastName;

        resp.getWriter().println("hello " + fullName);
    }
}
