package nc.sf2i.formation.exercice8batch.step;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.batch.item.ItemProcessor;

import nc.sf2i.formation.exercice8batch.model.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student item) throws Exception {
		Logger.getGlobal().info("Processing id = "+item.getId());
		if (item.getScore() > 50) {
			// generer un id technique
			Random random = new Random();
			item.setId(random.nextLong());
			return item;
		}
		return null;
	}

}
