package maven;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(value = "/redirect", name = "redirect-servlet")


public class PageRedirect extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        resp.getWriter().println("Hello"+ req.getParameter("user") );
        resp.setContentType("text/html");
        String site = new String("http://www.vnexpress.net");
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.setHeader("Location", site);
    }
}
