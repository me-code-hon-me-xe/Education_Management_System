package SE2.Project.Backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/signin")
    public String loginPage(){
        return"login";
    }

    @RequestMapping("/loginCheck")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role){
        if(role.equals("Student")){
            return "redirect:/student/dashboard";
        }else if (role.equals("teacher")) {
            return "redirect:/teacher/dashboard";
        } else if (role.equals("admin")) {
            return "redirect:/admin/dashboard";
        } else {
            // Invalid role, redirect back to login page with error message
            return "redirect:/login?error";
        }

    }

}
