package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientPage {
    private WebDriver driver;
    private static final String PAGE_URL = "/clients/manage";

    public ClientPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getH2Text() {
        return driver
                .findElement(By.xpath("//div[@id='headline2']//h2"))
                .getText().trim();
    }

    public void createClient(String name, String address, String city) {
        pressNewClientLink()
                .enterName(name)
                .enterAddress(address)
                .enterCity(city)
                .pressSaveClient();
    }

    public ClientPage enterAddress(String address) {
        System.out.println("Entering address:" + address);
        WebElement addressField = driver.findElement(By.name("firm_addr"));
        addressField.clear();
        addressField.sendKeys(address);
        return this;
    }

    public ClientPage enterCity(String city) {
        System.out.println("Entering city:" + city);
        WebElement cityField = driver.findElement(By.name("firm_town"));
        cityField.clear();
        cityField.sendKeys(city);
        return this;
    }

    public ClientPage enterName(String name) {
        System.out.println("Entering name:" + name);
        WebElement nameField = driver.findElement(By.name("firm_name"));
        nameField.clear();
        nameField.sendKeys(name);
        return this;
    }

    public ClientPage pressNewClientLink() {
        System.out.println("Pressing New Client link");
        driver.findElement(By.xpath("//a[@class='newbtn selenium-add-client-button']")).click();
        return this;
    }

    public void pressSaveClient() {
        System.out.println("Pressing Save button");
        WebElement saveInput = driver.findElement(By.name("do_submit"));
        saveInput.click();
    }

    public String getSuccessMessage() {
        return driver
                .findElement(By.id("okmsg"))
                .getText().trim();
    }

    public String getEmptyListMessage() {
        return driver
                .findElement(By.id("emptylist"))
                .getText();
    }

    public ClientPage navigateTo() {
        System.out.println("Navigating to Clients page");
        driver.navigate().to(Defaults.BASE_URL + PAGE_URL);
        return this;
    }

    public String getTableText() {
        return driver
                .findElement(By.id("fakturi_table")).getText();
    }
}
