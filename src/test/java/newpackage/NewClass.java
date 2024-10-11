package newpackage;


import model.BlogPost;
import model.User;
import view.BlogPostDAO;
import view.UserDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Minh
 */
public class NewClass {
    public static void main(String[] args) {
        BlogPostDAO blogPostDAO = new BlogPostDAO();
//        int id = Integer.parseInt(request.getParameter("bloglistid"));
        BlogPost blogPost = blogPostDAO.select(1);
        
        UserDAO userDao = new UserDAO();
        User author = userDao.select(1);
        
        System.out.println(blogPost);
        System.out.println(author);
    }
}
