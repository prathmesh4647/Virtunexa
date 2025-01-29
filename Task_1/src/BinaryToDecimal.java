import java.util.Scanner;

public class BinaryToDecimal{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a binary number: ");
        String binaryString = scanner.nextLine();

        try {
            int decimalValue = Integer.parseInt(binaryString, 2);
            System.out.println("Decimal equivalent: " + decimalValue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid binary number. Please enter only 0s and 1s.");
        }

        scanner.close();
    }
}
