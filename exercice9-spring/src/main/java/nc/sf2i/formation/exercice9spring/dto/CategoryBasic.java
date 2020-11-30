package nc.sf2i.formation.exercice9spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryBasic {

	private int categoryId;

	private int departmentId;

	private String name;

	public CategoryBasic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryBasic(int categoryId, int departmentId, String name) {
		super();
		this.categoryId = categoryId;
		this.departmentId = departmentId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}

