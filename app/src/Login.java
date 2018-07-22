
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {

	public Object userName;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("userpass");

		Object dataObject = getServletContext().getAttribute("data");
		Map<String, User> map = (Map<String, User>) dataObject;

		if (validate(username, password)) {
			if (map != null && map.containsKey(username) && map.get(username).getPassword().equals(password)
					&& map.get(username).isAdmin()) {
				System.out.println("from admin page");
				RequestDispatcher rd = request.getRequestDispatcher("admin1");
				rd.forward(request, response);

			} else if (map != null && map.containsKey(username) && map.get(username).getPassword().equals(password)
					&& map.get(username).isAdmin() == false) {
				System.out.println("hello" + username);
				RequestDispatcher re = request.getRequestDispatcher("WelcomeServlet");
				re.include(request, response);
			} else {
				out.print("No user data available");
			}

		} else {
			out.print("Sorry username or password error");
			System.out.println("user not found");
			RequestDispatcher rd = request.getRequestDispatcher("Index.html");
			rd.include(request, response);
		}
		out.close();
	}

	private boolean validate(String username, String password) {
		if (username.trim().length() == 0 || password.trim().length() == 0) {
			return false;
		}
		return true;
	}
}