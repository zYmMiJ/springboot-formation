package nc.sf2i.formation.exercice8batch.dao;

import java.util.List;

import nc.sf2i.formation.exercice8batch.model.Student;

/** C___S */
public interface StudentDao {
	public int[] create(List<? extends Student> students);
	public List<Student> searchAll();
}
