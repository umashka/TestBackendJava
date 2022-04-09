package Lesson4HomeWork;

import Lesson4.Response;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class Lesson4Test {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "521dac9eb12641fbbdc2dfe778d28f98";

    @BeforeEach
    void BeforeTest(){
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("username", "umashka1")
                .addQueryParam("hash", "27b9a4fa403c576ad3d9761d731af59026a2fab7")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    //============= GET /recepies/complexSearch
    @Test
    void apiKeyAuthTest() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);

    }

    @Test
    void oneParamTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "sushi")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    //============= POST /recipes/cuisine
    @Test
    void apiKeyAuthPostTest() {
        RestAssured.responseSpecification =
                responseSpecification
                        .expect()
                        .body(containsString("Mediterranean"));

        given()
                .queryParam("apiKey", apiKey)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void languageEnPostTest() {
        RestAssured.responseSpecification =
                responseSpecification
                        .expect()
                        .body(containsString("Mediterranean"));

        given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "Honey-Garlic Chicken Thighs")
                .queryParam("ingredientList", "6 chicken thighs \\n 2 teaspoons garlic powder \\n salt and ground black pepper to taste \\n 6 cloves garlic, crushed \\n ⅓ cup honey \\n ¼ cup water \\n 2 tablespoons rice wine vinegar \\n 1 tablespoon soy sauce")
                .queryParam("language", "en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    //============= автоматизация цепочки создания и удаления блюда в ShoppingList
    /*{
            "status": "success",
            "username": "umashka1",
            "spoonacularPassword": "grasshoppierpiewith90chickensausage",
            "hash": "27b9a4fa403c576ad3d9761d731af59026a2fab7"
    }*/

    @Test
    public void GetShoppingListPositiveTest(){
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/mealplanner/umashka1/shopping-list")
                .then()
                .spec(responseSpecification);
    }

    @Test
    public void AddToShoppingListPositiveTest(){
        AddToShoppingListResponse response = given().spec(requestSpecification)
                .when()
                .formParam("item","1 package baking powder")
                .formParam("aisle","Baking")
                .formParam("parse","true")
                .post("https://api.spoonacular.com/mealplanner/umashka1/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .body()
                .as(AddToShoppingListResponse.class);
        assertThat(response.aisle, containsString("Baking"));

        String id = response.id.toString();

        given().spec(requestSpecification)
                .delete("https://api.spoonacular.com/mealplanner/umashka1/shopping-list/items/" + id)
                .then()
                .spec(responseSpecification);
    }


}
