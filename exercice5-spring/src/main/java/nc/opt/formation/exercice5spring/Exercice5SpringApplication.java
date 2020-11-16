package nc.opt.formation.exercice5spring;

import nc.opt.formation.exercice5spring.entity.*;
import nc.opt.formation.exercice5spring.repository.AdressRepository;
import nc.opt.formation.exercice5spring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;


@SpringBootApplication
public class Exercice5SpringApplication implements CommandLineRunner {

	@Autowired
	protected ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Exercice5SpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// enregistrer l'event formation springboot
		Address address = new Address();
		address.setName("OPT FORMATION");
		address.setStreet("Xyris");
		address.setZipCode("98 800");

		Event event = new Event();
		event.setTitle("Formation Spring");
		event.setAddress(address);
		event.setDescription("Formation Spring, gradle, jhipster");
		event.setBeginDate(LocalDate.of(2020, 11, 2));
		event.setAllDAys(true);
		address.setEvent(event);

		User user = new User();
		user.setLogin("tchao");
		user.setEmail("gaspard.tchao@sf2i.nc");
		user.setPass("gaspard");
		user.getEvents().add(event);

		event.setUser(user);

		Item item1 = new Item();
		item1.setName("Video proj");
		item1.setCurrentQuantity(1);
		item1.setNeededQuantity(1);
		item1.setEvent(event);

		Item item2 = new Item();
		item2.setName("Barre de son");
		item2.setCurrentQuantity(1);
		item2.setNeededQuantity(1);
		item2.setEvent(event);

		Guest guest1 = new Guest();
		guest1.setName("Raitapo");
		guest1.setEmail("joe.raitapo@sf2i.nc");
		guest1.getEvents().add(event);

		Guest guest2 = new Guest();
		guest2.setName("Tartines");
		guest2.setEmail("kimberlee.tartines@sf2i.nc");
		guest2.setEvents(new HashSet<>(Arrays.asList(event)));
		event.setGuests((Map<String, Guest>) new HashSet(Arrays.asList("joe.raitapo@sf2i.nc", guest1, "kimberlee.tartines@sf2i.nc", guest2)));

		EventRepository repo = context.getBean(EventRepository.class);
		repo.save(event);

	}
}
