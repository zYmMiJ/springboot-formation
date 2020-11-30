/**
 * 
 */
package nc.sf2i.formation.exercice9spring.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thankgodukachukwu
 *
 */

public class DepartmentDTO {
	
	private int departmentId;

	private String description;

	private String name;

	public DepartmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentDTO(int departmentId, String description, String name) {
		super();
		this.departmentId = departmentId;
		this.description = description;
		this.name = name;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
