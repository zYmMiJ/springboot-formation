package nc.opt.formation.exercice5spring.repository;

import nc.opt.formation.exercice5spring.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
