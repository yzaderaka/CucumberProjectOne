package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest = given().
                header("Content-type", "application/json").
                body("{\n" +
                        "  \"email\": \"yzaderaka@mail.ru\",\n" +
                        "  \"password\": \"AA9882ip\"\n" +
                        "}");
        // hitting the endpoint
        Response response = generateTokenRequest.when().post("/generateToken.php");
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }
}
