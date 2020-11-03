package nc.sf2i.formation.excercice1spring.metier;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MessageServiceImpl implements MessageService {
    protected DateTimeFormatter formatter;

    public MessageServiceImpl(DateTimeFormatter dtf) {
        formatter = dtf;
    }

    // retourne la date du jour en String
    @Override
    public String getMessage() {
        LocalDate today = LocalDate.now();

        return formatter.format(today);
    }
}
