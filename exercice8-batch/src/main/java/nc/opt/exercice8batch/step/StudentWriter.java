package nc.opt.exercice8batch.step;

import nc.opt.exercice8batch.dao.StudentDao;
import nc.opt.exercice8batch.model.Student;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class StudentWriter implements ItemWriter<Student> {

    @Autowired
    private StudentDao studentDao;


    @Override
    public void write(List<? extends Student> items) throws Exception {
        Logger.getGlobal().info("Saving lenght = " + items.size());
        studentDao.create(items);
    }
}
