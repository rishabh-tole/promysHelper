// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String s = "";

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                s = s + "(" + i + ", " + j + "), ";
            }

        }

        System.out.println(s);
    }
}