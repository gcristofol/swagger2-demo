package nz.skytv.example;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@Api(value = "Books", consumes = "application/json", produces = "application/json")
@SpringBootApplication
public class SwaggerApplication { 

    private static final Logger LOG = LoggerFactory.getLogger(SwaggerApplication.class);

    @Configuration
    @EnableSwagger2
    static class SwaggerConfig {

        @Bean
        public Docket businessApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                     .groupName("Business")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/rest/**"))
                    .build();
        }
        
        @Bean
        public Docket operationsApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                     .groupName("Operations")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/foo/**"))
                    .build();
        }

        private ApiInfo apiInfo() {
            ApiInfo apiInfo = new ApiInfo(
                    "My REST API",
                    "Some custom description of API.",
                    "API TOS",
                    "Terms of service",
                    "myeaddress@company.com",
                    "License of API",
                    "API license URL");
            return apiInfo;
        }
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

     @ApiOperation(value = "Hello world", notes = "Hello World.",  tags = {"foo"})
       @RequestMapping(value = "/foo/bar", method = RequestMethod.GET, produces = "text/plain")
    public String index() {
        return "Hello";
    }
    
    @RequestMapping(value = {"/rest/books"}, method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve the list of books", notes = "Retrieve books.", response = Book.class, tags = {"book","searches"})
    @ApiResponses({
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message
                = "Books retrieved successfully"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message
                = "Something really bad happened")
    })
    List<Book> findBooksByIsbn(final String isbn) {
        LOG.debug("findBy {}", isbn);
        return Arrays.asList(new Book());
    }

    @ApiOperation(value = "Create a book", notes = "Create a book.", response = Book.class, tags = {"book","updates"})
    @ApiResponses({
        @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message
                = "Book created successfully"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message
                = "Something really bad happened")
    })
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.POST, consumes ="application/json")
    ResponseEntity<Void> createBook(@ApiParam(value = "Book entity", required = true) @RequestBody @Valid final Book book) {
        LOG.debug("create {}", book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a books", notes = "Delete a book.", response = Book.class, tags = {"book"})
    @ApiResponses({
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message
                = "Book deleted successfully"),
        @ApiResponse(code = HttpURLConnection.HTTP_GONE, message
                = "Not found")
    })
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.DELETE)
    void deleteBook(@ApiParam(value = "Book isbn", required = true)
            @RequestParam("isbn") final String isbn) {
        LOG.debug("delete {}", isbn);
    }

    @ApiOperation(value = "Update a book", notes = "Update a book.", response = Book.class, tags = {"book","updates"})
    @ApiResponses({
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message
                = "Book updated successfully"),
        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message
                = "Not found"),
        @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message
                = "Transient Entity")
    })
    @RequestMapping(value = {"/rest/book"}, method = RequestMethod.PATCH)
    void updateBook(@ApiParam(value = "Book entity", required = true) @RequestBody @Valid final Book book) {
        LOG.debug("update {}", book);
    }
}
