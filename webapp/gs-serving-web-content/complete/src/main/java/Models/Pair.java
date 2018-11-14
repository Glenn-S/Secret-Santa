package Models;

/***********************************************************
 * @author Glenn Skelton
 * @version 1.0
 * Last modified: @{date}
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

    public Boolean exists(Pair pair) {
        Boolean exists = this.getPartnerA().equals(pair.getPartnerA()) ||
                         this.getPartnerB().equals(pair.getPartnerB()) ||
                         this.getPartnerA().equals(pair.getPartnerB()) ||
                         this.getPartnerB().equals(pair.getPartnerA());
        return exists; // if name is already in the table it should not be allowed again

    }

    /**
     * Purpose: Returns true if the pairs have a name in common
     * else false.
     * @return Boolean - true if pair passed in matches exisiting couples
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

