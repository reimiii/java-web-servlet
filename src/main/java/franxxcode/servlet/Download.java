package franxxcode.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/download")
public class Download extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file = req.getParameter("file");
        Path path = Path.of("uploads/" + file);

        byte[] bytes = Files.readAllBytes(path);

        if ("true".equals(req.getParameter("force"))) {
            resp.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\"" + path.getFileName() + "\""
            );
        }

        resp.getOutputStream().write(bytes);
    }
}
