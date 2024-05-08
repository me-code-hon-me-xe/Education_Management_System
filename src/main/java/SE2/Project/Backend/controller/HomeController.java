package SE2.Project.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String home() {
        return "redirect:/admin/listAdmin";
    }


}
