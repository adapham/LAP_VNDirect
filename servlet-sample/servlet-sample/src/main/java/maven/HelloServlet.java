package maven;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/hello", name = "hello-servlet")

public class HelloServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException{
//        resp.getWriter().println("Hello HaNoi Java Clazz");
//    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException{
        resp.getWriter().println("Hello"+ req.getParameter("user") );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        response.getWriter().println("Hello "+ request.getParameter("user"));
    }
}
