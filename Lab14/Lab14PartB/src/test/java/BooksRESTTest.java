import books.domain.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testDeleteOneBook() {
        // add the book to be deleted
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test deleting the book
        given()
                .when()
                .delete("books/878")
                .then().statusCode(204);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("errorMessage", equalTo("Book with isbn= 878 is not available"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testAddBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testUpdateBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        Book bookUpdate = new Book("878","Book 1234", 19.95, "Joe Smithon");
        given()
                .contentType("application/json")
                .body(bookUpdate)
                .when().put("/books/878").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 1234"))
                .body("price",equalTo(19.95f))
                .body("author",equalTo("Joe Smithon"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testSearchBooksHasBooks() {
        // add the books to be fetched
        Book book = new Book("876","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        Book book1 = new Book("877","Book 1234", 19.95, "Joe Smithon");
        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books").then()
                .statusCode(200);
        Book book2 = new Book("878","Book 12345", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);
        // test for non-empty response
        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("books", not(empty()));
        //cleanup
        given()
                .when()
                .delete("books/876");
        given()
                .when()
                .delete("books/877");
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testSearchBooksHasAuthor() {
        // add the books to be fetched
        Book book = new Book("876","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        Book book1 = new Book("877","Book 1234", 19.95, "Joe Smithon");
        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books").then()
                .statusCode(200);
        Book book2 = new Book("878","Book 12345", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);
        // test for all items has correct author
        given()
                .queryParam("author", "\"Joe Smith\"")
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("books", not(empty()))
                .body("books.author", everyItem(equalTo("Joe Smith")));
        //cleanup
        given()
                .when()
                .delete("books/876");
        given()
                .when()
                .delete("books/877");
        given()
                .when()
                .delete("books/878");
    }
}
