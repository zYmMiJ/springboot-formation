package nc.sf2i.formation.exercice3spring.web;

import nc.sf2i.formation.exercice3spring.model.Participant;
import nc.sf2i.formation.exercice3spring.model.ParticipantRegistrationReply;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @RequestMapping(path = "/register/participant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ParticipantRegistrationReply registrationParticipant(@RequestBody Participant p) {
        ParticipantRegistration.INSTANCE.addParticipant(p);
        ParticipantRegistrationReply prr = new ParticipantRegistrationReply();
        prr.setAge(p.getAge());
        prr.setName(p.getName());
        prr.setRegistrationNb(p.getRegistrationNb());
        prr.setRegistrationStatus("SUCCESS");
        return prr;
    }

    @RequestMapping(path = "/unregister/participant/{regNb}", method = RequestMethod.DELETE)
    public ParticipantRegistrationReply unregister(@PathVariable(name = "regNb") String registrationNb) {
       Participant p = ParticipantRegistration.INSTANCE.getParticipants(registrationNb);
       ParticipantRegistrationReply prr = new ParticipantRegistrationReply();
       if ( p != null ) {
           ParticipantRegistration.INSTANCE.deleteParticipant(registrationNb);
           prr.setAge(p.getAge());
           prr.setName(p.getName());
           prr.setRegistrationNb(p.getRegistrationNb());
           prr.setRegistrationNb("CANCELED");
       } else {
           prr.setRegistrationNb("UNKNOWN");
       }
        return prr;
    }

    @RequestMapping( path= "/register/participant/{regNb}", method = RequestMethod.PUT )
    public ParticipantRegistrationReply updateRegister(@RequestBody Participant p, @PathVariable(name="regNb") String registrationNb) {
        ParticipantRegistration.INSTANCE.update(p);

        ParticipantRegistrationReply prr = new ParticipantRegistrationReply();

        prr.setAge(p.getAge());
        prr.setName(p.getName());
        prr.setRegistrationNb(registrationNb);
        prr.setRegistrationStatus("UPDATED");

        return prr;
    }
}
