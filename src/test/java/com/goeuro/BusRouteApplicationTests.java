package com.goeuro;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusRouteTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusRouteApplicationTests {

    @LocalServerPort
    private int randomServerPort;

    @Before
    public void init() {
        RestAssured.port = randomServerPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void directConnectionExists() {
        given()
                .param("dep_sid", 138)
                .param("arr_sid", 19)
                .when()
                .get("/api/direct")
                .then()
                .statusCode(200)
                .body("dep_sid", equalTo(138))
                .body("arr_sid", equalTo(19))
                .body("direct_bus_route", equalTo(true));
    }

    @Test
    public void directConnectionNotExists() {
        given()
                .param("dep_sid", 138)
                .param("arr_sid", 20)
                .when()
                .get("/api/direct")
                .then()
                .statusCode(200)
                .body("dep_sid", equalTo(138))
                .body("arr_sid", equalTo(20))
                .body("direct_bus_route", equalTo(false));
    }

    @Test
    public void stationDoesNotExists() {
        given()
                .param("dep_sid", -138)
                .param("arr_sid", 20)
                .when()
                .get("/api/direct")
                .then()
                .statusCode(400)
                .body("code", equalTo(400))
                .body("message", equalTo("Invalid station ids were provided"));
    }

    @Test
    public void missingMandatoryParams() {
        given()
                .param("dep_sid", 138)
                .when()
                .get("/api/direct")
                .then()
                .statusCode(400)
                .body("code", equalTo(400))
                .body("message", equalTo("Required int parameter 'arr_sid' is not present"));

        given()
                .param("arr_sid", 138)
                .when()
                .get("/api/direct")
                .then()
                .statusCode(400)
                .body("code", equalTo(400))
                .body("message", equalTo("Required int parameter 'dep_sid' is not present"));
    }
}
