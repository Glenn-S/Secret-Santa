
import java.util.ArrayList;

public class TestSecretSanta {
    public static void main(String[] args) {
        SecretSanta ss1 = new SecretSanta();
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Gianna Read-Skelton"));
        System.out.println(ss1.pickName("Glenn Skelto"));
        System.out.println(ss1.pickName("GLENN SKELTON"));
        System.out.println(ss1.pickName("glenn skelton"));
        System.out.println(ss1.pickName(""));

        //ss1.createConfigFile(new ArrayList<Pair>(), "test2.txt");

        SecretSanta ss2 = new SecretSanta();
        ss2.createConfigFile(ss2.getFinalPairs(), "test2.txt");
    }
}
