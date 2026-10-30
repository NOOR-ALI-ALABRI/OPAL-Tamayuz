package JavaPatternPapers;
import java.util.Scanner;

public class FullDiamond {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        // upper half of the diamond
        for (int i = 1; i <= n; i++) { // loop for number of rows n
            for (int s = 1; s <= n - i; s++) System.out.print(" ");
            for (int a = 1; a <= 2 * i - 1; a++) System.out.print("*");
            System.out.println();
        }
        // lower half of the diamond
        for (int i = n - 1; i >= 1; i--) {
            for (int s = 1; s <= n - i; s++) System.out.print(" ");
            for (int a = 1; a <= 2 * i - 1; a++) System.out.print("*");
            System.out.println();
        }
    }
}