package yte.intern.spring_data.services;

import org.springframework.stereotype.Service;
import yte.intern.spring_data.model.Book;
import yte.intern.spring_data.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(final Book book){
        return bookRepository.save(book);
    }

}
