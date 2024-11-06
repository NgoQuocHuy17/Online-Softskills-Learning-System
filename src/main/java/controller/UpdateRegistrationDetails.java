package controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import model.Course;
import model.Registration;
import model.RegistrationMedia;
import model.User;
import model.UserContact;
import model.Package;
import org.apache.commons.codec.digest.DigestUtils;
import view.CourseDAO;
import view.PackageDAO;
import view.RegistrationDAO;
import view.RegistrationMediaDAO;
import view.UserContactDAO;
import view.UserDAO;
import view.UserCourseDAO;

@WebServlet(name = "UpdateRegistrationDetails", urlPatterns = {"/UpdateRegistrationDetails"})
@MultipartConfig
public class UpdateRegistrationDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationDAO registrationDAO = new RegistrationDAO();
        RegistrationMediaDAO registrationMediaDAO = new RegistrationMediaDAO();
        UserDAO userDAO = new UserDAO();
        UserCourseDAO userCourseDAO = new UserCourseDAO();

        int packageId = Integer.parseInt(request.getParameter("packageId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));

        String status = request.getParameter("status");
        String userIdStr = request.getParameter("userId");
        Registration registration = registrationDAO.getRegistrationById(registrationId);

        Integer userId = 0;
        if (userIdStr != null && !userIdStr.isEmpty()) {
            userId = Integer.valueOf(userIdStr);
        }

        if (userId != 0 && "Paid".equals(status) && !"Paid".equals(registration.getStatus())) {
            if (userCourseDAO.addUserCourse(userId, courseId)) {
                request.setAttribute("message", "Thanh toán Course cho user thành công.");
            } else {
                request.setAttribute("message", "Thêm Course cho user thất bại.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
        }

        if (userId == 0 && "Paid".equals(status)) {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");

            if (userDAO.checkEmailExist(email)) {
                userId = userDAO.getUserIdByEmail(email);
            } else {
                String password = generateRandomPassword(10); // Sử dụng hàm tạo mật khẩu ngẫu nhiên
                String hash = createHash(); // Sử dụng hàm tạo hash từ mật khẩu

                boolean isRegistered = userDAO.registerUser(fullName, gender, email, password, hash);
                if (isRegistered) {
                    userId = userDAO.getUserIdByEmail(email);
                    if (userCourseDAO.addUserCourse(userId, courseId)) {
                        SendingEmail se = new SendingEmail();
                        String subject = "Kích hoạt tài khoản của bạn";
                        String body = "Một khóa học mới đã được thanh toán cho email đăng nhập của bạn.\n"
                                + "Mật khẩu đăng nhập của bạn là: " + password + "\n"
                                + "Để kích hoạt tài khoản, hãy bấm vào link bên dưới:\n"
                                + "http://localhost:9999/project/ActivateAccount?key1=" + email + "&key2=" + hash;
                        se.sendEmail(email, subject, body); // Gửi email thông báo tài khoản mới
                    } else {
                        request.setAttribute("message", "Thêm Course cho user thất bại.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                        return;
                    }

                } else {
                    request.setAttribute("message", "Đăng ký người dùng mới thất bại.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
            }
        }

        // Kiểm tra xem có tệp hình ảnh hoặc video được gửi đi không
        Part imagePart = request.getPart("newImage");
        Part videoPart = request.getPart("newVideo");

        if ((imagePart != null && imagePart.getSize() > 0) || (videoPart != null && videoPart.getSize() > 0)) {
            // Xử lý cập nhật hình ảnh
            if (imagePart != null && imagePart.getSize() > 0) {
                String imageType = imagePart.getContentType();
                byte[] imageData = new byte[(int) imagePart.getSize()];
                String imageNote = request.getParameter("imageNote");

                // Đọc dữ liệu từ file upload
                try (InputStream inputStream = imagePart.getInputStream()) {
                    inputStream.read(imageData);
                }

                // Ghi dữ liệu vào database
                registrationMediaDAO.insertMedia(registrationId, imageType, imageData, imageNote);
                request.setAttribute("message", "Cập nhật hình ảnh thành công.");
            }

            // Xử lý cập nhật video
            if (videoPart != null && videoPart.getSize() > 0) {
                String videoType = videoPart.getContentType();
                byte[] videoData = new byte[(int) videoPart.getSize()];
                String videoNote = request.getParameter("videoNote");

                // Đọc dữ liệu từ file upload
                try (InputStream inputStream = videoPart.getInputStream()) {
                    inputStream.read(videoData);
                }

                // Ghi dữ liệu vào database
                registrationMediaDAO.insertMedia(registrationId, videoType, videoData, videoNote);
                request.setAttribute("message", "Cập nhật video thành công.");
            }
        } else {
            // Không có tệp hình ảnh hoặc video, chỉ cập nhật thông tin đăng ký

            String notes = request.getParameter("notes");

            // Kiểm tra và chuyển đổi các giá trị ngày tháng
            String validFromStr = request.getParameter("validFrom");
            String validToStr = request.getParameter("validTo");
            Date validFrom = null;
            Date validTo = null;

            if (validFromStr != null && !validFromStr.isEmpty()) {
                try {
                    validFrom = Date.valueOf(validFromStr);
                } catch (IllegalArgumentException e) {
                    request.setAttribute("message", "Invalid date format for validFrom.");
                }
            }

            if (validToStr != null && !validToStr.isEmpty()) {
                try {
                    validTo = Date.valueOf(validToStr);
                } catch (IllegalArgumentException e) {
                    request.setAttribute("message", "Invalid date format for validTo.");
                }
            }

            // Cập nhật thông tin đăng ký
            boolean isUpdated = registrationDAO.updateRegistrationDetails(registrationId, userId, packageId, courseId, status, validFrom, validTo, notes);
            if (isUpdated) {
                request.setAttribute("message", "Cập nhật thông tin đăng ký thành công.");
            } else {
                request.setAttribute("message", "Cập nhật thông tin đăng ký thất bại.");
            }
        }

        User user = userDAO.getUserById(userId);
        UserContactDAO userContactDAO = new UserContactDAO();
        CourseDAO courseDAO = new CourseDAO();
        PackageDAO packageDAO = new PackageDAO();

        List<UserContact> phones = userContactDAO.getUserPhones(userId);
        List<UserContact> emails = userContactDAO.getUserEmails(userId);
        List<Course> courses = courseDAO.getAllCourses();
        Course course = courseDAO.getCourseById(courseId);
        Package pkg = packageDAO.getPackageById(packageId);
        List<Package> packages = packageDAO.getPackagesByCourseId(courseId);
        List<RegistrationMedia> images = registrationMediaDAO.getImagesByRegistrationId(registrationId);
        List<RegistrationMedia> videos = registrationMediaDAO.getVideosByRegistrationId(registrationId);

        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validFromStr = registration.getValidFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
        String validToStr = registration.getValidTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);

        // Gửi danh sách video, hình ảnh, số điện thoại và email đến JSP
        request.setAttribute("registration", registration);
        request.setAttribute("user", user);
        request.setAttribute("emails", emails);
        request.setAttribute("phones", phones);
        request.setAttribute("course", course);
        request.setAttribute("courses", courses);
        request.setAttribute("pkg", pkg);
        request.setAttribute("packages", packages);
        request.setAttribute("validFrom", validFromStr);
        request.setAttribute("validTo", validToStr);
        request.setAttribute("images", images);
        request.setAttribute("videos", videos);

        request.getRequestDispatcher("/registration-details.jsp").forward(request, response);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private String createHash() {
        String myHash;
        Random random = new Random();
        random.nextInt(999999);
        myHash = DigestUtils.md5Hex("" + random);
        return myHash;
    }

}
