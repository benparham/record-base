

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

    // private final static String REQ_TYPE = "type";
    // private final static String REQ_TYPE_REGISTER = "register";

    // private final static int READ_SIZE_BYTES = 1024;
    // private final static String tempPath = "/Users/pairofham/Desktop/RecordBaseFiles";

    // private PrintWriter writer;
    // private void printLine(String line) {
    //     writer.println(line + "<br/>");
    // }

    private RequestHandler handler;

    public void init() throws ServletException {
        
    }

    // protected void processGetRequest(HttpServletRequest request, HttpServletResponse response) 
    // throws ServletException, IOException {

    //     String requestType = request.getParameter(REQ_TYPE);
    //     assert(requestType != null);

    //     if (requestType.equals(REQ_TYPE_REGISTER)) {
    //         printLine("Got register request ");
    //     } else {
    //         assert(false);
    //     }
    // }

    // protected void processPostRequest(HttpServletRequest request, HttpServletResponse response) 
    // throws ServletException, IOException {


    //     String contentType = request.getHeader("Content-Type");
    //     assert(contentType != null);

    //     printLine("Content-Type is " + contentType);


    //     if (contentType.equals("application/x-www-form-urlencoded")) {
    //         processUrlencodedRequest(request, response);
    //     } else if (contentType.startsWith("multipart/form-data")) {
    //         processMultipartRequest(request, response);
    //     } else {
    //         printLine("Unknown Content-Type");
    //         return;
    //     }
    // }

    // protected void processUrlencodedRequest(HttpServletRequest request, HttpServletResponse response) 
    // throws ServletException, IOException {

    //     printLine("got url encoded request");

    // }

    // protected void processMultipartRequest(HttpServletRequest request, HttpServletResponse response) 
    // throws ServletException, IOException {

    //     printLine("got multipart request");

    //     final Part filePart = request.getPart("file");
    //     final String fileName = getFileName(filePart);

    //     if (fileName == null) {
    //         printLine("File not supplied in request");
    //     } else {
    //         printLine("Recieved file: " + fileName);
    //     }

    //     // OutputStream out = null;
    //     // InputStream fileContent = null;

    //     // try {
    //     //     out = new FileOutputStream(new File(tempPath + File.separator + fileName));
    //     //     fileContent = filePart.getInputStream();

    //     //     int read = 0;
    //     //     final byte[] bytes = new byte[READ_SIZE_BYTES];
           
    //     //     while ((read = fileContent.read(bytes)) != -1) {
    //     //         out.write(bytes, 0, read);
    //     //     }

    //     //     writer.println("<br/> Created new file " + fileName + " at location" + tempPath + "<br/>");
    //     // } catch (FileNotFoundException e) {
    //     //     writer.println("<br/>Failed to create file " + fileName + " at location" + tempPath + "<br/>");
    //     //     writer.println("<br/>ERROR: " + e.getMessage() + "<br/>");
    //     // } finally {
    //     //     if (out != null) { out.close(); }
    //     //     if (fileContent != null) { fileContent.close(); }
    //     // }
    // }

    // private String getFileName(final Part filePart) {
    //     for (String content : filePart.getHeader("content-disposition").split(";")) {
    //         if (content.trim().startsWith("filename")) {
    //             return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
    //         }
    //     }

    //     return null;
    // }

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException {

        // response.setContentType("text/html;charset=UTF-8");

        // writer = response.getWriter();

        // writer.println("<html>");
        // writer.println("<body>");

        // writer.println("<h1>GET Request Recieved</h1>");


        // processGetRequest(request, response);


        // writer.println("</body>");
        // writer.println("</html>");

        // writer.close();

        handler = new RequestHandler(RequestHandler.RequestMethod.GET, request, response);
        handler.handleRequest();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        // response.setContentType("text/html;charset=UTF-8");
        
        // writer = response.getWriter();
        
        // writer.println("<html>");
        // writer.println("<body>");

        // writer.println("<h1>POST Request Recieved</h1>");


        // processPostRequest(request, response);


        // writer.println("</body>");
        // writer.println("</html>");

        // writer.close();

        handler = new RequestHandler(RequestHandler.RequestMethod.POST, request, response);
        handler.handleRequest();
    }
}