package Santas;

import Models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


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


    /* --------------------------- GET/POST santa.html ------------------------- */

    @GetMapping("/santa")
    public String santaFormGet(Model model) {
        String santaPick = "";
        model.addAttribute("santaPick", santaPick);
        return "santa"; // get request to return the santa.html page
    }

    @PostMapping("/santa")
    public String santaFormPost(@RequestParam("santaName") String santa, ModelMap model) {
        String santaPick = ss.pickName(santa);

        // update the variable in the santa page
        if (santaPick.equals("Name not found. Please check spelling")) {
            model.put("santaPick", santaPick);
        }
        else {
            model.put("santaPick", "You picked " + santaPick + "!");
        }
        return "santa"; // open the page back up again
    }
}

