/******************************************************
 * @author Glenn Skelton
 * @version 1.0
 * @{date}
 *
 *
 ******************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;

public class SecretSanta {
    private String FILE = "tests/config.txt"; // default value
    private ArrayList<Pair> pairs;
    private ArrayList<ArrayList<Pair>> santaPairs = new ArrayList<ArrayList<Pair>>();
    public ArrayList<Pair> finalPairs;

    // constructor
    public SecretSanta() {
        pairs = new ArrayList<Pair>();
        parseConfigFile(FILE);
        generateTables(santaPairs);
        pickRandomPairs();
    }

    public SecretSanta(String filename) {
        FILE = filename == null ? FILE : new String(filename);
        pairs = new ArrayList<Pair>();
        parseConfigFile(FILE);
        generateTables(santaPairs);
        pickRandomPairs();
    }


    /**
     * Purpose: To pick the random name that will be associated with
     * the sant for gift giving.
     */
    public String pickName(String santa) {
        String partner = null;
        for (Pair p : finalPairs) {
            String name = p.getPair(santa);
            if (name != null) partner = name;
            else continue;
        }
        return partner;
    }

    private void pickRandomPairs() {
        Random rand = new Random();
        int index = (int)rand.nextInt(santaPairs.size()-1);
        finalPairs = new ArrayList<Pair>();

        for (Pair p : santaPairs.get(index)) {
            System.out.println(p);
            finalPairs.add(new Pair(p));
        }
    }

    /**
     *
     */
    private void parseConfigFile(String filename) {
        String buffer;
        Scanner in = null;

        try { // safely open the file
            in = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(filename + " could not be found");
            e.printStackTrace();
            return;
        }

        // get the file contents
        while (in.hasNextLine()) {
            buffer = in.nextLine();
            if (buffer.equals("") || buffer.contains("#")) continue; // ignore comments and empty lines
            else {
                // parse the file
                String[] names = buffer.split(" - ");
                // insert into the pairs array ArrayList
                if (names.length == 1) this.pairs.add(new Pair(names[0]));
                else if (names.length == 2) this.pairs.add(new Pair(names[0], names[1]));
                else {
                    System.out.println("Error in parsing " + filename);
                    return;
                }
            }
        }
        if (in != null) { // close the file
            in.close();
        }
    }


    /**
     * Purpose: To generate the possible random outcomes given
     * couples.
     */
    private void generateTables(ArrayList<ArrayList<Pair>> finalPairs) {
        // create sets of all possible and then remove impossible cases
        // set intersect
        //finalPairs.add(new ArrayList<Pair>());
        ArrayList<ArrayList<Pair>> pairings = new ArrayList<ArrayList<Pair>>();
        int totalOutcomes = 0;


        if (this.pairs.size() > 0) {
            // populate the outcomes and save them
            // iterate through each person
            ArrayList<String> names = new ArrayList<String>();
            for (Pair p : pairs) {
                if (p.size() == 1) names.add(p.getPartnerA());
                else if (p.size() == 2) {
                    names.add(p.getPartnerA());
                    names.add(p.getPartnerB());
                }
                totalOutcomes += p.size();
            }


            // generate the pairings
            permute(pairings, names, 0, names.size()-1, 0);

            Boolean valid = true;
            ArrayList<Pair> temp;

            //System.out.println(pairings.size());
            // filter out the pairings that are not allowed
            for (ArrayList<Pair> pairing : pairings) {
                temp = new ArrayList<Pair>();
                valid = true; // reset

                // each array in the array
                for (Pair pair : pairing) { // check to determine if valid
                    for (Pair p : pairs) {
                        if (pair.compare(p)) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid == false) break;
                    else temp.add(new Pair(pair)); // add into final array
                }

                if (valid == false) continue; // don't add this array
                else {
                    int ttlPairs = finalPairs.size();
                    //System.out.println(ttlPairs);
                    finalPairs.add(new ArrayList<Pair>());
                    for (Pair p : temp) {
                        //System.out.println(p);
                        finalPairs.get(ttlPairs).add(new Pair(p));
                    }
                }
            }

            System.out.println("Final Size " + finalPairs.size());
            // assign pairings to be the final pairings
            return;
        }
        else {
            return;
        }

    }

    /**
     * Inspiration from https://stackoverflow.com/questions/7537791/understanding-recursion-to-generate-permutations
     */
    private ArrayList<String> swap(ArrayList<String> names, int left, int right) {
        ArrayList<String> swapNames = new ArrayList<String>();
        for (String name : names) swapNames.add(new String(name));

        // using the copy, swap the elements
        String temp = swapNames.get(left);
        swapNames.set(left, swapNames.get(right));
        swapNames.set(right, temp);
        return swapNames;
    }

    /**
     * Inspiration from https://stackoverflow.com/questions/7537791/understanding-recursion-to-generate-permutations
     */
    private void permute(ArrayList<ArrayList<Pair>> pairings,
                         ArrayList<String> names,
                         int left, int right, int depth) {

        if (left == right) {
            // add the new pairings
            int index = pairings.size();
            pairings.add(new ArrayList<Pair>());

            for (int i = 0; i < names.size(); i++) {
                if (i == names.size()-1) {
                    // pair the last person with the first person
                    pairings.get(index).add(new Pair(names.get(i), names.get(0)));
                }
                else {
                    // regular pairings
                    pairings.get(index).add(new Pair(names.get(i), names.get(i+1)));
                }
            }
            return; // it's done
        }
        else {
            for (int i = left; i <= right; i++) {
                names = swap(names, left, right);
                permute(pairings, names, left + 1, right, depth+1);
                names = swap(names, left, i);
            }
        }
    }

    /**
     * Purpose: To print out the pairs stored in the table.
     */
    public void printPairs(ArrayList<Pair> ps) {
        for (Pair p : ps) System.out.println(p);
    }

}
