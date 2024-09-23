import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {
    @Test
    public void testGetRequest() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Выполняем GET-запрос
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .contentType(ContentType.JSON) // Ожидаем JSON ответ
                .extract().response();

        // Проверяем статус код
        response.then().statusCode(200);

        // Проверяем содержимое ответа
        response.then().body("args.foo1", equalTo("bar1"));
        response.then().body("args.foo2", equalTo("bar2"));

        // Проверяем заголовки ответа (пример)
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.x-forwarded-port", equalTo("443"));
    }

    @Test
    public void testPostRequest() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Выполняем POST-запрос
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .contentType(ContentType.JSON) // Ожидаем JSON ответ
                .extract().response();

        // Проверяем статус код
        response.then().statusCode(200);

        // Проверяем содержимое ответа
        response.then().body("data", equalTo(requestBody));

        // Проверяем заголовки ответа с использованием containsString
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.content-type", containsString("text/plain"));
    }

    @Test
    public void testPostFormData() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Выполняем POST-запрос с данными формы
        Response response = given()
                .contentType(ContentType.URLENC) // Устанавливаем тип контента
                .formParam("foo1", "bar1") // Передаем данные формы
                .formParam("foo2", "bar2")
                .when()
                .post("/post") // Указываем путь
                .then()
                .log().all() // Логируем ответ для отладки
                .contentType(ContentType.JSON) // Ожидаем JSON-ответ
                .extract().response();

        // Проверяем статус код
        if (response.statusCode() != 200) {
            System.err.println("Error: Received status code " + response.statusCode());
            System.out.println("Response body: " + response.getBody().asString());
            assert false; // Прерываем тест, если код не 200
        }

        // Проверяем содержимое ответа
        response.then().body("form.foo1", equalTo("bar1"));
        response.then().body("form.foo2", equalTo("bar2"));

        // Проверяем заголовки ответа
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.content-type", containsString("application/x-www-form-urlencoded"));
        response.then().body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testPutRequest() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Тело запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполняем PUT-запрос
        Response response = given()
                .contentType("text/plain") // Устанавливаем тип контента
                .body(requestBody) // Передаем данные в теле запроса
                .when()
                .put("/put") // Указываем путь
                .then()
                .log().all() // Логируем ответ для отладки
                .contentType(ContentType.JSON) // Ожидаем JSON ответ
                .extract().response();

        // Проверяем статус код
        response.then().statusCode(200);

        // Проверяем содержимое ответа
        response.then().body("data", equalTo(requestBody)); // Проверяем, что тело запроса возвращается в поле "data"
        response.then().body("args", is(emptyMap())); // Проверяем, что аргументы пустые
        response.then().body("files", is(emptyMap())); // Проверяем, что поля files пустые
        response.then().body("form", is(emptyMap())); // Проверяем, что поля form пустые
        response.then().body("url", equalTo("https://postman-echo.com/put")); // Проверяем URL ответа

        // Проверяем заголовки ответа
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.content-type", containsString("text/plain")); // Обратите внимание на изменение здесь
    }

    @Test
    public void testPatchRequest() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Тело запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполняем PATCH-запрос
        Response response = given()
                .contentType("text/plain") // Устанавливаем тип контента
                .body(requestBody) // Передаем данные в теле запроса
                .when()
                .patch("/patch") // Указываем путь
                .then()
                .log().all() // Логируем ответ для отладки
                .contentType(ContentType.JSON) // Ожидаем JSON ответ
                .extract().response();

        // Проверяем статус код
        response.then().statusCode(200);

        // Проверяем содержимое ответа
        response.then().body("data", equalTo(requestBody)); // Проверяем, что тело запроса возвращается в поле "data"
        response.then().body("args", is(emptyMap())); // Проверяем, что аргументы пустые
        response.then().body("files", is(emptyMap())); // Проверяем, что поля files пустые
        response.then().body("form", is(emptyMap())); // Проверяем, что поля form пустые
        response.then().body("url", equalTo("https://postman-echo.com/patch")); // Проверяем URL ответа

        // Проверяем заголовки ответа
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.content-type", containsString("text/plain")); // Учтем наличие charset
    }

    @Test
    public void testDeleteRequest() {
        // Указываем базовый URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Тело запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполняем DELETE-запрос
        Response response = given()
                .contentType("text/plain") // Устанавливаем тип контента
                .body(requestBody) // Передаем данные в теле запроса
                .when()
                .delete("/delete") // Указываем путь
                .then()
                .log().all() // Логируем ответ для отладки
                .contentType(ContentType.JSON) // Ожидаем JSON ответ
                .extract().response();

        // Проверяем статус код
        response.then().statusCode(200);

        // Проверяем содержимое ответа
        response.then().body("data", equalTo(requestBody)); // Проверяем, что тело запроса возвращается в поле "data"
        response.then().body("args", is(emptyMap())); // Проверяем, что аргументы пустые
        response.then().body("files", is(emptyMap())); // Проверяем, что поля files пустые
        response.then().body("form", is(emptyMap())); // Проверяем, что поля form пустые
        response.then().body("url", equalTo("https://postman-echo.com/delete")); // Проверяем URL ответа

        // Проверяем заголовки ответа
        response.then().body("headers.host", equalTo("postman-echo.com"));
        response.then().body("headers.content-type", containsString("text/plain")); // Учтем наличие charset
    }

}

