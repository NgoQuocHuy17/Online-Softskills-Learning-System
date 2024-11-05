package AutomationTest;

import java.time.Duration;
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
    public void testSearch() {
        // Nhập từ khóa tìm kiếm và submit
        WebElement searchInput = driver.findElement(By.name("searchTerm"));
        searchInput.sendKeys("Nguyen Van A");
        searchInput.submit();

        // Kiểm tra kết quả tìm kiếm
        List<WebElement> userRows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement row : userRows) {
            String userName = row.findElement(By.xpath("td[2]")).getText();
            assertTrue(userName.contains("Nguyen Van A"), "Search result validation failed.");
        }
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
    public void testFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Lấy các phần tử cần tương tác
        WebElement genderMaleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("genderMale")));
        WebElement roleAdminCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("roleAdmin")));
        WebElement statusValidCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("statusValid")));
        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".filter-form .btn-primary")));

        // Chọn các checkbox nếu chưa được chọn
        if (!genderMaleCheckbox.isSelected()) {
            genderMaleCheckbox.click();
        }
        if (!roleAdminCheckbox.isSelected()) {
            roleAdminCheckbox.click();
        }
        if (!statusValidCheckbox.isSelected()) {
            statusValidCheckbox.click();
        }

        // Submit form
        filterButton.click();

        // Đợi trang tải lại và kiểm tra kết quả lọc
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
        List<WebElement> userRows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement row : userRows) {
            String gender = row.findElement(By.xpath("td[3]")).getText();
            String role = row.findElement(By.xpath("td[6]")).getText();
            String status = row.findElement(By.xpath("td[7]")).getText();
            assertEquals("Male", gender);
            assertEquals("Admin", role);
            assertEquals("Active", status);
        }
    }

    @Test
    public void testFilterNoResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement statusInvalidCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("statusInvalid")));
        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".filter-form .btn-primary")));

        // Chọn checkbox Invalid nếu chưa được chọn
        if (!statusInvalidCheckbox.isSelected()) {
            statusInvalidCheckbox.click();
        }

        // Submit form
        filterButton.click();

        // Đợi trang tải lại và kiểm tra không có kết quả trả về
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-warning")));
        WebElement noResultsAlert = driver.findElement(By.cssSelector(".alert.alert-warning"));

        // Xác thực thông báo "Không có người dùng hợp lệ"
        assertTrue(noResultsAlert.isDisplayed(), "Expected 'No valid users' alert to be displayed.");
    }

    @Test
    public void testSortAscending() {
        // Sắp xếp tăng dần theo User ID
        driver.findElement(By.xpath("//th[contains(text(), 'User ID')]//a[1]")).click();

        // Xác thực kết quả sắp xếp tăng dần
        List<WebElement> userIds = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        for (int i = 0; i < userIds.size() - 1; i++) {
            int id1 = Integer.parseInt(userIds.get(i).getText());
            int id2 = Integer.parseInt(userIds.get(i + 1).getText());
            assertTrue(id1 <= id2, "Sorting ascending validation failed.");
        }
    }

    @Test
    public void testSortDescending() {
        // Sắp xếp giảm dần theo User ID
        driver.findElement(By.xpath("//th[contains(text(), 'User ID')]//a[2]")).click();

        // Xác thực kết quả sắp xếp giảm dần
        List<WebElement> userIds = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        for (int i = 0; i < userIds.size() - 1; i++) {
            int id1 = Integer.parseInt(userIds.get(i).getText());
            int id2 = Integer.parseInt(userIds.get(i + 1).getText());
            assertTrue(id1 >= id2, "Sorting descending validation failed.");
        }
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

//    @Test
//    public void testSearchAndFilter() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//
//        // Tìm kiếm
//        WebElement searchInput = driver.findElement(By.name("searchTerm"));
//        searchInput.sendKeys("Nguyen Van A");
//        searchInput.submit();
//
//        // Chờ kết quả tìm kiếm
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
//
//        // Áp dụng bộ lọc
//        WebElement genderMaleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("genderMale")));
//        WebElement roleAdminCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("roleAdmin")));
//        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".filter-form .btn-primary")));
//
//        if (!genderMaleCheckbox.isSelected()) {
//            genderMaleCheckbox.click();
//        }
//        if (!roleAdminCheckbox.isSelected()) {
//            roleAdminCheckbox.click();
//        }
//
//        // Submit bộ lọc
//        filterButton.click();
//
//        // Đợi trang tải lại và kiểm tra kết quả lọc
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
//        List<WebElement> userRows = driver.findElements(By.xpath("//table/tbody/tr"));
//        for (WebElement row : userRows) {
//            String gender = row.findElement(By.xpath("td[3]")).getText();
//            String role = row.findElement(By.xpath("td[6]")).getText();
//            assertEquals("Male", gender);
//            assertEquals("Admin", role);
//        }
//    }
//
//    @Test
//    public void testFilterAndSort() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement genderMaleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("genderMale")));
//        WebElement roleAdminCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("roleAdmin")));
//        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".filter-form .btn-primary")));
//
//        // Áp dụng bộ lọc
//        if (!genderMaleCheckbox.isSelected()) {
//            genderMaleCheckbox.click();
//        }
//        if (!roleAdminCheckbox.isSelected()) {
//            roleAdminCheckbox.click();
//        }
//
//        // Submit bộ lọc
//        filterButton.click();
//
//        // Đợi trang tải lại
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
//
//        // Sắp xếp tăng dần theo User ID
//        driver.findElement(By.xpath("//th[contains(text(), 'User ID')]//a[1]")).click();
//
//        // Xác thực kết quả sắp xếp
//        List<WebElement> userIds = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
//        for (int i = 0; i < userIds.size() - 1; i++) {
//            int id1 = Integer.parseInt(userIds.get(i).getText());
//            int id2 = Integer.parseInt(userIds.get(i + 1).getText());
//            assertTrue(id1 <= id2, "Sorting ascending validation failed.");
//        }
//    }

    @Test
    public void testSearchFilterAndSort() {
        // Tìm kiếm
        WebElement searchInput = driver.findElement(By.name("searchTerm"));
        searchInput.sendKeys("Nguyen Van A");
        searchInput.submit();

        // Filter
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement genderMaleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("genderMale")));
        WebElement roleAdminCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("roleAdmin")));
        WebElement statusValidCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("statusValid")));
        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".filter-form .btn-primary")));

        if (!genderMaleCheckbox.isSelected()) {
            genderMaleCheckbox.click();
        }
        if (!roleAdminCheckbox.isSelected()) {
            roleAdminCheckbox.click();
        }
        if (!statusValidCheckbox.isSelected()) {
            statusValidCheckbox.click();
        }
        filterButton.click();

        // Sort
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
        driver.findElement(By.xpath("//th[contains(text(), 'User ID')]//a[1]")).click(); // Sắp xếp tăng dần theo User ID

        // Xác thực kết quả
        List<WebElement> userRows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement row : userRows) {
            String userName = row.findElement(By.xpath("td[2]")).getText();
            assertTrue(userName.contains("Nguyen Van A"), "Search result validation failed.");

            String gender = row.findElement(By.xpath("td[3]")).getText();
            String role = row.findElement(By.xpath("td[6]")).getText();
            String status = row.findElement(By.xpath("td[7]")).getText();
            assertEquals("Male", gender);
            assertEquals("Admin", role);
            assertEquals("Active", status);
        }
    }

//    @Test
//    public void testClickAddUser() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add User")));
//
//        // Click nút Add User
//        addUserButton.click();
//
//        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
//        wait.until(ExpectedConditions.urlContains("AddUser.jsp"));
//        String currentUrl = driver.getCurrentUrl();
//        assertTrue(currentUrl.contains("AddUser.jsp"), "Failed to navigate to Add User page.");
//    }
//
//    @Test
//    public void testClickDetails() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        WebElement detailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@action='UserDetails']/button[@type='submit']")));
//
//        // Click nút Details
//        detailsButton.click();
//
//        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
//        wait.until(ExpectedConditions.urlContains("UserDetails"));
//        String currentUrl = driver.getCurrentUrl();
//        assertTrue(currentUrl.contains("UserDetails"), "Failed to navigate to User Details page.");
//    }
//
//    @Test
//    public void testClickHome() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        WebElement homeButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Home")));
//
//        // Click nút Home
//        homeButton.click();
//
//        // Đợi trang chuyển đổi và kiểm tra xem URL đã thay đổi đúng chưa
//        wait.until(ExpectedConditions.urlContains("home"));
//        String currentUrl = driver.getCurrentUrl();
//        assertTrue(currentUrl.contains("home"), "Failed to navigate to Home page.");
//    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
