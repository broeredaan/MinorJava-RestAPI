package nl.stenden.zes.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class StendenListener {

    @JmsListener(destination = "stenden6")
    public void receiveMessage(String message) {

        System.out.println(message);
    }
}
