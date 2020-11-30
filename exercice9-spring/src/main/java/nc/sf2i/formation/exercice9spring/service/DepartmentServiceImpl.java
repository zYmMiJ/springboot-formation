package nc.sf2i.formation.exercice9spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nc.sf2i.formation.exercice9spring.dto.DepartmentDTO;
import nc.sf2i.formation.exercice9spring.model.Department;
import nc.sf2i.formation.exercice9spring.repository.DepartmentRepository;



/**
 * DepartmentServiceImpl for department related handling.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	
  @Autowired
  private DepartmentRepository departmentRepository;

  

	@Override
	public Optional<Department> findByName(String name) {
		
		return departmentRepository.findByName(name);
	}

	@Override
	public Optional<DepartmentDTO> findById(Integer departmentId) {
		
		return departmentRepository.findCustomD(departmentId);
		
		
	}

	@Override
	public List<DepartmentDTO> getAll() {
		
		return departmentRepository.findAllOurDeps();
	}

}
