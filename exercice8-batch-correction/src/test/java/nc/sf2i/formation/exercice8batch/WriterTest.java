package nc.sf2i.formation.exercice8batch;

import nc.sf2i.formation.exercice8batch.dao.StudentDao;
import nc.sf2i.formation.exercice8batch.model.Student;
import nc.sf2i.formation.exercice8batch.step.StudentWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WriterTest {

    static Logger testLog = Logger.getLogger("Writer.Test");

    @Autowired
    private StudentWriter writer;
    @Autowired
    private StudentDao dao;
    private List<Student> students;

    @BeforeEach
    public void setup() {
        students = new ArrayList<Student>() {{
            add(new Student(13L, "Gerard", "Menvussa", LocalDate.of(1999, 11, 22), 60));
            add(new Student(14L, "Marie", "Golotte", LocalDate.of(1989, 4, 4), 70));
        }};
    }
    @Test
    public void testWrite() {
        try {
            List<Student> before = dao.searchAll();
            writer.write(students);
            List<Student> after = dao.searchAll();
            assertEquals(after.size()-before.size(), students.size());
        } catch (Exception e) {
            testLog.severe(e.getMessage());
        }

    }
}
