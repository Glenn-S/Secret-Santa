/**
 * @author Glenn Skelton
 * @version 1.0
 * @{date}
 *
 * Purpose: Class for storing unordered pairs of partners.
 */
public class Pair {
    private String partnerA;
    private String partnerB;
    private int total;

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

    public String getPartnerA() {
        return this.partnerA == null ? "" : new String(this.partnerA);
    }

    public String getPartnerB() {
        return this.partnerB == null ? "" : new String(this.partnerB);
    }

    public void setPartnerA(String partnerA) {
        if (partnerA.length() > 0) {
            this.partnerA = new String(partnerA);
            this.total += 1;
        }
    }

    public void setPartnerB(String partnerB) {
        if (partnerB.length() > 0) {
            this.partnerB = new String(partnerB);
            this.total += 1;
        }
    }

    /**
     *
     */
    public String getPair(String name) {
        if (this.getPartnerA().toUpperCase().equals(name.toUpperCase())) return this.getPartnerB();
        else return null;
    }

    /**
     * Purpose: Returns true if the pairs have a name in common
     * else false.
     */
    public Boolean compare(Pair pair) {
        Boolean comp =
                    (this.getPartnerA().equals(pair.getPartnerA()) && this.getPartnerB().equals(pair.getPartnerB())) ||
                    (this.getPartnerA().equals(pair.getPartnerB()) && this.getPartnerB().equals(pair.getPartnerA()));
        return comp; //
    }

    public int size() {
        return this.total;
    }

    @Override
    public String toString() {
        return "(" + getPartnerA() + ", " + getPartnerB() + ")";
    }

}
