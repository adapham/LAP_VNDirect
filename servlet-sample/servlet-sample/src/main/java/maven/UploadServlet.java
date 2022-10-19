package maven;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
@WebServlet(value = "/upload", name = "upload-servlet")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {

            for(Part part : request.getParts()){
                Collection<String>headers = part.getHeaderNames();
                headers.forEach( header ->{

                        response.getWriter().println(header + " : "+part.getHeader(header));

                });
            }
    }
     response.getWriter().print("Upload has been done successfully!");
}
