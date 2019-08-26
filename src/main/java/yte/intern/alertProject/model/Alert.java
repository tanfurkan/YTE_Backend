package yte.intern.alertProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class Alert {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String url;
    private String httpMethod;
    private Long controlPeriod;
    private Long createdAt;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @JoinColumn(name="alertID")
    private Set<Response> responses;

}