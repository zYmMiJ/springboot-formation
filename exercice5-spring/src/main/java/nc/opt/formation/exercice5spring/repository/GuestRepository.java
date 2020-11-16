package nc.opt.formation.exercice5spring.repository;

import nc.opt.formation.exercice5spring.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
}
