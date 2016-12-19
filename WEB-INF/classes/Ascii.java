import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Ascii")
public class Ascii extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	int nbCol = Integer.parseInt(req.getParameter("nb"));
	out.println("<head><title>Ascii</title></head><body><center>");
	out.println("<table border=\"1\">");
	out.println("<tr>");
	for(int i=32; i<=255; i++){
	    if((i-32)%nbCol==0)
		out.println("</tr><tr>");
	    out.println("<td>"+i+"</td><td>"+(char)i+"</td>");
	}
	out.println("</tr>");
	out.println("</table></center></body>");
    }
}
	
					
