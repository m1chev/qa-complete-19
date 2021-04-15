package test;

import api.ClientAPI;
import dto.Client;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientPageTest extends BaseTest {
    private static final String SUCCESS_MESSAGE = "Клиентът е добавен успешно.";
    private static final String EMPTY_LIST_MESSAGE = "Все още нямате добавени клиенти.";


    @Test
    @DisplayName("Can create new client with valid information")
    public void canCreateNewClientWithValidInformation() {
        loginPage.defaultLogin();
        homePage.pressClientsLink();
        //Assert heading text
        Assertions.assertEquals("Клиенти", clientPage.getH2Text());
        //Create new client
        clientPage.createClient("Pragmatic", "Student District", "Sofia");
        //Assert that the client was created / success message
        Assertions.assertEquals(SUCCESS_MESSAGE, clientPage.getSuccessMessage());
        //Delete client via API
        ClientAPI.deleteAll();
    }

    @Test
    @DisplayName("Can get correct message when there are no clients")
    public void canGetCorrectMessageWhenThereAreZeroClients() {
        ClientAPI.deleteAll(); //Delete client via API
        loginPage.defaultLogin();
        homePage.pressClientsLink();
        //Assert heading text
        Assertions.assertEquals("Клиенти", clientPage.getH2Text());
        //Assert message is correct when 0 clients exist
        Assertions.assertEquals(EMPTY_LIST_MESSAGE, clientPage.getEmptyListMessage());
        //Create client
        clientPage.createClient("TO_BE_DELETED", "ADDRESS", "CITY");
        //Check that the client is visible
        clientPage.navigateTo();
        Assertions.assertTrue(clientPage.getTableText().contains("TO_BE_DELETED"), "Client name not visible in the table");
        //Delete all clients
        ClientAPI.deleteAll();
        clientPage.navigateTo();
        //Check that the empty list message is shown
        Assertions.assertEquals(EMPTY_LIST_MESSAGE, clientPage.getEmptyListMessage());
    }

    @Test
    @DisplayName("Can get all clients using RESTful api")
    public void canGetAllClientsViaAPI() {
        Response response = ClientAPI.getAll();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Can create new client via RESTful api")
    public void canCreateClientViaAPI() {
        Client clientDto = new Client();
        clientDto.setFirm_name("Pragmatic");
        clientDto.setFirm_addr("Student district");
        clientDto.setFirm_town("Sofia");
        clientDto.setFirm_mol("Alex");
        clientDto.setFirm_is_reg_vat(false);
        Response response = ClientAPI.create(clientDto);
        Assertions.assertEquals(200, response.statusCode());
    }
}
