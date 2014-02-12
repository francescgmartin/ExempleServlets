

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Public
 */
@WebServlet("/Public")
public class Public extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Public() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String user;
		if( session.getAttribute("login") !=null)
		{
			user=(String)session.getAttribute("name");
		}
		else
		{
			user="guest";
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<p>WELCOME " + user + "</p>");
		out.println("<h1 style=\"background-color:LightSeaGreen;\"> THE GRINDCORE SECRET PAGE </h1>");
		out.println("<h3 style=\"background-color:SkyBlue;\" >MEMBER ZONE</h3>");
		out.println("<p style=\"background-color:SkyBlue;\"> TxGxSxP is a private collective dedicated to spread the noisy plague called grindcore <br> <br> Email us to grindinfection@gmail.com to get a user and start publishing gigs <br> </p>");
		out.println("<a href=\"login.html\">Back to Homepage</a>");
		out.println("<form method=\"get\" action=\"logout\">");
		out.println("<input type=\"submit\" value=\"logout\"/>");
		out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
