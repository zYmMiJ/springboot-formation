package nc.sf2i.formation.excercice1spring;

import nc.sf2i.formation.excercice1spring.metier.MessagePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class Excercice1SpringApplication implements CommandLineRunner {

	static Logger ex1Log = Logger.getGlobal();

	@Autowired
	protected ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Excercice1SpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		for (String name: context.getBeanDefinitionNames() ) {
//			ex1Log.info(name + " -> " + context.getBean(name).getClass().getSimpleName());
//		}
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}



}
