package yte.intern.spring_data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String name;
    private Long pageCount ;
    private LocalDateTime publishDate;
    private String isbnNUmber;
    @ManyToOne
    @JoinColumn
    private Student student;

}
