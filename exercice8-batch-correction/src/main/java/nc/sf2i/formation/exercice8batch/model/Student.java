package nc.sf2i.formation.exercice8batch.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Student {
	private Long id;
	private String firstname;
	private String lastname;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	private Integer score;
	
	public Student() {
		id = -1L;
	}

	public Student(Long id, String firstname, String lastname, LocalDate birthDate, Integer score) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birthDate=" + birthDate
				+ ", score=" + score + "]";
	}
	
}
