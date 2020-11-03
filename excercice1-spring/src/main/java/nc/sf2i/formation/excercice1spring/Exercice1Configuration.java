package nc.sf2i.formation.excercice1spring;

import nc.sf2i.formation.excercice1spring.metier.MessageService;
import nc.sf2i.formation.excercice1spring.metier.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class Exercice1Configuration {

    @Bean(name = "messageService1")
    public MessageService getMessageService1() {
        return new MessageServiceImpl(DateTimeFormatter.ISO_DATE);
    }

    @Bean(name = "messageService2")
    public MessageService getMessageService2() {
        return new MessageServiceImpl(DateTimeFormatter.ISO_WEEK_DATE);
    }


}
