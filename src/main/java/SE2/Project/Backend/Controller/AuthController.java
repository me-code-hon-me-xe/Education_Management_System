package SE2.Project.Backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String login() {
        return "LoginScreen";
    }
    @GetMapping("/home")
    public String home(){
        return "teacher-grade-management";
    }
}