package ua.com.alevel.unit_16_servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    private Map<String, String> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new ConcurrentHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ip = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");
        users.put(ip, userAgent);

        out.println("<h3> Users who have visited this page: <h3>");

        for (Map.Entry<String, String> user: users.entrySet()) {
            if (user.getKey().equals(ip)) {
                out.println("<b> " + user.getKey() + " :: " + user.getValue() + " <b>");
            } else {
                out.println(user.getKey() + " :: " + user.getValue());
            }
        }

        out.close();
    }
}
