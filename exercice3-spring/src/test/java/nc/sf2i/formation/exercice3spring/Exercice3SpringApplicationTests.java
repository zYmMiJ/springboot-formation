package nc.sf2i.formation.exercice3spring;

import nc.sf2i.formation.exercice3spring.model.Participant;
import nc.sf2i.formation.exercice3spring.web.ParticipantController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class Exercice3SpringApplicationTests {

	@Autowired
	protected ParticipantController controller;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllParticipants() {
		List<Participant> list = controller.getAllParticipants();
		assertTrue("",list.size() >= 0);
	}
}
