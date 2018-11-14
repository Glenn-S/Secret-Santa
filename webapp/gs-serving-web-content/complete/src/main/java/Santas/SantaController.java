package Santas;

import Models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * Created by kurtis on 2018-11-11.
 */
@Controller
public class SantaController {
    private SecretSanta ss = new SecretSanta();
    private ArrayList<Pair> pairs = new ArrayList<Pair>(); // possibly for creating config file


    @GetMapping("/list")
    public String list(Model model) {
        return "list";
    }

    @RequestMapping(value="/list", params="submitPair", method=RequestMethod.POST)
    public String formPost(@RequestParam("partnerA") String partnerA,
                           @RequestParam("partnerB") String partnerB,
                           ModelMap model) {
        // add to the ArrayList to create a configuration file
        Pair newPair;
        newPair = !partnerB.equals("") ? new Pair(partnerA, partnerB) : new Pair(partnerA);
        return "list";
    }

    @RequestMapping(value="/list", params="generateConfig", method=RequestMethod.POST)
    public String createConfigFile() {
        System.out.println("Generating Config File");
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

        // perhaps need to limit it so that once somebody has picked they can't look again

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

