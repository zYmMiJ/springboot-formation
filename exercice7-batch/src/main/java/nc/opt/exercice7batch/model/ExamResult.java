package nc.opt.exercice7batch.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
public class ExamResult {
    protected String studentName;
    protected LocalDate birthDay;
    protected Double score;

    public ExamResult () {
        studentName = "unknown";
        birthDay = LocalDate.now();
        score = 0.0;
    }

    @XmlElement(name = "student-name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
    @XmlElement(name = "date-of-birth")
    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @XmlElement(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
