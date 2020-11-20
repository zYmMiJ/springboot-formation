package nc.sf2i.formation.exercice8batch;

import nc.sf2i.formation.exercice8batch.model.Student;
import nc.sf2i.formation.exercice8batch.step.StudentProcessor;
import nc.sf2i.formation.exercice8batch.step.StudentReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.processing.Processor;
import java.time.LocalDate;
import java.util.logging.Logger;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class Exercice8BatchApplicationTests {

	static Logger testLog = Logger.getLogger("AAA");

	protected StudentReader reader;
	protected StudentProcessor processor;

	@BeforeEach
	public void step() {
		reader = new StudentReader("data-test.csv");
		System.out.println(reader);
		processor = new StudentProcessor();
	}

	@ParameterizedTest
	@CsvSource( value = {"5,Laurent,Outant,04/02/1994,91;"}, delimiter = ';')
	public void testReader(String input, String expected) throws Exception {
		try {
			Resource res = new ByteArrayResource(input.getBytes());
			reader.setResource(res);
			reader.open(MetaDataInstanceFactory.createStepExecution().getExecutionContext());
			Student student = reader.read();
			testLog.info(res.toString());
			Logger.getGlobal().info("student " + student);
			assertNotNull(student);
			assertEquals(new Integer(91), student.getScore());
			reader.close();
		} catch (Exception e) {
			testLog.severe(e.getMessage());
		}
	}

	@Test
	public void testProcessor() {
		Student student = new Student(12L, "Leo", "Cafe", LocalDate.of(2020, 11, 11), 40);
		try {
			Student res = processor.process(student);
			assertNull(res);
		} catch (Exception e) {
			testLog.severe(e.getMessage());
		}
	}


}
