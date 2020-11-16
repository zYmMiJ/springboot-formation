package nc.opt.formation.exercice5spring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Guest {
    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String name;
    protected String email;

    @ManyToMany(mappedBy = "guests")
    protected Set<Event> events;

    public Guest() {
        events = new HashSet<Event>();
    }
}
