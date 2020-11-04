package nc.sf2i.formation.exercice3spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Participant {
    protected String name;
    protected Integer age;
    protected String registrationNb;

    public Participant() {

    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", registrationNb='" + registrationNb + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegistrationNb() {
        return registrationNb;
    }

    public void setRegistrationNb(String registrationNb) {
        this.registrationNb = registrationNb;
    }


}
