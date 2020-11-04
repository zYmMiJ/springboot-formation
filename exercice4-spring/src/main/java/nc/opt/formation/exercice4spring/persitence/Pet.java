package nc.opt.formation.exercice4spring.persitence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Pet {
    @Id
    protected Integer id;
    protected String name;
    protected String species;
}
