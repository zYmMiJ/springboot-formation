package nc.opt.exercice8batch.dao;

import nc.opt.exercice8batch.model.Student;

import java.util.List;

public interface StudentDao {
    public int[] create(List<? extends Student> students);
    public List<Student> searchAll();
}
