package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(int id);
    List<User> findByName(String name);
    User findByEmail(String email);
    void deleteById(int id);
}
