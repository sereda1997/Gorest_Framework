import infrastructure.utils.Report;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;

import static infrastructure.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.containsPath;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestUtils {

    public static String mainUrl = "https://gorest.co.in/public-api/";

    static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setAccept(ContentType.XML)
            .addHeader("Authorization", "Bearer ebb7d56e708802aa6e045d2a05603e7ab558673d988d4926c052084761f2e2d6")
            .build();


    public static Integer createUser(String name, String gender, String emailAddress) {
        Report.log("Creating user "+ name + gender+emailAddress);
        return given().spec(requestSpec)
                .contentType("application/json")
                .body("{\"name\":\"" + name + "\", \"gender\":\"" + gender + "\", \"email\":\"" + emailAddress + "\", \"status\":\"Active\"}")
                .when()
                .post(mainUrl + UsersEndpoint.getName())
                .then().extract().path("data.id");

    }

    public static void verifyUserCreated(Integer id, String name) {
        Report.log("Verify user created");
        ValidatableResponse response =
                given().spec(requestSpec)
                        .when()
                        .get(mainUrl + UsersEndpoint.getName() + "/" + id.toString())
                        .then();

        response.assertThat().statusCode(200).body("data.name", equalTo(name));
    }

    public static void editField(Integer id, String fieldName, String value) {
        Report.log("Edit field"+fieldName+"to: "+ value);
        ValidatableResponse response =
                given().spec(requestSpec)
                        .contentType("application/json")
                        .body("{\"" + fieldName + "\"" + ":" + "\"" + value + "\"}")
                        .when()
                        .patch(mainUrl + UsersEndpoint.getName() + "/" + id.toString())
                        .then().log().ifError();

    }

    public static void verifyUserFields(Integer id, String fieldName, String value) {
        Report.log("Verify field "+ fieldName + " Vhenged to value:"+value);
        ValidatableResponse response =
                given().spec(requestSpec)
                        .when()
                        .get(mainUrl + UsersEndpoint.getName() + "/" + id.toString())
                        .then();

        response.assertThat().statusCode(200).body("data." + fieldName, equalTo(value));
    }


    public static void deleteUser(Integer id){
        Report.log("Deleting user with id: " + id);
        given().spec(requestSpec)
                .when()
                .delete(mainUrl + UsersEndpoint.getName()+ "/" + id.toString())
                .then().log().ifError().body("code", equalTo(204));
    }


}
