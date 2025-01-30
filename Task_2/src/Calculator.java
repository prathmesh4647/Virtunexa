import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);

        System.out.print("1. add Values \n" +
                         "2. exit\n");
        int Input = input.nextInt();

        if(Input == 1 ){
            System.out.print("Enter first value: ");
            float x = input.nextFloat();

            System.out.print("Enter second value: ");
            float y = input.nextFloat();

            System.out.println("1. Addition\n" +
                               "2. Subtraction\n" +
                               "3. Multiplication\n" +
                               "4. Division");
            int in = input.nextInt();
            if(in == 1){
                System.out.print("The Addition is : " + (x+y) );
            }

            else if(in == 2){
                System.out.print("The Subtraction is : " + (x-y) );
            }

            else if(in == 3){
                System.out.print("The Multiplication is : " + (x*y) );
            }

            else if(in == 4){
                System.out.print("The Division is : " + (x/y) );
            }
        }
        else{
            System.out.print("Thank you!");
        }

    }
}
