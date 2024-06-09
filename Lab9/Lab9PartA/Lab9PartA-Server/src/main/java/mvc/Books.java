package mvc;

import java.util.Collection;
import java.util.List;

public class Books {
    private Collection<Book> books;

    public Books(Collection<Book> books) {
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void filterByAuthor(String author) {
        if(author.isBlank() || author.isEmpty()) return;
        books = books.stream().filter(b -> b.getAuthor().equals(author)).toList();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
