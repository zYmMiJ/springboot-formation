package nc.sf2i.formation.exercice9spring.service;

import java.util.List;
import java.util.Optional;

import nc.sf2i.formation.exercice9spring.dto.DepartmentDTO;
import nc.sf2i.formation.exercice9spring.model.Department;





/**
 * DepartmentService interface for all services related to Department.
 */

public interface DepartmentService {
	
	  
	  Optional<Department> findByName(String name);

	  Optional<DepartmentDTO> findById(Integer departmentId);
	  
	  public List<DepartmentDTO> getAll();
	  
	  
}



