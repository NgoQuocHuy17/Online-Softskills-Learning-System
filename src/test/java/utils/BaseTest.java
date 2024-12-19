package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;  // Add the AfterClass annotation
import java.util.concurrent.TimeUnit;
import pages.LoginPage;

public class BaseTest {

    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\code\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:\\code\\chrome-win64\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void login(String email, String password, int courseId) {      
        // Logout to ensure clean state before login
        driver.get("http://localhost:8080/project/logout.jsp");

        // Navigate to login page and perform login
        driver.get("http://localhost:8080/project/login.jsp");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        // Optional wait to ensure login is successful before navigating to subject page
        try {
            Thread.sleep(2000); // 2000 milliseconds = 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Navigate to the subject detail page
        driver.get("http://localhost:8080/project/subjectDetail?action=edit&courseId=" + courseId);
    }
    

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        // Any cleanup code after all tests run, if necessary
        if (driver != null) {
            driver.quit();
        }
    }
}
