package franxxcode.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class FormUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter()
                .println(
                        Files.readString(
                                Path.of(FormUpload.class
                                        .getResource("/html/form-upload.html").getPath())
                        )
                );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part profile = req.getPart("profile");

        Path uploadFile = Path.of(
                "uploads/" + UUID.randomUUID() + profile.getSubmittedFileName()
        );

        Files.copy(profile.getInputStream(), uploadFile);

        resp.getWriter()
                .println("hello: " + name + ", your images save in: " + uploadFile.toAbsolutePath());
    }
}
