
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n = request.getParameter("username");
		out.print("Welcome admin " + n + " to database");

		Object dataObject = getServletContext().getAttribute("data");
		if (dataObject != null) {
			Map<String, User> map = (Map<String, User>) dataObject;
			// Set<String> mapKeys = map.keySet();
			out.print("<table width= 10 border=1 height=auto border-spacing=0px ");
			out.print("<tr>");
			out.print("<td>Username</td>");
			out.print("<td>Name</td>");
			out.print("<td>Password</td>");
			out.print("</tr>");
			for (Map.Entry<String, User> entry : map.entrySet()) {
				if (!entry.getValue().isAdmin()) {
					System.out.println(entry.getKey() + " = " + entry.getValue());
					out.print("<tr>");
					out.print("<td><a href=EditData?v="+entry.getKey()+">" + entry.getKey() + "</a></td>");
					out.print("<td>" + entry.getValue().getName() + "</td>");
					out.print("<td>******</td>");
					out.print("</tr>");
				}
			}
			out.print("</table>");
		}
		out.close();
	}
}