package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static final String PAGE_URL = "/home";
    private WebDriver driver;
    private By userPanelLocator = By.cssSelector("div.userpanel-header");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getUserPanelText(){
        return driver.findElement(userPanelLocator).getText().trim();
    }

    public void logout() {
        System.out.println("Pressing Logout link");
        driver.findElement(userPanelLocator)
                .click();
        driver.findElement(By.xpath("//a[@class='selenium-button-logout button-logout']"))
                .click();
    }

    public void pressClientsLink(){
        System.out.println("Pressing clients link from the menu");
        driver.findElement(By.id("tabs_clients")).click();
    }
}
