package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.model.UserToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    UserToken findById(int id);
    UserToken findByToken(String token);
    List<UserToken> findByUser(User user);
    void deleteById(int id);
}
