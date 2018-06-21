import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        HttpSession session = request.getSession();
        ArrayList<User> list = JDBC.getUsersList();

        if (list != null) {
            for (int i = 0; list.size() > i; i++) {
                User u = list.get(i);

                if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                   // System.out.println(u.getLevel());
                    if (u.getLevel().equals("admin")) {
                      //  RequestDispatcher rs = request.getRequestDispatcher("indexAdmin.jsp");
                      //  rs.forward(request, response);
                        session.setAttribute(Constants.userIDKey, u.getLevel());
                        out.println("Dane poprawne. Witaj !</br><h1>"+u.getEmail()+"</h1></br> Zaraz Cie przekierujemy");
                        response.setHeader("Refresh", "2; URL=http://localhost:8080/index.jsp");
                    } else {
                        session.setAttribute(Constants.userIDKey, u.getLevel());
                        out.println("Dane poprawne. Witaj !</br><h1>"+u.getEmail()+"</h1></br> Zaraz Cie przekierujemy");
                        response.setHeader("Refresh", "2; URL=http://localhost:8080/index.jsp");
                      //  RequestDispatcher rs = request.getRequestDispatcher("indexUser.jsp");
                      //  rs.forward(request, response);
                    }
                }
            }
        }

        if (validate(email, password)) {
            session = request.getSession();
            session.setAttribute(Constants.userIDKey, email);
            out.println("Dane poprawne. Witaj !</br><h1>"+email+"</h1></br> Zaraz Cie przekierujemy");
            response.setHeader("Refresh", "2; URL=http://localhost:8080/index.jsp");
        } else {
            out.println("Dane niepoprawne. Zaraz Cie przekierujemy");
            response.setHeader("Refresh", "2; URL=http://localhost:8080/index.jsp");
        }
    }


    private boolean validate(String email, String password) {
        if (email.equals("user") && password.equals("user")) return true;
        else if (email.equals("admin") && password.equals("admin")) return true;
        else return false;
    }


}

