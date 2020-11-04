package nc.sf2i.formation.exercice3spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ParticipantRegistrationReply {
    protected String name;
    protected Integer age;
    protected String registrationNb;
    protected String registrationStatus;

    @Override
    public String toString() {
        return "ParticipantRegistrationReply{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", registrationNb='" + registrationNb + '\'' +
                ", registrationStatus='" + registrationStatus + '\'' +
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

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
