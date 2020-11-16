package nc.opt.formation.exercice5spring.repository;

import nc.opt.formation.exercice5spring.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
