package Journal.example.Journal.project.Service;

import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Repository.User_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Service
@Slf4j
public class User_Service {
    @Autowired
    User_repo ur;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public boolean SaveNewUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            ur.save(user);
            return true;
        } catch (Exception e) {
            log.error("error occured ", e);
            return false;
        }

    }

    public void saveuser(User user) {
        ur.save(user);
    }

    public void SaveAdminUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("Admin", "USER   "));
        ur.save(user);
    }

    public Optional<User> finduser(String id) {
        return ur.findById(id);
    }

    public User findbyusername(String username) {
        return ur.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return ur.findAll();
    }


    public void deletebyusername(String username) {
        ur.deleteByUsername(username);
    }

    public void deletebyid(String id) {
        ur.deleteById(id);
    }


}
