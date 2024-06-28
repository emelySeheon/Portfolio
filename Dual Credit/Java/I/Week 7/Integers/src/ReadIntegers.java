import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadIntegers {

    public static void main(String[] args) throws IOException {
        //open file using Path and Scanner
        Path numberPath = Paths.get("numbers.txt");
        Scanner fileInput = new Scanner(numberPath);

        //read the number of rows from the file
        //using the nextInt method of Scanner
        int rows = -1;
        if (fileInput.hasNext()){
            rows = fileInput.nextInt();
        }

        //read the number of columns from the file
        //using the nextInt method of Scanner
        int cols = -1;
        if (fileInput.hasNext()){
            cols = fileInput.nextInt();
        }

        //create a 2d integer array using the number of rows
        //and the number of columns as the size of the array
        int[][] array = new int[rows][cols];

        //Using two nested for loops, outer loop for the number of
        //rows and the inner loop for the number of columns.
        //Read the array data from the file using the nextInt
        //method of Scanner and put it into the 2d integer array
        for (int i=0; i<array.length; i++){
            for (int j = 0; j<array[i].length;j++){
                array[i][j]=fileInput.nextInt();
            }
        }

        //Using two nested for loops, same as above print out the
        //values of the 2d array. Make sure the values printed are
        //the same values that are in the file.
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

}