package nc.opt.exercice8batch.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Student {
    private Long id;
    private String firstname;
    private String lastname;
    private String birthDate;
    private Integer score;

    public Student(Long id, String firstname, String lastname, String birthDate, Integer score) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.score = score;
    }
}
