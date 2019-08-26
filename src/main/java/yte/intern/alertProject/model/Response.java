package yte.intern.alertProject.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class Response {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isSuccessful;
    private Long responseTime;
    private Long timeStamp;

    public Response(boolean isSuccessful, Long responseTime, Long timeStamp) {
        this.isSuccessful = isSuccessful;
        this.responseTime = responseTime;
        this.timeStamp = timeStamp;
    }
}
