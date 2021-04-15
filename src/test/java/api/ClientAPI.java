package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import dto.Client;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pages.Defaults;

import java.util.List;

public class ClientAPI {
    private static final String BASE_PATH = "/RESTapi";
    private static final String CLIENT_URL = "/client";
    private static final String CLIENTS_URL = "/clients";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static {
        //Basic setup
        RestAssured.baseURI = Defaults.BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.authentication = RestAssured.preemptive().basic(Defaults.EMAIL, Defaults.PASSWORD);
    }


    public static Response getAll(){
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(CLIENTS_URL);
        response.prettyPrint();
        return response;
    }

    public static Response delete(String id){
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(CLIENT_URL + "/" + id);
        response.prettyPrint();
        return response;
    }

    public static Response create(Client client){
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(client))
                .when()
                .post(CLIENT_URL);
        response.prettyPrint();
        return response;
    }

    public static void deleteAll(){
        //Get all clients
        Response getAllResponse = getAll();
        //Extract all client ids from the response body
        List<String> ids = JsonPath.read(getAllResponse.body().asString(), "$..id");
        System.out.println("Ids for deletion found:" + ids.toString());
        //Delete all ids 1 by 1
        ids.forEach(id -> delete(id));
    }
}
