package Journal.example.Journal.project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {
//    CHECKING SYSTEM IS WORKING FINE
    @GetMapping("/hc")
    public String check() {
        return "Ok";
    }
}
