package restAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class LocalRestAssured {

  @Test
  public void getTest() {
    baseURI = "http://localhost:3000/";
    given()
      .get("users")
      .then()
      .statusCode(200)
      .body("[2].firstName", equalTo("Scott"));
  }

  @Test
  public void postTest() {
    JSONObject request = new JSONObject();
    request.put("firstName", "Shivam");
    request.put("lastName", "QA");
    request.put("subjectId", "2");

    baseURI = "http://localhost:3000/";
    given()
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .body(request.toJSONString())
      .when()
      .post("users")
      .then()
      .statusCode(201)
      .log()
      .all();
  }

  @Test
  public void putTest() {
    JSONObject request = new JSONObject();
    request.put("firstName", "Philll");
    request.put("lastName", "Kkkk");
    request.put("subjectId", "1");

    baseURI = "http://localhost:3000/";
    given()
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .body(request.toJSONString())
      .when()
      .put("users/4")
      .then()
      .statusCode(200)
      .log()
      .all();
  }

  @Test
  public void patchTest() {
    JSONObject request = new JSONObject();
    request.put("lastName", "kkkk");

    baseURI = "http://localhost:3000/";
    given()
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .body(request.toJSONString())
      .when()
      .patch("users/4")
      .then()
      .statusCode(200)
      .log()
      .all();
  }

  @Test
  public void deleteUser() {
    baseURI = "http://localhost:3000/";
    when().delete("users/5").then().statusCode(200).log().all();
  }
}
