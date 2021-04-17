package test;

import api.ClientAPI;
import dto.Client;
import enums.SearchField;
import org.junit.jupiter.api.*;

public class Homework extends BaseTest{

   /*
    Your homework is to create the below tests using UI/API interactions
    If necessary add more methods to ClientPage
     */
    @BeforeAll
    public static void createSearchableElement() {
        Client clientToSearch = new Client(
                "Postman",
                "Sofia",
                "Lulin 10",
                "Elvis Presley",
                false
        );
        ClientAPI.create(clientToSearch);
    }

    @BeforeEach
    public void background() {
        loginPage.navigateTo();
        loginPage.defaultLogin();
        homePage.pressClientsLink();
        clientPage.enterSearchMenu();
    }

    @AfterAll
    public static void deleteSearchableElements() {
        ClientAPI.deleteAll();
    }

    //Create test for search client by name
    @Test
    @DisplayName("Successfully search client by name")
    @Tag("smoke")
    public void searchClientByName() {
        clientPage.enterSearchText("postman", SearchField.NAME);
        clientPage.pressSearchButton();
        Assertions.assertEquals("Postman", clientPage.getSearchResult());
    }

    //Create test for search client by mol
    @Test
    @DisplayName("Successfully search client by mol")
    @Tag("smoke")
    public void searchClientByMol() {
        clientPage.enterSearchText("elvis presley", SearchField.MOL);
        clientPage.pressSearchButton();
        Assertions.assertEquals("Postman", clientPage.getSearchResult());
    }

    // Create test for search client by address
    @Test
    @DisplayName("Successfully search client by address")
    @Tag("smoke")
    public void searchClientByAddress() {
        clientPage.enterSearchText("lulin 10", SearchField.ADDRESS);
        clientPage.pressSearchButton();
        Assertions.assertEquals("Postman", clientPage.getSearchResult());
    }

    //Create test for search which does not match existing client
    @Test
    @DisplayName("Unsuccessful search")
    @Tag("smoke")
    public void unsuccessfulSearchByAddress() {
        clientPage.enterSearchText("mladost", SearchField.ADDRESS);
        clientPage.pressSearchButton();
        Assertions.assertEquals(
                "Не са намерени клиенти, отговарящи на зададените критерии.",
                clientPage.getSearchMessage()
        );
    }
}
