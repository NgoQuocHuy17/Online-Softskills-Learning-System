package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Thumbnail;
import view.CourseDAO;
import view.ThumbnailDAO;
import view.UserDAO;

@WebServlet(name = "NewSubject", urlPatterns = {"/new-subject"})
@MultipartConfig
public class NewSubject extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch the form data
        String title = request.getParameter("name");
        String category = request.getParameter("category");
        String featuredParam = request.getParameter("featured");
        boolean sponsored = "true".equalsIgnoreCase(featuredParam); // "Yes" -> true
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String owner = request.getParameter("owner");

        // Retrieve user ID based on owner's full name
        UserDAO userDAO = new UserDAO();
        int ownerId = 0;
        try {
            ownerId = userDAO.getIDByUser(owner);
        } catch (SQLException ex) {
            Logger.getLogger(NewSubject.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create a new Course object
        Course newCourse = new Course(title, description, category, ownerId, sponsored, status);

        // Insert the new course into the database
        CourseDAO c = new CourseDAO();
        c.addNewCourse(newCourse);
        
        ThumbnailDAO thumbnailDAO = new ThumbnailDAO();
        int id = c.getIdByTitle(title);

        for (Part part : request.getParts()) {
            if (part.getName().equals("images") && part.getSize() > 0) {
                // Extract file input stream and name
                InputStream fileContent = part.getInputStream();
                String fileName = part.getSubmittedFileName();

                // Convert InputStream to byte array
                byte[] fileData = fileContent.readAllBytes();

                // Create a Thumbnail object
                Thumbnail thumbnail = new Thumbnail(id, fileName, fileData);

                // Insert thumbnail into the database
                thumbnailDAO.addThumbnail(thumbnail);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "NewSubject Servlet handles the creation of new courses.";
    }
}
