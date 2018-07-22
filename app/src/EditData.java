
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditData
 */
@WebServlet("/EditData")
public class EditData extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Object dataObject = getServletContext().getAttribute("data");
		Map<String, User> map = (Map<String, User>) dataObject;
		String username = request.getParameter("v").toString();
		User user = map.get(username);
		out.print("<html>");
		out.print("<head>");
		out.print("<title> Admin Page </title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<form name=data action=Save onsubmit=return validateForm() method=post>");
		out.print("Name: <input type=text name=Name value="+user.getName() + " /><br /> <br />");
		out.print("Password: <input type=text name=Password value="+ user.getPassword() + " /><br /> <br />");
		out.print("Admin access: <input type=checkbox name=admin>Admin Permission");
		out.print("<input type=hidden name=username value="+ user.getUserName() + " /><br /> <br />");		
		out.print("<input type=submit value= 'Update Values' />");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");
	}

}
