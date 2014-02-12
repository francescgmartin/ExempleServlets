

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Newshow
 */
@WebServlet("/Newshow")
public class Newshow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newshow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date;
		String bands;
		String place;
		if( request.getParameter("date") !="" && request.getParameter("bands") !="" && request.getParameter("place") !="" )
		{
			 BufferedWriter writer = null;
			    try {
			        FileWriter fWriter = new FileWriter(request.getRealPath("/") + "WEB-INF/lib/agenda.txt", true);
			        writer = new BufferedWriter(fWriter);
			        writer.append(request.getParameter("date") + " " + request.getParameter("bands") + " @ " + request.getParameter("place"));
			        writer.newLine();
			        writer.flush();
			        writer.close();
			        response.sendRedirect("Pagloguejada");
			    } catch (Exception e) {
			    }
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2> INVALID FIELDS <h2>");
			out.println("<h3> PLEASE FILL ALL THE FIELDS IN THE FORM </h3>");
			out.println("<a href=\"Pagloguejada\"><h4> back </h4></a>");
			out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
