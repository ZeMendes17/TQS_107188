package pt.ua.deti.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.deti.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pt.ua.deti.backend.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;   

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // methods
    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        log.info("POST /user");
        return userService.save(user);
    }
    
    @GetMapping("/user")
    public User getUser(@RequestParam String email) {
        log.info("GET /user?email=" + email);
        return userService.getUserByEmail(email);
    }
    
}
