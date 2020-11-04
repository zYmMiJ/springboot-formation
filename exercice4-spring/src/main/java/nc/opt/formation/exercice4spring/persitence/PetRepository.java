package nc.opt.formation.exercice4spring.persitence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PetRepository {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public Pet findById(Integer id) {
        final String sql = "select * from pet where id = :id";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("id", id);
        RowMapper<Pet> rowMapper = new BeanPropertyRowMapper<Pet>(Pet.class);
        return jdbcTemplate.queryForObject(sql, paramMap, rowMapper);
    }

    public Iterable<Pet> findAll() {
        final String sql = "select * from pet";
        RowMapper<Pet> rowMapper = new BeanPropertyRowMapper<Pet>(Pet.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Integer save(Pet pet) {
        final String sql = "insert into pet (name, species) values (:name, :species)";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("name", pet.getName());
        paramMap.addValue("species", pet.getSpecies());
        return jdbcTemplate.update(sql, paramMap);
    }

    public Integer delete(Integer id) {
        final String sql = "delete from pet where id = :id";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("id", id);
        return jdbcTemplate.update(sql, paramMap);
    }
}
