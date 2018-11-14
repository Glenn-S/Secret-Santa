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
    private SecretSanta ss; // session copy of SecretSanta so that pairings stay the same
    private ArrayList<Pair> pairings; // storage for pairings from list page


    /* ------------------------- GET/POST list.html ---------------------------- */

    @RequestMapping("/list")
    public String list(Model model) {
        // start blank list everytime this page is loaded
        // if back button from another page is pressed, this information remains though
        pairings = new ArrayList<Pair>();
        System.out.println("size=" + pairings.size());
        return "list";
    }

    @RequestMapping(value="/listEntry", params="submit", method=RequestMethod.POST)
    public String listEntry(@RequestParam("partnerA") String partnerA,
                            @RequestParam("partnerB") String partnerB,
                            ModelMap model) {
        // add to the ArrayList to create a configuration file
        Pair newPair = !partnerB.equals("") ? new Pair(partnerA, partnerB) : new Pair(partnerA);
        pairings.add(newPair);

        //System.out.println(newPair); // for test purposes
        System.out.println("size=" + pairings.size());
        return "list";
    }

    @RequestMapping(value="/clearEntries", params="clear", method=RequestMethod.POST)
    public String clearEntries() {

        pairings = new ArrayList<Pair>();
        System.out.println("size=" + pairings.size());
        return "list";
    }


    /* ------------------------- GET/POST santa.html ---------------------------- */

    @GetMapping("/santa")
    public String santaFormGet(ModelMap model) {
        //System.out.println("size=" + pairings.size());

        // read in the pairings entered by the user and generate pairings
        ss = new SecretSanta(pairings);

        model.addAttribute("santaPick", "");
        return "santa"; // get request to return the santa.html page
    }

    @PostMapping("/santa")
    public String santaFormPost(@RequestParam("santaName") String santa, ModelMap model) {
        String santaPick = ss.pickName(santa);

        // perhaps need to limit it so that once somebody has picked they can't look again

        // update the variable in the santa page
        if (santaPick.equals(ss.errMsg1) || santaPick.equals(ss.errMsg2)) {
            model.put("santaPick", santaPick);
        }
        else {
            model.put("santaPick", "You picked " + santaPick + "!");
        }
        return "santa"; // open the page back up again
    }

    @RequestMapping(value="/goBack", params="returnToList", method=RequestMethod.GET)
    public String goBack() {
        return "list"; // return to the list page and clear the list
    }

}

