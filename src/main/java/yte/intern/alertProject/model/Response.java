package yte.intern.alertProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Response {

    @Id
    @GeneratedValue
    private Long id;

    private Long responseCode;
    private Long responseTime;
    private LocalDateTime timeStamp;

}
