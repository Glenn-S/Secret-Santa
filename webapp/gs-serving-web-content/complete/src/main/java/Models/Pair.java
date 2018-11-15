package Models;

/***********************************************************
 * @author Glenn Skelton
 * @version 1.0
 * Last Modified by Glenn Skelton: 14-11-2018
 *
 * Purpose: Class for storing unordered pairs of partners.
 ***********************************************************/
public class Pair {
    private String partnerA;
    private String partnerB;
    private int total;

    /*********************** CONSTRUCTORS **********************/
    public Pair() {
        this.partnerA = null;
        this.partnerB = null;
        this.total = 0;
    }

    public Pair(String partnerA) {
        this.setPartnerA(partnerA);
        this.partnerB = null;
        this.total = 1;
    }

    public Pair(String partnerA, String partnerB) {
        this.setPartnerA(partnerA);
        this.setPartnerB(partnerB);
        this.total = 2;
    }

    public Pair(Pair copyPair) {
        this.setPartnerA(copyPair.getPartnerA());
        this.setPartnerB(copyPair.getPartnerB());
        this.total = copyPair.size();
    }


    /********************** SETTERS/GETTERS ***********************/

    /**
     * Purpose: To retrieve partnerA.
     * @return String - the name of the person stored in partnerA.
     */
    public String getPartnerA() {
        return this.partnerA == null ? "" : new String(this.partnerA);
    }

    /**
     * Purpose: To retrieve partnerB.
     * @return String - the name of the person stored in partnerB.
     */
    public String getPartnerB() {
        return this.partnerB == null ? "" : new String(this.partnerB);
    }

    /**
     * Purpose: To set partnerA to the name passed in. If the name
     * passed in is an empty string, partnerA remains null.
     * @return void
     */
    public void setPartnerA(String partnerA) {
        if (partnerA.length() > 0) {
            this.partnerA = new String(partnerA);
            this.total += 1;
        }
    }

    /**
     * Purpose: To set partnerB to the name passed in. If the name
     * passed in is an empty string, partnerB remains null.
     * @return void
     */
    public void setPartnerB(String partnerB) {
        if (partnerB.length() > 0) {
            this.partnerB = new String(partnerB);
            this.total += 1;
        }
    }

    /**
     * Purpose: To retrieve the name of the person paired with the
     * name of the person passed in.
     * @return String - partnerB paired with partnerA
     */
    public String getPair(String name) {
        if (this.getPartnerA().toUpperCase().equals(name.toUpperCase()))
            return this.getPartnerB();
        else return null;
    }

    /**
     * Purpose: To determine if any person in a pair exists in the database
     * already.
     * @param pair
     * @return exists - boolean value that is true if pair has a person in common
     */
    public Boolean exists(Pair pair) {
        Boolean exists = false;

        if (pair.size() == 1) { // done to ensure null doesn't throw things off
            exists = this.getPartnerA().equals(pair.getPartnerA()) ||
                     this.getPartnerB().equals(pair.getPartnerA());
        }
        else if (pair.size() == 2) {
            exists = this.getPartnerA().equals(pair.getPartnerA()) ||
                     this.getPartnerB().equals(pair.getPartnerA()) ||
                     this.getPartnerA().equals(pair.getPartnerB()) ||
                     this.getPartnerB().equals(pair.getPartnerB());
        }

        return exists;
    }

    /**
     * Purpose: Returns true if the pairs have a name in common
     * else false.
     * @return Boolean - true if pair passed in matches existing couples
     * pairing else false
     */
    public Boolean compare(Pair pair) {
        Boolean comp = (this.getPartnerA().equals(pair.getPartnerA()) && this.getPartnerB().equals(pair.getPartnerB())) ||
                       (this.getPartnerA().equals(pair.getPartnerB()) && this.getPartnerB().equals(pair.getPartnerA()));
        return comp;
    }

    /**
     * Purpose: To retrieve the number of people stored in a pairing.
     */
    public int size() {
        return this.total;
    }

    /**
     * Purpose: To return a string form of the Pair class
     * @return String - (personA, PersonB)
     */
    @Override
    public String toString() {
        return "(" + getPartnerA() + ", " + getPartnerB() + ")";
    }

}

