


public class TestSecretSanta {
    public static void main(String[] args) {
        SecretSanta ss1 = new SecretSanta();
        ss1.printPairs();

        SecretSanta ss2 = new SecretSanta("tests/test1.txt");
        ss2.printPairs();
    }
}
