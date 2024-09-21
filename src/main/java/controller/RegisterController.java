package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import view.UserDAO;

/**
 *
 * @author hung6
 */
public class RegisterController extends HttpServlet {

    @WebServlet(name = "register", urlPatterns = "/register")
    public class register extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");
                String mobile = request.getParameter("mobile");
                String gender = request.getParameter("gender");
                String password = request.getParameter("password");
                String newPassword = DigestUtils.md5Hex(password);

                // Tạo hashcode giúp cho việc Activation Link
                String myHash;
                Random random = new Random();
                random.nextInt(999999);
                myHash = DigestUtils.md5Hex("" + random);

                //Tạo Data Bean
                registerBean rb = new registerBean();
                rb.setFullName(fullname);
                rb.setEmail(email);
                rb.setMobile(mobile);
                rb.setGender(gender);
                rb.setPassword(password);
                rb.setMyHash(newPassword);

                //Sau khi có register trong UserDAO
                UserDAO regDAO = new UserDAO();
                String str = regDAO.register(rb);
                if (str.equals("SUCCESS")) {
                    response.sendRedirect("verify.jsp");
                } else {
                    request.setAttribute("error", "Account can not activated");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } catch (Exception e) {
                System.out.println("RegisterServlet File!");
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

    }
}
