package nc.opt.formation.exercice5spring.repository;

import nc.opt.formation.exercice5spring.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
