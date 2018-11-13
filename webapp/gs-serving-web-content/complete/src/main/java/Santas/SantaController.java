package Santas;

import Models.SecretSanta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurtis on 2018-11-11.
 */
@Controller
public class SantaController {
    private SecretSanta ss = new SecretSanta();

    @GetMapping("/list")
    public String list(Model model) {
        //TODO read from config

        // this could be used to create a new config file
        //model.addAttribute("names", names);
        return "list";
    }

    @GetMapping("/findSanta")
    public String findSanta(@RequestParam(name="name", required=true, defaultValue="test") String name, Model model) {
        String santa = ss.pickName(name);
        // update the web content
        model.addAttribute("name", name);
        model.addAttribute("santa", santa);
        return "santa";
    }

}
