package nl.stenden.zes.service;

import nl.stenden.zes.model.User;

import java.util.List;

public interface UserService {

    void createData();
    List<User> getUsersByName(String name);
    User getUserByEmail(String email);
}
