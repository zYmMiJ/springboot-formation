package nc.opt.exercice8batch.step;

import nc.opt.exercice8batch.dao.StudentDao;
import nc.opt.exercice8batch.model.Student;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class StudentListener extends JobExecutionListenerSupport {

    @Autowired
    private StudentDao studentDao;

    /** Pour controler que la table student à changer */
    @Override
    public void afterJob(JobExecution jobExecution) {
        List<Student> students = studentDao.searchAll();
        Logger.getGlobal().info("now student count = " + students.size());
        for (Student student : students) {
            Logger.getGlobal().info(student.toString());
        }
        ExecutionContext ec = jobExecution.getExecutionContext();
        if (ec.containsKey("count")) {
            int val = ec.getInt("count");
            ec.put("count", val + students.size());
        } else {
            ec.put("count", students.size());
        }
    }
}
