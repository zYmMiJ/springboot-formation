package nc.sf2i.formation.excercice1spring.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
public class MessagePrinter {

    @Resource(name = "messageService2")
    protected MessageService service;

//    @Autowired
//    public void setService(MessageService service) {
//        this.service = service;
//    }

    public void printMessage() {
        Logger.getGlobal().info(service.getMessage());
    }
}
