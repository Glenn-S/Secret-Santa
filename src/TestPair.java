
import java.util.ArrayList;

public class TestPair {
    public static void main(String[] args) {
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        pairs.add(new Pair("Glenn Skelton", "Gianna Read-Skelton"));
        pairs.add(new Pair("Kurtis Niedling", "Karisa Gaul"));
        pairs.add(new Pair("Paw Ming Ngaung"));
        pairs.add(new Pair("Jesses Blencowe", "Jen Blencowe"));

        printPairs(pairs);
    }

    public static void printPairs(ArrayList<Pair> pairs) {
        for (Pair p : pairs) System.out.println(p);
    }
}
