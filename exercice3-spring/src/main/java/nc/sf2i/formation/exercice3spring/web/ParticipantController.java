package nc.sf2i.formation.exercice3spring.web;

import nc.sf2i.formation.exercice3spring.model.Participant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParticipantController {

    @RequestMapping(path = "/formation/participants")
    public List<Participant> getAllParticipants() {
        return ParticipantRegistration.INSTANCE.getParticipants();
    }
}
