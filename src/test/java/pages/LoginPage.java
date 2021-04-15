package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private static final String PAGE_URL = "/login";
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage navigateTo() {
        System.out.println("Navigating to Login page");
        driver.navigate().to(Defaults.BASE_URL + PAGE_URL);
        return this;
    }

    public void login(String email, String password) {
        navigateTo()
                .enterEmail(email)
                .enterPassword(password)
                .pressLoginButton();
    }

    public void defaultLogin(){
        login(Defaults.EMAIL, Defaults.PASSWORD);
    }

    public LoginPage enterEmail(String email) {
        System.out.println("Entering email:" + email);
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        System.out.println("Entering password:" + password);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage pressLoginButton() {
        System.out.println("Pressing Login button");
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='selenium-submit-button g-recaptcha']"));
        loginButton.click();
        return this;
    }

    public String getH1Text(){
        return driver.
                findElement(By.xpath("//h1"))
                .getText().trim();
    }

    public String getLoginError() {
        WebElement error = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        return error
                .getText()
                .trim();
    }

    public String getLogoutSuccess() {
        return driver
                .findElement(By.id("okmsg"))
                .getText().trim();
    }
}
