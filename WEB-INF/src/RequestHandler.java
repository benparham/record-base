

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler {

	public enum RequestMethod {
		GET,
		POST
	}

	private RequestMethod method;
	private PrintWriter writer;

    private void printLine(String line) {
        writer.println(line + "<br/>");
    }

    public RequestHandler(RequestMethod method, HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    	this.method = method;
    	this.writer = response.getWriter();

    	response.setContentType("text/html;charset=UTF-8");
    }

	public void handleRequest() throws ServletException {

        writer.println("<html>");
        writer.println("<body>");

		switch (method) {
			case GET:
				printLine("Recieved GET request");
				break;
			case POST:
				printLine("Recieved POST request");
				break;
			default:
				printLine("Unsupported request type. Require GET or POST");
				break;
		}

		writer.println("</body>");
        writer.println("</html>");

        writer.close();
	}
}