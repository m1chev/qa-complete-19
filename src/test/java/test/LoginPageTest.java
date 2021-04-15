package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.Defaults;

public class LoginPageTest extends BaseTest {
    private static final String LOGIN_ERROR = "Грешно потребителско име или парола. Моля, опитайте отново.";
    private static final String BLANK_CREDS_ERROR = "Моля, попълнете вашия email";
    private static final String LOGOUT_SUCCESS = "Вие излязохте от акаунта си.";


    @Test
    @DisplayName("Can login with valid credentials")
    @Tag("smoke")
    public void canLoginWithValidCredentials() {
        loginPage.navigateTo();
        //Assert login page heading
        Assertions.assertEquals("Вход в inv.bg", loginPage.getH1Text());
        loginPage.enterEmail(Defaults.EMAIL);
        loginPage.enterPassword(Defaults.PASSWORD);
        loginPage.pressLoginButton();
        //Assert that login was successful
        Assertions.assertEquals(Defaults.EMAIL, homePage.getUserPanelText());
    }

    @Test
    @DisplayName("Cant login with invalid credentials")
    @Tag("smoke")
    public void cantLoginWithInvalidCredentials() {
        loginPage.navigateTo();
        //Assert login page heading
        Assertions.assertEquals("Вход в inv.bg", loginPage.getH1Text());
        loginPage.login(Defaults.EMAIL, "121212343");
        Assertions.assertEquals(LOGIN_ERROR, loginPage.getLoginError());
    }

    @Test
    @DisplayName("Cant login with blank credentials")
    @Tag("smoke")
    public void cantLoginWithBlankCredentials() {
        loginPage.navigateTo();
        //Assert login page heading
        Assertions.assertEquals("Вход в inv.bg", loginPage.getH1Text());
        loginPage.pressLoginButton();
        Assertions.assertEquals(BLANK_CREDS_ERROR, loginPage.getLoginError());
    }


    @Test
    @DisplayName("Can login with valid credentials and logout")
    @Tag("smoke")
    public void canLoginWithValidCredentialsAndLogout() {
        loginPage.login(Defaults.EMAIL, Defaults.PASSWORD);
        Assertions.assertEquals(Defaults.EMAIL, homePage.getUserPanelText());
        //Logout
        homePage.logout();
        //Assert logout message
        Assertions.assertEquals(LOGOUT_SUCCESS, loginPage.getLogoutSuccess());
    }
}
