package nc.opt.formation.exercice4spring.persitence;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NewPetRepository extends CrudRepository<Pet, Integer> {
    @Query(value = "select * from pet where name = :name")
    public Iterable<Pet> findByName(@Param("name") String name);
}
