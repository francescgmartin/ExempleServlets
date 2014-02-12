import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Pagloguejada" ,urlPatterns = {"/Pagloguejada"})
public class Pagloguejada extends HttpServlet
{
	
	public final String root_name = "root";
	public final String root_pass = "patata";
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String path = request.getRealPath("/");
		if( request.getParameter("name") !=null && request.getParameter("psw") !=null)
		{
			String nom =	request.getParameter("name");
			String pass = request.getParameter("psw");
			
			if (nom.compareTo(root_name)==0 && root_pass.compareTo(pass)==0)	
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("name", nom);
				session.setAttribute("login", true);
				this.printMemberPage((String)session.getAttribute("name"), response, path);
			}
			else
			{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1> LOGIN FAIL <h1>");
			out.println("<h2> YOU CANNOT PASS </h2>");
			out.close();
			}
		}
		else
		{
			HttpSession session = request.getSession();
			if (session.getAttribute("login")!=null)
			{
				boolean login = (Boolean) session.getAttribute("login");
				if (login)
				{
					this.printMemberPage((String)session.getAttribute("name"), response, path);				
				}
			}
			else
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<h1> THIS PAGE IS ONLY FOR MEMBERS <h1>");
				out.println("<a href=\"login.html\"><h2> LOGIN </h2></a>");
				out.close();
			}
		}

	}
	public void printMemberPage(String user, HttpServletResponse response,String path) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<p>WELCOME " + user + "</p>");
		out.println("<h1 style=\"background-color:LightSeaGreen;\"> THE GRINDCORE SECRET PAGE </h1>");
		out.println("<h3 style=\"background-color:SkyBlue;\" >MEMBER ZONE</h3>");
		out.println("<h2 style=\"background-color:SkyBlue;\" >UPCOMING SHOWS</h2>");
		out.println(getAgenda(path));
		out.println("<form method=\"get\" action=\"Newshow\">");
		out.println("<table>");
		out.println("<tr><td>DATE</td> <td><input type=\"text\" name=\"date\"/></td>");
		out.println("<td>BANDS</td><td><input type=\"text\" name=\"bands\"></td>");
		out.println("<td>PLACE</td><td><input type=\"text\" name=\"place\"></td>");
		out.println("</tr><tr><td><input type=\"submit\" value=\"ADD SHOW\"/></td></tr>");
		out.println("</table>");		
		out.println("</form>");
		out.println("<a href=\"login.html\">Back to Homepage</a>");
		out.println("<form method=\"get\" action=\"logout\">");
		out.println("<input type=\"submit\" value=\"logout\"/>");
		out.println("</form");
		out.close();
	}
	public String getAgenda(String path) throws IOException {
		
	    BufferedReader br = new BufferedReader(new FileReader(path+"WEB-INF/lib/agenda.txt"));
	    String everything="";
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append("<p style=\"background-color:DodgerBlue ;\" >" + line + "</p>");
	            sb.append("\n");
	            line = br.readLine();
	        }
	        everything = sb.toString();
	    } finally {
	        br.close();
	    }
		return everything;
	}
}