import com.sun.jdi.connect.spi.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonLoaderClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/clazz/xml");
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Accept","Application/xml");
            InputStream stream = connection.getInputStream();
            int read =0;
            byte[] bytes = new byte[4*1024];
            while (((read = stream.read(bytes))!=-1)){
                System.out.println(new String(bytes,0,read));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
