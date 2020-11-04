package nc.sf2i.formation.exercice3spring.web;

import nc.sf2i.formation.exercice3spring.model.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//  Singleton qui simule le repository de Participants
public enum ParticipantRegistration {
    INSTANCE;
    private static int counter = 0;

    protected List<Participant> participants;

    ParticipantRegistration() {
        participants = new ArrayList<Participant>();
    }

    public void addParticipant(Participant p) {
        p.setRegistrationNb(Integer.toString(counter));
        counter ++;
        participants.add(p);
    }

    public String update(Participant p) {
//        participants.stream().filter(part -> p.getRegistrationNb().equals(part.getRegistrationNb())).findAny()
        for (Participant part: participants) {
            if (p.getRegistrationNb().equals(part.getRegistrationNb())) {
                part.setAge(p.getAge());
                part.setName(p.getName());
            }
        }
        return p.getRegistrationNb();
    }

    public String deleteParticipant(String registrationNb) {
        participants = participants.stream().filter(p -> registrationNb.equals(p.getRegistrationNb()) == false).collect(Collectors.toList());
        return registrationNb;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public Participant getParticipants(String registrationNb) {
        return participants.stream().filter(p -> registrationNb.equals(p.getRegistrationNb())).findFirst().orElse(null);
    }
}
