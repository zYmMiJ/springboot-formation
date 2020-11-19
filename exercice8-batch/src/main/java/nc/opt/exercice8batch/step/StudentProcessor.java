package nc.opt.exercice8batch.step;

import nc.opt.exercice8batch.model.Student;
import org.springframework.batch.item.ItemProcessor;

import java.util.Random;
import java.util.logging.Logger;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student item) throws Exception {
        Logger.getGlobal().info("Processing id = "+item.getId());
        if (item.getScore() > 50) {
            // générer un id technique
            Random random = new Random();
            item.setId(random.nextLong());
        }
        return null;
    }
}
