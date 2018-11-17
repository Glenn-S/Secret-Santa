package Santas;

import Models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


/**
 * Created by kurtis on 2018-11-11.
 * Modified by Glenn Skelton: 14-11-2018
 *
 * the system will print the error message if there aren't three singles in the list
 * or at least 2 pairs or a pair and 2 singles. (this is expected behaviour).
 */
@Controller
public class SantaController {
    private SecretSanta ss; // session copy of SecretSanta so that pairings stay the same
    private ArrayList<Pair> pairings; // storage for pairings from list page
    private Logger logger;

    // ?is there a way to make this "global" access for if we split list into its own controller?

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(SantaController.class);
        pairings = new ArrayList<>();

        logger.info("Santa Controller Initializing");
    }

    /* ------------------------- GET/POST list.html ---------------------------- */

    @RequestMapping("/list")
    public String list(ModelMap model) {
        // start blank list everytime this page is loaded
        // if back button from another page is pressed, this information remains though

        // The list page should list the current users
        model.addAttribute("names", getCurrentList());
        model.addAttribute("errorMessage", ""); // for conveying error messages
        return "list";
    }

    @RequestMapping(value="/listEntry", params="submit", method=RequestMethod.POST)
    public ModelAndView listEntry(@RequestParam("partnerA") String partnerA,
                            @RequestParam("partnerB") String partnerB,
                            ModelMap model) {
        // add to the ArrayList to create a configuration file
        Pair newPair = !partnerB.equals("") ? new Pair(partnerA, partnerB) : new Pair(partnerA);
        String retMsg = "";

        //TODO move messaging to client side or to log
        // need to check and make sure no duplicates are being added
        for (Pair p : pairings) {
            if (p.exists(newPair)) {
                // if person has already been entered don't add again
                model.put("message", "A name you entered already exists in the database");
                //return "list"; // don't add the pair since one of the names already exists
            }
        }
        // give user confirmation message
        if (newPair.size() == 1) {
            retMsg = newPair.getPartnerA() + " has been added!";
            pairings.add(newPair);
        } else if (newPair.size() == 2) {
            retMsg = newPair.getPartnerA() + " and " + newPair.getPartnerB() + " have been added!";
            pairings.add(newPair);
        } else retMsg = "Error in adding pair, please try again";
        model.put("message", retMsg); // print out user message


        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value="/clearEntries", params="clear", method=RequestMethod.POST)
    public String clearEntries() {
        pairings.clear();
        logger.info("All entries cleard.");
        return "list";
    }


    /* ------------------------- GET/POST santa.html ---------------------------- */

    @GetMapping("/santa")
    public String santaFormGet(ModelMap model) {
        // read in the pairings entered by the user and generate pairings
        ss = new SecretSanta(pairings);

        model.addAttribute("pairings", ss.getFinalPairs());
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

    //TODO I don't know what the point of this is, there is a back button in the browser?
    @RequestMapping(value="/goBack", params="returnToList", method=RequestMethod.GET)
    public String goBack() {
        return "list"; // return to the list page and clear the list
    }

    private ArrayList<String> getCurrentList() {
        ArrayList<String> persons = new ArrayList<>();
        for (Pair pair : pairings) {
            if (pair.hasPartnerA()) {
                persons.add(pair.getPartnerA());
            }
            if (pair.hasPartnerB()) {
                persons.add(pair.getPartnerB());
            }
        }
        return persons;
    }
}

