package Journal.example.Journal.project.Controller;

import Journal.example.Journal.project.API_RESPONSE.WeatherResponse;
import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Repository.User_repo;
import Journal.example.Journal.project.Service.User_Service;
import Journal.example.Journal.project.Service.Weather_Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Transactional
public class User_Controller {

    @Autowired
    User_Service us;
    @Autowired
    Weather_Service ws;
    //    WHAT
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    User_repo ur;

    //    WHAT
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Set a known password, like "password123"
        user.setPassword(encoder.encode("password123"));
        ur.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/save")
    public void saveuser(@RequestBody User user) {

        us.SaveNewUser(user);
    }

    @GetMapping("/getall")
    public List<User> getAllUser() {
        return us.findAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return us.finduser(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByUsername(@PathVariable String name) {
        return us.findbyusername(name);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUser(@PathVariable String id) {
        us.deletebyid(id);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteByUsername(@PathVariable String username) {
        us.deletebyusername(username);
    }

    @PutMapping("/update/{pass}")
    public void updatePass(@PathVariable String pass) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String Username = auth.getName();
        User userindb = us.findbyusername(Username);
        userindb.setPassword(pass);
        us.SaveNewUser(userindb);
        System.out.println(userindb.getPassword());


    }

    //    NOT COMPLETED
    @DeleteMapping("/deletejournal/{id}")
    public void deleteJournal(@PathVariable String id) {

    }

    @RequestMapping("/api/protected/resource")
    public ResponseEntity<String> testEndpoint(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);
        return ResponseEntity.ok("Authorized");
    }

    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = ws.getWeather("Delhi");

        return ResponseEntity.ok("today is this" + weather);
    }


}
