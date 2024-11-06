package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Đường dẫn đến ChromeDriver
        System.setProperty("webdriver.chrome.driver", "D:\\code\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Tạo ChromeOptions và chỉ định đường dẫn đến trình duyệt Chrome của bạn
        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:\\code\\chrome-win64\\chrome-win64\\chrome.exe");

        // Khởi tạo trình điều khiển với ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Cài đặt thời gian chờ
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Điều hướng đến trang đăng nhập
        driver.get("http://localhost:8080/project/login.jsp");

        // Khởi tạo LoginPage với driver hiện tại
        LoginPage loginPage = new LoginPage(driver);

        // Thực hiện đăng nhập với email và mật khẩu
        loginPage.login("nguyenvana@example.com", "password123");

        // Kiểm tra sự tồn tại của phần tử thông báo lỗi
        List<WebElement> errorElements = driver.findElements(By.xpath("//h4[@style='color: red;']"));
        if (!errorElements.isEmpty()) {
            // Lấy nội dung của thông báo lỗi nếu có
            String errorMessage = errorElements.get(0).getText();
            System.out.println("Error Message: " + errorMessage);
        } else {
            // Không có thông báo lỗi, đăng nhập có thể đã thành công
            System.out.println("Login successful (or no error message displayed).");
        }

        // Đóng trình duyệt sau khi hoàn tất kiểm tra
        driver.quit();
    }
}
