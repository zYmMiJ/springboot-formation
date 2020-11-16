package nc.opt.formation.exercice5spring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Setter
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String name;
    @Column(name = "current_quantity")
    protected Integer currentQuantity;
    @Column(name = "needed_quantity")
    protected Integer neededQuantity;
    @ManyToOne
    @JoinColumn(name = "event")
    protected Event event;


}
