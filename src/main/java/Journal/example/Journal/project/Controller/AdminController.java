package Journal.example.Journal.project.Controller;

import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private User_Service us;

    @GetMapping("/all-users")
    public ResponseEntity<?> getALlUsers() {
        List<User> all = us.findAllUsers();
        if (!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/add-admin-user")
    public ResponseEntity<?> addAdminUser(@RequestBody User user) {
        us.SaveAdminUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
