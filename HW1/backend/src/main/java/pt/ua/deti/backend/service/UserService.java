package pt.ua.deti.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.deti.backend.entity.User;

import pt.ua.deti.backend.repository.UserRepo;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // methods
    public User save(User user) {
        log.info("Saving user: " + user);
        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        log.info("Getting user by email: " + email);
        return userRepo.findByEmail(email);
    }

    public User getUserById(Integer id) {
        log.info("Getting user by id: " + id);
        return userRepo.findById(id).orElse(null);
    }
}
