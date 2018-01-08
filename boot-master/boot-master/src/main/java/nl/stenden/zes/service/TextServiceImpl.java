package nl.stenden.zes.service;

import nl.stenden.zes.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements Textservice {

    @Override
    public String uppercase(String text) {
        if (text == null) {
            return "";
        }
        return text.toUpperCase();
    }
}
