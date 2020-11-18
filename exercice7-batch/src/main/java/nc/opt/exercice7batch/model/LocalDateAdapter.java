package nc.opt.exercice7batch.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private DateTimeFormatter formatter;

    public LocalDateAdapter() {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        Logger.getGlobal().info("LocalDateAdapter: "+v);
        return LocalDate.parse(v, formatter);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        Logger.getGlobal().info("LocalDateAdapter: "+v.toString());
        return formatter.format(v);
    }
}
