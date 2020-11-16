package nc.opt.formation.exercice5spring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    protected Integer id;
    protected String title;
    protected String description;
    @Column(name = "begin_date")
    protected LocalDate beginDate;
    @Column(name = "all_days")
    protected Boolean allDAys;
    @OneToOne(mappedBy = "event", cascade = CascadeType.PERSIST)
    protected Address address;
    @ManyToOne
    @JoinColumn(name = "users")
    protected User user;
    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    protected Set<Item> items;
    @ManyToMany
    @JoinTable(
            name = "guest_event",
            joinColumns = { @JoinColumn(name = "fk_event")},
            inverseJoinColumns = { @JoinColumn(name = "fk_guest") }
    )
    @MapKey(name = "email")
    protected Map<String, Guest> guests;

    public Event() {
        items = new HashSet<Item>();
        guests = new HashMap<String, Guest>();
    }
}
