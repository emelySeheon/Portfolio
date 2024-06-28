package Exercise6;

/*
Name: Emely Seheon
Program: Exercise 6
Date: 6/6/20
Purpose: To learn how to use loops.
*/

public class Exercise6 {
    public static void main(String[] args) {
    int x = Utils.readIntFromInput("Enter Number To Find Sqrt of: ");
    float guess = 3f;
    while (Math.abs(guess*guess-x)!=0);
        {
            guess=(guess+x/guess)/2;
        }
    
    System.out.println("guess = "+guess);
    }
}