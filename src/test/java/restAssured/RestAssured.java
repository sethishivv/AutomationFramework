package restAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class RestAssured {

  public static Properties conf = new Properties();
  public static FileReader fr;

  @Test(enabled = true)
  public void getStatusCode() throws IOException {
    fr =
      new FileReader(
        System.getProperty("user.dir") +
        "/src/test/resources/configfiles/config.properties"
      );
    conf.load(fr);
    Response response = get(conf.getProperty("url"));
    int statusCode = response.getStatusCode();
    long timeTaken = response.getTime();
    System.out.println("Status code of the site = " + statusCode);
    System.out.println("Time Taken by the site = " + timeTaken + "ms");
    System.out.println(response.getBody().asPrettyString());

    org.testng.Assert.assertEquals(statusCode, 200);
  }

  @Test(enabled = true)
  public void getBodyName() {
    given()
      .get("https://reqres.in/api/users")
      .then()
      .statusCode(200)
      .body("data[1].first_name", equalTo("Janet"))
      .log()
      .all();
  }

  @Test(enabled = true)
  public void postUser() {
    JSONObject request = new JSONObject();
    request.put("name", "Steven");
    request.put("job", "QA");
    baseURI = "https://reqres.in/api";

    given()
      .body(request.toJSONString())
      .when()
      .post("/users/2")
      .then()
      .statusCode(201)
      .log()
      .all();
  }

  @Test(enabled = true)
  public void putUser() {
    JSONObject request = new JSONObject();
    request.put("name", "Steven");
    request.put("job", "QA");
    baseURI = "https://reqres.in/api";

    given()
      .body(request.toJSONString())
      .when()
      .put("/users/2")
      .then()
      .statusCode(200)
      .log()
      .all();
  }

  @Test
  public void patchUser() {
    JSONObject request = new JSONObject();
    request.put("name", "Steven");
    request.put("job", "QA");
    baseURI = "https://reqres.in/api";

    given()
      .body(request.toJSONString())
      .when()
      .patch("/users/2")
      .then()
      .statusCode(200)
      .log()
      .all();
  }

  @Test
  public void deleteUser() {
    baseURI = "https://reqres.in/api";
    when().delete("/users/2").then().statusCode(204).log().all();
  }
}
