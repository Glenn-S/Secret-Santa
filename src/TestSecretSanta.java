
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
/*
        SecretSanta ss1 = new SecretSanta();
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));

        SecretSanta ss1 = new SecretSanta();
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
        System.out.println(ss1.pickName("Glenn Skelton"));
*/
    }
}
