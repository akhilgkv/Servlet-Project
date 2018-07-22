

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Save
 */
@WebServlet("/Save")
public class Save extends HttpServlet {
       
	private String on;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String name = request.getParameter("Name");
		String password = request.getParameter("Password");
		String admin = request.getParameter("admin");
		

		Object dataObject = getServletContext().getAttribute("data");
		Map<String, User> map = (Map<String, User>) dataObject;
		User user = new User();
		
		if (name.trim().length() == 0 || userName.trim().length() == 0 || password.trim().length() == 0) {
			System.out.print("please enter values");
			out.print("Please enter particular values");
		}
		if(admin != null) {
		System.out.println(admin);
		user.setName(name);
		user.setPassword(password);
		user.setUserName(userName);
		user.setAdmin(true);
		map.put(userName, user);	
		out.println("Name has been changed to " + name);
		out.print("Password has been changed to " + password);
		out.print("User have admin privilege");
		}
		else {
			System.out.println(admin);
			user.setName(name);
			user.setPassword(password);
			user.setUserName(userName);
			map.put(userName, user);	
			out.println("name has been changed to " + name );
			out.print("password has been changed to " + password);
		}
	}
}
