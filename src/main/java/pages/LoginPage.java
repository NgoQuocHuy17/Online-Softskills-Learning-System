package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    // Constructor khởi tạo driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Define web elements
    By emailField = By.xpath("//input[@type='email' and @name='email']");
    By passwordField = By.xpath("//input[@type='password' and @name='password']");
    By signInButton = By.xpath("//button[@type='submit' and contains(@class, 'login-btn')]");
    By errorMessage = By.xpath("//h4[contains(@style, 'color: red')]");

    // Methods to interact with elements
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        if (error.isDisplayed()) {
            return error.getText();
        } else {
            return "";
        }
    }

    // Login method
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public By getEmailField() {
        return emailField;
    }

}
