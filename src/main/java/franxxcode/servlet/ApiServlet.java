package franxxcode.servlet;

import franxxcode.servlet.model.SayHelloRequest;
import franxxcode.servlet.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/api/hello")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SayHelloRequest request = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHelloRequest.class);

        StringBuilder builder = new StringBuilder();
        builder.append("Hello: ")
                .append(request.getUsername())
                .append(", comment is: ")
                .append(request.getComment());

        Map<String, StringBuilder> data = Map.of(
                "data", builder
        );

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(
                JsonUtil.getObjectMapper().writeValueAsString(data)
        );
    }
}
