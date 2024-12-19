package tests;

import java.math.BigDecimal;
import java.time.Duration;
import model.Course;
import model.CourseContent;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import pages.SubjectDetailPage;

import static org.testng.Assert.assertTrue;
import view.CourseDAO;
import view.CourseContentDAO;

import utils.BaseTest;

public class SubjectDetailPageTest extends BaseTest {

    private final CourseDAO courseDAO = new CourseDAO();
    private final CourseContentDAO courseContentDAO = new CourseContentDAO();

    @Test
    public void verifyPageLoadsCorrectly() {
        login("nguyenvana@example.com", "password123", 1);

        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(driver);

        // Verify all fields are present and displayed on the page
        Assert.assertTrue(driver.findElement(subjectDetailPage.titleField).isDisplayed(), "Title field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.descriptionField).isDisplayed(), "Description field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.categoryField).isDisplayed(), "Category field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.basicPackagePriceField).isDisplayed(), "Basic Package Price field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.advancedPackagePriceField).isDisplayed(), "Advanced Package Price field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.statusDropdown).isDisplayed(), "Status dropdown is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.sponsoredLabel).isDisplayed(), "Sponsored label is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.contentField).isDisplayed(), "Content field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.saveButton).isDisplayed(), "Save button is not displayed");
    }

    @Test
    public void testAccessAsGuess() {
        driver.get("http://localhost:8080/project/logout.jsp");
        driver.get("http://localhost:8080/project/subjectDetail?action=edit&courseId=1");
        // Kiểm tra nếu là Guess, họ sẽ được chuyển hướng về trang home
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("home"), "Guess should be redirected to home page.");
    }

    @Test
    public void testAccessAsStudent() {
        login("tranthib@example.com", "password456", 1);

        // Kiểm tra nếu là Student, họ sẽ được chuyển hướng về trang home
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("home"), "Student should be redirected to home page.");
    }

    @Test
    public void testAccessAsTeacherHasPermission() {
        login("phamind@example.com", "password321", 1);

        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(driver);

        // Verify all fields are present and displayed on the page
        Assert.assertTrue(driver.findElement(subjectDetailPage.titleField).isDisplayed(), "Title field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.descriptionField).isDisplayed(), "Description field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.categoryField).isDisplayed(), "Category field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.basicPackagePriceField).isDisplayed(), "Basic Package Price field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.advancedPackagePriceField).isDisplayed(), "Advanced Package Price field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.statusDropdown).isDisplayed(), "Status dropdown is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.sponsoredLabel).isDisplayed(), "Sponsored label is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.contentField).isDisplayed(), "Content field is not displayed");
        Assert.assertTrue(driver.findElement(subjectDetailPage.saveButton).isDisplayed(), "Save button is not displayed");
    }

    @Test
    public void testAccessAsTeacherDoesntHasPermission() {
        login("vothie@example.com", "password654", 1);

        // Kiểm tra nếu không có quyền chỉnh sửa, họ sẽ được chuyển hướng về trang SubjectList
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("SubjectList"), "Teacher soesn't has permission should be redirected to SubjectList page.");
    }

    @Test
    public void testEditSubjectDetails() {
        // Đăng nhập vào hệ thống với quyền admin
        login("nguyenvana@example.com", "password123", 1);

        // Khởi tạo trang SubjectDetailPage
        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(driver);

        // Dữ liệu mẫu để cập nhật
        String expectedTitle = "Updated Title";
        String expectedDescription = "Updated Description";
        String expectedCategory = "New Category";
        BigDecimal expectedBasicPackagePrice = new BigDecimal("100");
        BigDecimal expectedAdvancedPackagePrice = new BigDecimal("200");
        String expectedStatus = "Published";
        String expectedContent = "Updated content for testing.";

        // Nhập dữ liệu mẫu vào form
        subjectDetailPage.enterTitle(expectedTitle);
        subjectDetailPage.enterDescription(expectedDescription);
        subjectDetailPage.enterCategory(expectedCategory);
        subjectDetailPage.enterBasicPackagePrice(expectedBasicPackagePrice.toString());
        subjectDetailPage.enterAdvancedPackagePrice(expectedAdvancedPackagePrice.toString());
        subjectDetailPage.selectStatus(expectedStatus);
        subjectDetailPage.enterContent(expectedContent);

        // Click nút lưu
        WebElement saveButton = driver.findElement(subjectDetailPage.saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(saveButton));

        try {
            saveButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        }

        // Đợi vài giây để thao tác lưu hoàn tất
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lấy dữ liệu từ database để kiểm tra
        int courseId = 1;  // ID của khóa học bạn đang chỉnh sửa
        Course course = courseDAO.getCourseById(courseId);
        CourseContent courseContent = courseContentDAO.getCourseContentById(courseId);

        // Kiểm tra dữ liệu đã được cập nhật đúng hay chưa
        assertEquals(course.getTitle(), expectedTitle, "Title should be updated in the database.");
        assertEquals(course.getDescription(), expectedDescription, "Description should be updated in the database.");
        assertEquals(course.getCategory(), expectedCategory, "Category should be updated in the database.");
        assertTrue(course.getBasicPackagePrice().compareTo(expectedBasicPackagePrice) == 0,
                "Basic package price should be updated in the database.");
        assertTrue(course.getAdvancedPackagePrice().compareTo(expectedAdvancedPackagePrice) == 0,
                "Advanced package price should be updated in the database.");
        assertEquals(course.getStatus(), expectedStatus, "Status should be updated in the database.");
        assertEquals(courseContent.getContent(), expectedContent, "Content should be updated in the database.");
    }

    @Test
    public void testAddMediaButtonFunctionality() {
        login("nguyenvana@example.com", "password123", 1);
        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(driver);

        // Scroll and wait for the Add Media button to be clickable
        WebElement addMediaButton = driver.findElement(subjectDetailPage.addMediaButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addMediaButton);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(addMediaButton));

        // Click the Add Media button using JavaScript if normal click fails
        try {
            addMediaButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addMediaButton);
        }

        // Verify redirection to the add media page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("addMedia"), "The Add Media button did not redirect to the expected page.");
    }

    @Test
    public void testGoToLessonListButtonFunctionality() {
        login("nguyenvana@example.com", "password123", 1);
        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(driver);

        // Click on the Go To Lesson List button
        subjectDetailPage.clickGoToLessonListButton();

        // Verify redirection to the lesson list page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("LessonListController"), "The Go To Lesson List button did not redirect to the expected page.");
    }

}
