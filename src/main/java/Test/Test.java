package Test;


import java.util.List;
import view.CourseDAO;
import view.PackageDAO;
import model.Package;

public class Test {
    public static void main(String[] args) {
        PackageDAO courseDAO = new PackageDAO();
        
        // Replace with a valid course ID to test
        int courseIdToTest = 1; // Change this to a valid course ID in your database
        
        List<Package> packages = courseDAO.getPackagesByCourseId(courseIdToTest);
        
        if (packages.isEmpty()) {
            System.out.println("No packages found for course ID: " + courseIdToTest);
        } else {
            System.out.println("Packages for course ID " + courseIdToTest + ":");
            for (Package pkg : packages) {
                System.out.println("ID: " + pkg.getId() +
                                   ", Course ID: " + pkg.getCourseId() +
                                   ", Package Name: " + pkg.getPackageName() +
                                   ", Price: " + pkg.getPrice() +
                                   ", Sale Price: " + pkg.getSalePrice() +
                                   ", Sale Start: " + pkg.getSaleStart() +
                                   ", Sale End: " + pkg.getSaleEnd());
            }
        }
    }
}


