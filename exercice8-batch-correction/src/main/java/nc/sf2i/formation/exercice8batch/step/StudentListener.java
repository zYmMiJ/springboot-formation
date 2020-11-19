package nc.sf2i.formation.exercice8batch.step;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nc.sf2i.formation.exercice8batch.dao.StudentDao;
import nc.sf2i.formation.exercice8batch.model.Student;

@Component
public class StudentListener extends JobExecutionListenerSupport {
	@Autowired
	private StudentDao studentDao;
	
	/** Pour controler que la table student changer */
	@Override
	public void afterJob(JobExecution jobExecution) {
		List<Student> students = studentDao.searchAll();
		Logger.getGlobal().info("now student count = "+students.size());
		for (Student student : students) {
			Logger.getGlobal().info(student.toString());
		}
		// ajouter le nombre de students dans le contexte d'execution
		ExecutionContext ec = jobExecution.getExecutionContext();
		if (ec.containsKey("count")) {
			int val = ec.getInt("count");
			ec.put("count", val + students.size());
		} else
			ec.put("count", students.size());
	}
}
