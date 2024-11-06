package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import model.Slider;
import view.SliderDAO;

public class Test {

    // Convert file to Blob
    public static Blob fileToBlob(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileContent);
        }
        try {
            return new SerialBlob(fileContent);
        } catch (Exception e) {
            throw new IOException("Error converting file to Blob", e);
        }
    }

    // Convert Blob to Base64 string to print
    public static String blobToBase64(Blob blob) throws IOException {
        byte[] blobBytes = null;
        try {
            blobBytes = blob.getBytes(1, (int) blob.length());
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Base64.getEncoder().encodeToString(blobBytes);
    }

//    public static void main(String[] args) {
//        // Replace with your actual relative image path
//        String imagePath = "src/main/webapp/assets/img/blog/blog-10.jpg";
//
//        try {
//            // Convert file to Blob
//            Blob blob = fileToBlob(imagePath);
//
//            SliderDAO sliderDAO = new SliderDAO();
//            Slider slider = sliderDAO.select(10);
//            System.out.println(slider);
//            slider.setImageUrl(blob);
//            sliderDAO.update(slider);
//            System.out.println(slider);
//            
////            // Convert Blob to Base64 string and print
////            String base64Data = blobToBase64(blob);
////            System.out.println("Base64 Blob Content: ");
////            System.out.println(base64Data); // This is your Blob content in Base64
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
