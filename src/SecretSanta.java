/******************************************************
 * @author Glenn Skelton
 * @version 1.0
 * Last modified: @{date}
 *
 * Purpose: A Secret Santa pairings generator which
 * creates the unique pairings for a group of any size
 * while taking into account the pairings of couples to
 * ensure that they do not end up with each other.
 * HAPPY HOLIDAYS!
 ******************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;



public class SecretSanta {
    private String FILE = "tests/config.txt"; // default value
    private ArrayList<Pair> pairs; // initial users storage
    private ArrayList<ArrayList<Pair>> santaPairs = new ArrayList<ArrayList<Pair>>();
    private ArrayList<Pair> finalPairs; // final secret santa pairings


    /*********************** CONSTRUCTORS **********************/
    public SecretSanta() {
        // use default configuration file name
        pairs = new ArrayList<Pair>();

        // instantiate the tables and pick the random set
        parseConfigFile(FILE);
        generateTables(santaPairs);
        pickRandomPairs();
    }

    public SecretSanta(String filename) {
        FILE = filename == null ? FILE : new String(filename);
        pairs = new ArrayList<Pair>();

        // instantiate the tables and pick the random set
        parseConfigFile(FILE);
        generateTables(santaPairs);
        pickRandomPairs();
    }


    /******************** HELPER METHODS ***********************/

    /**
     * Purpose: To pick the random name that will be associated with
     * the santa for gift giving.
     * @return String - the name of the partner for this santa.
     */
    public String pickName(String santa) {
        String partner = null;
        for (Pair p : finalPairs) {
            String name = p.getPair(santa);
            if (name != null) partner = name;
            else continue;
        }
        if (partner == null) partner = "Name not found. Please check spelling";
        return partner;
    }

    /**
     * Purpose: To print out the pairs stored in the table passed in.
     * @return void
     */
    public void printPairs(ArrayList<Pair> ps) {
        for (Pair p : ps) System.out.println(p);
    }

    /**
     * Purpose: To return the final pairs array.
     * @return copyPairs - an array list of Pairs.
     */
    public ArrayList<Pair> getFinalPairs() {
        ArrayList<Pair> copyPairs = new ArrayList<Pair>();
        for (Pair p : this.finalPairs) copyPairs.add(new Pair(p));
        return copyPairs;
    }

    /**
     * Purpose: To generate a configuration file based on the
     * names passed in. Used for saving state or creating config
     * file from GUI to create pairings.
     */
    public void createConfigFile(ArrayList<Pair> inputPairs, String filename) {
        PrintWriter outFile = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            File file = new File("tests/" + filename);
            file.setReadable(false, false);
            file.setWritable(true, true);
            file.setExecutable(false, false);

            outFile = new PrintWriter(file);
            outFile.println("# " + filename + " " + df.format(now) + "\n");
            for (Pair p : inputPairs)
                outFile.println(p.getPartnerA() + " - " + p.getPartnerB());
        } catch (IOException e) {
            System.out.println("Error with opening " + filename);
        } finally {
            if (outFile != null) {
                outFile.close();
            }
        }
        return;
    }

    /**
     * Purpose: To randomly pick a set of pairings and store import junit.framework.TestCase;
     * in the finalPairs array for use by pickName().
     * @return void
     */
    private void pickRandomPairs() {
        Random rand = new Random();
        int index = (int)rand.nextInt(santaPairs.size()-1);
        finalPairs = new ArrayList<Pair>();

        for (Pair p : santaPairs.get(index)) {
            //System.out.println(p);
            finalPairs.add(new Pair(p));
        }
    }

    /**
     * Purpose: To parse a configuration file and extract and
     * store the Secret Santa participants.
     * @return void
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
     * @return void
     */
    private void generateTables(ArrayList<ArrayList<Pair>> finalPairs) {
        // temporary storage array for all possible pairings
        ArrayList<ArrayList<Pair>> pairings = new ArrayList<ArrayList<Pair>>();
        Boolean valid = true; // for determining if a set is valid to keep
        ArrayList<Pair> temp;

        if (this.pairs.size() > 0) {
            // iterate through each person to populate the outcomes and save them
            ArrayList<String> names = new ArrayList<String>();
            for (Pair p : pairs) {
                if (p.size() == 1) names.add(p.getPartnerA());
                else if (p.size() == 2) {
                    names.add(p.getPartnerA());
                    names.add(p.getPartnerB());
                }
            }

            // generate the pairings
            permute(pairings, names, 0, names.size()-1, 0);

            // filter out the pairings that are not allowed
            for (ArrayList<Pair> pairing : pairings) {
                temp = new ArrayList<Pair>(); // reset for each iteration
                valid = true; // reset for each iteration

                // go through each array in the array
                for (Pair pair : pairing) { // check to determine if valid
                    for (Pair p : pairs) {
                        if (pair.compare(p)) {
                            valid = false; // invalid grouping so throw away
                            break;
                        }
                    }
                    if (valid == false) break;
                    else temp.add(new Pair(pair)); // add into final array
                }

                if (valid == false) continue; // don't add this array
                else { // if all good, add this set to the final set of sets
                    int ttlPairs = finalPairs.size();
                    finalPairs.add(new ArrayList<Pair>());
                    for (Pair p : temp) finalPairs.get(ttlPairs).add(new Pair(p));
                }
            }
        }
    }

    /**
     * Purpose: To swap array element values.
     * @return ArrayList<Pair> - an array list of all pairings of this set
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
     * Purpose: To generate all possible permutations of a set of pairings
     * @return void
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

}
