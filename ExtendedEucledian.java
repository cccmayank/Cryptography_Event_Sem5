import java.util.Scanner;

public class ExtendedEuclidean {

    // Function to find the GCD and the coefficients for the Extended Euclidean Algorithm
    public static int[] extendedGCD(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0}; // GCD, x, y
        } else {
            int[] result = extendedGCD(b, a % b);
            int gcd = result[0];
            int x1 = result[1];
            int y1 = result[2];
            int x = y1;
            int y = x1 - (a / b) * y1;
            return new int[]{gcd, x, y}; // GCD, x, y
        }
    }

    // Function to find the multiplicative inverse of a under modulo m
    public static int multiplicativeInverse(int a, int m) {
        int[] result = extendedGCD(a, m);
        int gcd = result[0];
        int x = result[1];

        if (gcd != 1) {
            throw new ArithmeticException("Inverse doesn't exist");
        } else {
            return (x % m + m) % m; // Ensure the result is positive
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter integer a: ");
        int a = scanner.nextInt();

        System.out.print("Enter integer m: ");
        int m = scanner.nextInt();

        try {
            int inverse = multiplicativeInverse(a, m);
            System.out.println("Multiplicative Inverse of " + a + " modulo " + m + " is: " + inverse);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
