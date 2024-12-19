package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class SubjectDetailPage {

    WebDriver driver;

    // Constructor khởi tạo driver
    public SubjectDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators cho các trường trong form
    public By titleField = By.name("title");
    public By descriptionField = By.name("description");
    public By categoryField = By.name("category");
    public By basicPackagePriceField = By.name("basicPackagePrice");
    public By advancedPackagePriceField = By.name("advancedPackagePrice");
    public By statusDropdown = By.name("status");
    public By sponsoredLabel = By.className("form-check-label");
    public By contentField = By.name("content");
    public By saveButton = By.xpath("//button[contains(text(),'Save Changes')]");
    public By addMediaButton = By.cssSelector("a[href^='addMedia']");
    public By goToLessonListButton = By.cssSelector("a[href^='LessonListController']");

    // Method to get the value of a field
    public String getTitleValue() {
        return driver.findElement(titleField).getAttribute("value");
    }

    public String getDescriptionValue() {
        return driver.findElement(descriptionField).getText();
    }

    public String getCategoryValue() {
        return driver.findElement(categoryField).getAttribute("value");
    }

    public String getBasicPackagePriceValue() {
        return driver.findElement(basicPackagePriceField).getAttribute("value");
    }

    public String getAdvancedPackagePriceValue() {
        return driver.findElement(advancedPackagePriceField).getAttribute("value");
    }

    public String getStatusValue() {
        return driver.findElement(statusDropdown).getAttribute("value");
    }

    public boolean isSponsoredChecked() {
        return driver.findElement(sponsoredLabel).getText().equals("Sponsored");
    }

    public String getContentValue() {
        return driver.findElement(contentField).getText();
    }

    // Methods to interact with the form
    public void enterTitle(String title) {
        driver.findElement(titleField).clear();
        driver.findElement(titleField).sendKeys(title);
    }

    public void enterDescription(String description) {
        driver.findElement(descriptionField).clear();
        driver.findElement(descriptionField).sendKeys(description);
    }

    public void enterCategory(String category) {
        driver.findElement(categoryField).clear();
        driver.findElement(categoryField).sendKeys(category);
    }

    public void enterBasicPackagePrice(String price) {
        driver.findElement(basicPackagePriceField).clear();
        driver.findElement(basicPackagePriceField).sendKeys(price);
    }

    public void enterAdvancedPackagePrice(String price) {
        driver.findElement(advancedPackagePriceField).clear();
        driver.findElement(advancedPackagePriceField).sendKeys(price);
    }

    public void selectStatus(String status) {
        driver.findElement(statusDropdown).sendKeys(status);
    }

    public void enterContent(String content) {
        driver.findElement(contentField).clear();
        driver.findElement(contentField).sendKeys(content);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void clickAddMediaButton() {
        driver.findElement(addMediaButton).click();
    }

    public void clickGoToLessonListButton() {
        driver.findElement(goToLessonListButton).click();
    }

    

}
