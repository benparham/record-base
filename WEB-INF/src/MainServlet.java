import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class MainServlet extends HttpServlet {

    private final static int READ_SIZE_BYTES = 1024;
    private final static String tempPath = "/Users/pairofham/Desktop/RecordBaseFiles";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<body>");


        writer.println("<h1>Upload Request Recieved</h1>");

        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        if (fileName == null) {
            writer.println("<h2>File not supplied in request</h2>");
        } else {
            writer.println("<h2>Recieved file: " + fileName + "</h2>");
        }


        writer.println("</body>");
        writer.println("</html>");


        // OutputStream out = null;
        // InputStream fileContent = null;

        // try {
        //     out = new FileOutputStream(new File(tempPath + File.separator + fileName));
        //     fileContent = filePart.getInputStream();

        //     int read = 0;
        //     final byte[] bytes = new byte[READ_SIZE_BYTES];
           
        //     while ((read = fileContent.read(bytes)) != -1) {
        //         out.write(bytes, 0, read);
        //     }

        //     writer.println("<br/> Created new file " + fileName + " at location" + tempPath + "<br/>");
        // } catch (FileNotFoundException e) {
        //     writer.println("<br/>Failed to create file " + fileName + " at location" + tempPath + "<br/>");
        //     writer.println("<br/>ERROR: " + e.getMessage() + "<br/>");
        // } finally {
        //     if (out != null) { out.close(); }
        //     if (fileContent != null) { fileContent.close(); }
        //     if (writer != null) { writer.close(); }
        // }

        writer.close();
    }

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException {

    	PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<body>");

        writer.println("<h1>Get Request Recieved</h1>");

        writer.println("</body>");
        writer.println("</html>");

        writer.close();

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        processRequest(request, response);

    }

    private String getFileName(final Part filePart) {
        for (String content : filePart.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}