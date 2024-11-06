package AutomationTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTestUserList {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        // Thiết lập đường dẫn đến EdgeDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\hung6\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("http://localhost:9999/project/UserListTest");
    }

    @Test
    public void testSearchNoResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement searchInput = driver.findElement(By.name("searchTerm"));
        searchInput.sendKeys("John Doe");
        searchInput.submit();

        // Đợi trang tải lại và kiểm tra không có kết quả trả về
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-warning")));
        WebElement noResultsAlert = driver.findElement(By.cssSelector(".alert.alert-warning"));

        // Xác thực thông báo "Không có người dùng hợp lệ"
        assertTrue(noResultsAlert.isDisplayed(), "Expected 'No valid users' alert to be displayed.");
    }

    @Test
    public void testPagination() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement nextPageLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='page-link' and contains(@href, 'page=2')]")));

        // Cuộn phần tử vào khung nhìn
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextPageLink);

        // Thêm delay trước khi click
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click phần tử
        nextPageLink.click();

        // Xác thực đã chuyển đến trang 2
        WebElement currentPage = driver.findElement(By.cssSelector(".pagination .active a"));
        assertEquals("2", currentPage.getText());
    }

    @Test
    public void testSearchFilterAndSort() {
        // Tìm kiếm
        WebElement searchInput = driver.findElement(By.name("searchTerm"));
        searchInput.sendKeys("Nguyen");
        searchInput.submit();
        // Filter
        driver.findElement(By.id("genderMale")).click();
        driver.findElement(By.id("roleAdmin")).click();
        driver.findElement(By.id("statusValid")).click();
        driver.findElement(By.cssSelector(".filter-form .btn-primary")).click();
        // Sort
        driver.findElement(By.xpath("//th[contains(text(), 'User ID')]//a[1]")).click(); // Sắp xếp tăng dần theo User ID
        // Xác thực kết quả
        List<WebElement> userRows = driver.findElements(By.xpath("//table/tbody/tr"));
        List<Integer> listIds = new ArrayList<>();
        for (WebElement row : userRows) {
            String userIdText = row.findElement(By.xpath("td[1]")).getText();
            int userId = Integer.parseInt(userIdText);
            listIds.add(userId);
            String userName = row.findElement(By.xpath("td[2]")).getText();
            String gender = row.findElement(By.xpath("td[3]")).getText();
            String role = row.findElement(By.xpath("td[6]")).getText();
            String status = row.findElement(By.xpath("td[7]")).getText();
            
            assertTrue(userName.contains("Nguyen"), "Search result validation failed.");
            assertEquals("Male", gender);
            assertEquals("Admin", role);
            assertEquals("Active", status);
        }

        // Kiểm tra sắp xếp theo thứ tự tăng dần của User ID
        List<Integer> sortedUserIds = new ArrayList<>(listIds);
        Collections.sort(sortedUserIds);
        assertEquals(sortedUserIds, listIds, "User ID sorting validation failed.");
    }

    @Test
    public void testClickAddUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add User")));

        // Click nút Add User
        addUserButton.click();

        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
        wait.until(ExpectedConditions.urlContains("AddUser.jsp"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("AddUser.jsp"), "Failed to navigate to Add User page.");
    }

    @Test
    public void testClickDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement detailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@action='UserDetails']/button[@type='submit']")));

        // Click nút Details
        detailsButton.click();

        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
        wait.until(ExpectedConditions.urlContains("UserDetails"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("UserDetails"), "Failed to navigate to User Details page.");
    }

    @Test
    public void testClickHome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement homeButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Home")));

        // Click nút Home
        homeButton.click();

        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
        wait.until(ExpectedConditions.urlContains("home"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("home"), "Failed to navigate to Home page.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
