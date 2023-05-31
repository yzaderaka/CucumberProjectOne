package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUwMzQ5NzEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTA3ODE3MSwidXNlcklkIjoiNTQ2NiJ9.pITIpBoApMeB9-QSJbLCL3_w1rfbPWG_H0kUjRa1moo";
    static String employeeId;

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification prepareRequest = given().header("Content-Type", "application/json").header("Authorization", token).
                queryParam("employee_id", employeeId);
        // hitting the endpoint
        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        // verify the response
        response.then().assertThat().statusCode(200);

        String tempEmpId = response.jsonPath().getString("employee.employee_id");

        // we have 2 emp_id, one is global and second is local
        Assert.assertEquals(employeeId, tempEmpId);
    }

    @Test
    public void acreateEmployee() {
        //prepare the request
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"Yaroslav\",\n" +
                        "  \"emp_lastname\": \"Zaderaka\",\n" +
                        "  \"emp_middle_name\": \"Borisovich\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1984-12-23\",\n" +
                        "  \"emp_status\": \"Confirmed\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");
        //hitting the endpoint
        Response response = preparedRequest.when().post("/createEmployee.php");
        //verifying the assertions
        response.then().assertThat().statusCode(201);
        //we are capturing employee id from the response
        employeeId = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employeeId);

        response.then().assertThat().body("Employee.emp_firstname", equalTo("Yaroslav"));
        //it will print the output in console
        response.prettyPrint();
        response.then().assertThat().header("Content-Type", "application/json");

    }

    // in homework create a method to get all employee status and job title
    @Test
    public void cupdateEmployee() {
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "\"employee_id\": \"" + employeeId + "\",\n" +
                        "\"emp_firstname\": \"Yaroslav\",\n" +
                        "\"emp_middle_name\": \"Borisovich\",\n" +
                        "\"emp_lastname\": \"Zaderaka\",\n" +
                        "\"emp_birthday\": \"1984-12-23\",\n" +
                        "\"emp_gender\": \"M\",\n" +
                        "\"emp_job_title\": \"SDET\",\n" +
                        "\"emp_status\": \"confirmed\"\n" +
                        "}");
        //hitting the endpoint
        Response response = prepareRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        //for verification
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
    }

    @Test
    public void dgetUpdatedEmployee() {
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employeeId);

        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        // if you want to verify the body of the response you can do that using homecrast matches
    }
}
