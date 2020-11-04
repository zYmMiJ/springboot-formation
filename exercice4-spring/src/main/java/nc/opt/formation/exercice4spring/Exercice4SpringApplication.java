package nc.opt.formation.exercice4spring;

import nc.opt.formation.exercice4spring.persitence.NewPetRepository;
import nc.opt.formation.exercice4spring.persitence.Pet;
import nc.opt.formation.exercice4spring.persitence.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class Exercice4SpringApplication implements CommandLineRunner {

	@Autowired
	protected ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Exercice4SpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		NewPetRepository repo = context.getBean(NewPetRepository.class);

		Logger.getGlobal().info("Il y a "+repo.count() + " chien(s).");

		String chien = "zYmMiJ";
		Logger.getGlobal().info("On cherche "+chien+".");
		for (Pet p: repo.findByName(chien)) {
			Logger.getGlobal().info(p.toString());
		}

		Logger.getGlobal().info("On affiche tous les chiens.");
		for (Pet p: repo.findAll()) {
			Logger.getGlobal().info(p.toString());
		}
	}
}
