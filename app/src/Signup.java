
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Login firstservlet = new Login();

		String name = request.getParameter("name");
		String userName = request.getParameter("username");
		String password = request.getParameter("userpass");

		if (name.trim().length() == 0 || userName.trim().length() == 0 || password.trim().length() == 0) {
			System.out.print("please enter values");
			pw.print("Please enter values");
		} else {
			Object dataObject = getServletContext().getAttribute("data");
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setUserName(userName);
			if (dataObject == null || ((Map) dataObject).isEmpty()) {
				HashMap<String, User> map = new HashMap<String, User>();
				user.setAdmin(true);
				map.put(userName, user);
				getServletContext().setAttribute("data", map);
				pw.println("thanks for signup "+ userName + " you are admin");
				System.out.println("admin");
			} else {
				user.setAdmin(false);
				Map<String, User> map = (Map<String, User>) dataObject;
				User userAvailable = map.get(userName);
				if (userAvailable != null) {
					System.out.println("user already available");
					pw.print("Error user is already available");
					// user is already available
					return;
				}
				map.put(userName, user);
				getServletContext().setAttribute("data", map);
				System.out.println("local user");
				pw.println("Welcome "+ userName);
				// move user to welcome screen
			}
		}
	}
}
