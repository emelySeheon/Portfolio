package Exercise6;

import java.util.Scanner;

public class Utils {
  
  public static int readIntFromInput(String prompt) {
    System.out.print(prompt);
    Scanner sc = new Scanner(System.in);
    int value = sc.nextInt();
    sc.close();
    
    return value;
    
  }
   
}