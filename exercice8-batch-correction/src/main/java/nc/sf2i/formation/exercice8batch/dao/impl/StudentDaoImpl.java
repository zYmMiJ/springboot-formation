package nc.sf2i.formation.exercice8batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import nc.sf2i.formation.exercice8batch.dao.StudentDao;
import nc.sf2i.formation.exercice8batch.model.Student;

@Repository("studentDao")
public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao {
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		super.setDataSource(dataSource);
	}

	@Override
	public int[] create(List<? extends Student> students) {
		String createSQL = "insert into student (id, first_name, last_name, birth_date, score) values (?, ?, ?, ?, ?)";
		// il ne faut passer les ordre directement mais creer des batchs
		BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student st = students.get(i);
				ps.setLong(1, st.getId());
				ps.setString(2, st.getFirstname());
				ps.setString(3, st.getLastname());
				ps.setDate(4, Date.valueOf(st.getBirthDate()));
				ps.setDouble(5, st.getScore());
			}
			
			@Override
			public int getBatchSize() {
				return students.size();
			}
		};
		return super.getJdbcTemplate().batchUpdate(createSQL, bpss);
	}

	@Override
	public List<Student> searchAll() {
		String searchSQL = "select * from student";
		List<Map<String, Object>> results = super.getJdbcTemplate().queryForList(searchSQL);
		ArrayList<Student> students = new ArrayList<>();
		LocalDate birthDate = LocalDate.now();	// TODO a changer
		for (Map<String, Object> map: results) {
			Long id = (Long) map.get("id");
			String firstname = (String) map.get("first_name");
			String lastname = (String) map.get("last_name");
			Integer score = (Integer) map.get("score");
			Student st = new Student(id, firstname, lastname, birthDate, score);
			students.add(st);
		}
		return students;
	}
}
