package maven;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
@WebServlet(value = "/count", name = "count-servlet")


public class HitCounter extends HttpServlet {
    private int hitCount;
    public void init() { hitCount = 0;}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        hitCount++;

        resp.setContentType("text/html");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<br>Hit count = " + hitCount);
        resp.getWriter().println("</html>");
    }
}
