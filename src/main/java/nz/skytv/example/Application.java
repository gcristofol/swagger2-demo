package nz.skytv.example;

import javax.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/rest/foo", method = RequestMethod.GET, produces = "text/plain")
    public String index() {
        return "Hello";
    }

    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.GET)
    Book findBookByIsbn(final String isbn) {
        return new Book();
    }
    
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.POST)
    void createBook(@Valid final Book book) {
    }
    
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.DELETE)
    void deleteBook(final String isbn) {
    }
    
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.PATCH)
    void updateBook(@Valid final Book book) {
    }    
    
}
