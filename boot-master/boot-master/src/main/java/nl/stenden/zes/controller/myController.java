package nl.stenden.zes.controller;

import nl.stenden.zes.model.User;
import nl.stenden.zes.service.Textservice;
import nl.stenden.zes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;


@RestController
@RequestMapping("api")
public class myController {

    private Textservice textservice;
    private UserService userService;
    private JmsTemplate jmsTemplate;

    public myController(@Autowired Textservice ts, UserService us, JmsTemplate jt) {
        this.textservice = ts;
        this.userService = us;
        this.jmsTemplate = jt;
    }

    @RequestMapping(method = RequestMethod.GET, path = "health")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Operational");
    }

    @RequestMapping(path = "echo")
    public ResponseEntity<String> echo(@RequestParam(value = "msg", required = false) String text) {
        String value = (text != null ? text : "Dit is default tekst");
        String result = textservice.uppercase(value);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST, path = "users")
    public ResponseEntity createData() {
        userService.createData();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "users/{name}")
    public ResponseEntity<List<User>> getByName(@PathVariable String name) {
        List<User> users = userService.getUsersByName(name);
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            for (User u : users) {
                jmsTemplate.convertAndSend("stenden6", u.getEmail());
            }
            return ResponseEntity.ok(users);
        }
    }
}
