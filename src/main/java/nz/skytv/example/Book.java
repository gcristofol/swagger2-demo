package nz.skytv.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gerardc
 */
@ApiModel("Book")
public class Book {

    /**
     * @return the type
     */
    public BookType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(BookType type) {
        this.type = type;
    }

    /**
     * @return the isbn2
     */
    public String getIsbn2() {
        return isbn2;
    }

    /**
     * @param isbn2 the isbn2 to set
     */
    public void setIsbn2(String isbn2) {
        this.isbn2 = isbn2;
    }

    public enum BookType {
        SOFTCOVER, HARDCOVER, DELUXE;
    }

    @ApiModelProperty(value = "The International Standard Book Number (ISBN) book identifier.", required = true)
    @NotNull
    private String isbn;
    
    private String isbn2;

    @ApiModelProperty(value = "The Description.", required = true)
    @Size(min = 2)
    private String description;

    @ApiModelProperty(value = "The binding types can be made to order using our selection of fine materials and customized cover decoration options.")
    private BookType type;

    public Book() {
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
