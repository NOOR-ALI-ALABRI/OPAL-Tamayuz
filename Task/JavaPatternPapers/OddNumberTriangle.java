package JavaPatternPapers;
import java.util.Scanner;

public class OddNumberTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int odd = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(odd);
                odd += 2;
                if (j < i) System.out.print(" ");
            }
            System.out.println();
        }
    }
}