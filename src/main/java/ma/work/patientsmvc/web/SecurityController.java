package ma.work.patientsmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(path = "/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
    @GetMapping(path = "/logout")
    public String logout(){
        return "redirect:/login";
    }
}

