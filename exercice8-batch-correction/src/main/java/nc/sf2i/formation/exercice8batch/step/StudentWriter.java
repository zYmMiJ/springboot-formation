package nc.sf2i.formation.exercice8batch.step;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nc.sf2i.formation.exercice8batch.dao.StudentDao;
import nc.sf2i.formation.exercice8batch.model.Student;

@Component
public class StudentWriter implements ItemWriter<Student> {
	@Autowired
	private StudentDao studentDao;

	@Override
	public void write(List<? extends Student> items) throws Exception {
		Logger.getGlobal().info("Saving length = "+items.size());
		studentDao.create(items);
	}

}
