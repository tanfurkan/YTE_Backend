package yte.intern.spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring_data.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
}
