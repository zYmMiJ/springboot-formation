package nc.opt.exercice8batch.dao.impl;

import nc.opt.exercice8batch.dao.StudentDao;
import nc.opt.exercice8batch.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String createSQL = "insert into student (id, first_name, last_name, score) values (?, ?, ?, ?)";
        // il ne faut pas passer les ordre directement mais créer des batchs
        BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Student st = students.get(i);
                ps.setLong(1, st.getId());
                ps.setString(2, st.getFirstname());
                ps.setString(3, st.getLastname());
                ps.setDouble(4, st.getScore());
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
        String searchSql = "select * from student";
        List<Map<String, Object>> results = super.getJdbcTemplate().queryForList(searchSql);
        ArrayList<Student> students = new ArrayList();
        String birthDate = LocalDate.now().toString();
        for (Map<String, Object> map: results) {
            Long id = (Long) map.get("id");
            String firstname = (String) map.get("first_name");
            String lastname = (String) map.get("last_name");
            Integer score = (Integer) map.get("score");
            Student st = new Student(id, firstname, lastname, birthDate, score);
        }
        return null;
    }
}
