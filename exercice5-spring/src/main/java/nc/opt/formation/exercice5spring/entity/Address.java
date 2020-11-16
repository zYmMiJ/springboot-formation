package nc.opt.formation.exercice5spring.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String name;
    protected String street;
    protected String comments;
    @Column(name = "zip_code")
    protected String zipCode;
    protected String city;
    @OneToOne(mappedBy = "address")
    protected Event event;

}
