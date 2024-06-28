import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadingFile {

    public static void main(String[] args) {
        if (args.length ==0){
            System.out.println("Error");
            System.exit(1);
        }

        //Getting file name
        String file = args[0];

        Path filePath = Paths.get(file);
        try {
            //Opening the scanner
            Scanner sc = new Scanner(filePath);

            //Printing out the info on the file
            String month = sc.next();
            System.out.println("Read a string: " + month);

            int numberOne = sc.nextInt();
            System.out.println("Read an int: " + numberOne);

            int numberTwo = sc.nextInt();
            System.out.println("Read an int: " + numberTwo);

            String day = sc.next();
            System.out.println("Read a string: " + day);

            String year = sc.next();
            System.out.println("Read a string: " + year);

            int numberThree = sc.nextInt();
            System.out.println("Read an int: " + numberThree);

            System.out.println("Read line: " + sc.nextLine());

            //Catching errors
        } catch (java.io.IOException exc) {
            System.out.println("Error: Path does not exist.");
            System.exit(2);

        } catch (java.nio.file.InvalidPathException exc){
            System.out.println("Error: Invalid Path");
            System.exit(3);

        } catch (java.util.InputMismatchException exc){
            System.out.println("Error: Input did not match");
            System.exit(4);
        }
    }
}