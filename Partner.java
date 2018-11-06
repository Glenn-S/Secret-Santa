

public class Partner {
    private String partnerA;
    private String partnerB;


    public Partner () {
        this.partnerA = "";
        this.partnerB = "";
    }

    public Partner (String partnerA, String partnerB) {
        this.setPartnerA(partnerA);
        this.setPartnerB(partnerB);
    }

    public Partner (Partner copyPartner) {
        this.setPartnerA(copyPartner.getPartnerA());
        this.setPartnerB(copyPartner.getPartnerB());
    }



    public String getPartnerA() {
        return new String(this.partnerA);
    }

    public String getParternB() {
        return new String(this.partnerB);
    }

    public ArrayList<String> getPair() {
        ArrayList<String> pair = new ArrayList<String>(this.getPartnerA(), this.getParternB());
        return pair;
    }

    public void setPartnerA(String partnerA) {
        this.partnerA = partnerA.equals("") : "" ? new String(partnerA);
    }

    public void setPartnerB(String partnerB) {
        this.partnerB = partnerB.equals("") : "" ? new String(partnerB);
    }

}
