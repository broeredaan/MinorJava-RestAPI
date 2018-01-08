package nl.stenden.zes.service;

import nl.stenden.zes.model.User;
import nl.stenden.zes.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createData() {
        userRepository.save(new User("Bakker", "jbakker@gmail"));
        userRepository.save(new User("Molenaar", "wmolenaar@gmail"));
        userRepository.save(new User("Kerstens", "kkerstens@yahoo"));
        userRepository.save(new User("Bakker", "hbakkerlive"));
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
