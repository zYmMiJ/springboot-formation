package nc.sf2i.formation.excercice1spring.rest;

import nc.sf2i.formation.excercice1spring.metier.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PrinterService {

    @Resource(name = "messageService1")
    protected MessageService service;

    @RequestMapping("/message")
    public String printMessage() {
        return service.getMessage();
    }
}
