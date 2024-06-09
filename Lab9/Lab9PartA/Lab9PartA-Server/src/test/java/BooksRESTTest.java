import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mvc.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

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
        Book book = new Book("748282", "Frank Brown", "Keep On", 100.99F);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/748282")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("748282"))
                .body("author",equalTo("Frank Brown"))
                .body("title",equalTo("Keep On"))
                .body("price",equalTo(100.99F));
        //cleanup
        given()
                .when()
                .delete("books/books");
    }

    @Test
    public void testDeleteBook() {
        // add the book to be deleted book
        Book book = new Book("438943","Mary Jones", "Strive", 199.99F);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .when()
                .delete("books/438943");

        given()
                .when()
                .get("books/438943")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("Book with isbn= 438943 is not available"));
    }

    @Test
    public void testAddBook() {
        // add the book
        Book book = new Book("438943","Mary Jones", "Strive", 199.99F);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("books/438943")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("438943"))
                .body("author",equalTo("Mary Jones"))
                .body("title",equalTo("Strive"))
                .body("price",equalTo(199.99F));
        //cleanup
        given()
                .when()
                .delete("books/438943");
    }

    @Test
    public void testUpdateBook() {
        // add the book
        Book book = new Book("438943","Mary Jones", "Strive", 199.99F);
        Book updateBook = new Book("438943","Mary Jonson", "Strive", 199.99F);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        //update book
        given()
                .contentType("application/json")
                .body(updateBook)
                .when().put("/books/"+updateBook.getIsbn()).then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("books/438943")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("438943"))
                .body("author",equalTo("Mary Jonson"))
                .body("title",equalTo("Strive"))
                .body("price",equalTo(199.99F));
        //cleanup
        given()
                .when()
                .delete("books/438943");
    }

    @Test
    public void testGetAllBooks() {
        // add the books
        Book book = new Book("748282", "Frank Brown", "Keep On", 100.99F);
        Book book2 = new Book("438943","Mary Jones", "Strive", 199.99F);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);

        // get all books and verify
        given()
                .when()
                .get("books")
                .then()
                .statusCode(200)
                .and()
                .body("books.isbn", hasItems("748282", "438943"))
                .body("books.author",hasItems("Frank Brown", "Mary Jones"))
                .body("books.title",hasItems("Keep On", "Strive"))
                .body("books.price",hasItems(100.99F, 199.99F));
        //cleanup
        given()
                .when()
                .delete("books/748282");
        given()
                .when()
                .delete("books/438943");
    }

}
