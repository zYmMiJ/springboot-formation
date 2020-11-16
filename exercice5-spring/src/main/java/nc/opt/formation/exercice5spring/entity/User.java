package nc.opt.formation.exercice5spring.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String login;
    protected String pass;
    protected String email;
    @OneToMany(mappedBy = "user")
    protected Set<Event> events;

    public User() {
        events = new HashSet<Event>();
    }
}
