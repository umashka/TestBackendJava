package Lesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Lesson3Test {
    private final String apiKey = "521dac9eb12641fbbdc2dfe778d28f98";

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    //============= GET /recepies/complexSearch (минимум 5 кейсов)
    @Test
    void apiKeyAuthTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("offset"), is(0));
    }

    @Test
    void noAuthTest() {
        given()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(401);
    }

    @Test
    void oneParamTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "sushi")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), not(0));
    }

    @Test
    void multipleParamsTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", "indian")
                .queryParam("diet", "ketogenic")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), not(0));
    }

    @Test
    void limitLicenseTrueTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("limitLicense", "true")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), not(0));
    }

    //============= POST /recipes/cuisine (минимум 5 кейсов)
    @Test
    void apiKeyAuthPostTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("cuisine"), is("Mediterranean"));
    }

    @Test
    void wrongUrlTest() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine1")
                .then()
                .statusCode(405);
    }

    @Test
    void languageEnPostTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "Honey-Garlic Chicken Thighs")
                .queryParam("ingredientList", "6 chicken thighs \\n 2 teaspoons garlic powder \\n salt and ground black pepper to taste \\n 6 cloves garlic, crushed \\n ⅓ cup honey \\n ¼ cup water \\n 2 tablespoons rice wine vinegar \\n 1 tablespoon soy sauce")
                .queryParam("language", "en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("cuisine"), is("Mediterranean"));
    }

    @Test
    void languageDePostTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "Morgenmad")
                .queryParam("ingredientList", "brød \\n smør \\n kage")
                .queryParam("language", "de")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("cuisine"), is("Mediterranean"));
    }

    @Test
    void emptyParametersPostTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "")
                .queryParam("ingredientList", "")
                .queryParam("language", "en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("cuisine"), is("Mediterranean"));
    }

    //============= автоматизация цепочки создания и удаления блюда в ShoppingList
    /*{
            "status": "success",
            "username": "umashka1",
            "spoonacularPassword": "grasshoppierpiewith90chickensausage",
            "hash": "27b9a4fa403c576ad3d9761d731af59026a2fab7"
    }*/

    @Test
    public void addMealTest() {
        String id = given()
                .queryParam("hash", "27b9a4fa403c576ad3d9761d731af59026a2fab7")
                .queryParam("apiKey", apiKey)
                .body("{\n" +
                        "    \"date\": 1589500800,\n" +
                        "    \"slot\": 1,\n" +
                        "    \"position\": 0,\n" +
                        "    \"type\": \"RECIPE\",\n" +
                        "    \"value\": {\n" +
                        "        \"id\": 296213,\n" +
                        "        \"servings\": 2,\n" +
                        "        \"title\": \"Spinach Salad with Roasted Vegetables and Spiced Chickpea\",\n" +
                        "        \"imageType\": \"jpg\"\n" +
                        "    }\n" +
                        "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/umashka1/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "27b9a4fa403c576ad3d9761d731af59026a2fab7")
                .queryParam("apiKey", apiKey)
                .delete("https://api.spoonacular.com/mealplanner/umashka1/items/" + id)
                .then()
                .statusCode(200);
    }
}
