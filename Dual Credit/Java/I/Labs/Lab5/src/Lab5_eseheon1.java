/**
 * Project is a program to process image data froma file.
 *
 *  @author Emely Seheon
 */

import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lab5_eseheon1 {

    /**
     * <Main Method, reads file and outputs results>
     */

    public static void main(String[] args) {

        int arrayNum = 0;

        //Opening the file
        Path file = Paths.get("imagedata.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (java.io.IOException exc) {
            System.out.println("Error: Path does not exist.");
            System.exit(2);

        } catch (java.lang.ArrayIndexOutOfBoundsException exc){
            System.out.println("Error: Index out of bounds.");
            System.exit(2);
        }

        while(sc.hasNext()){
            arrayNum = arrayNum+1;

            //Getting the rows and columns
            int rows = sc.nextInt();
            int cols = sc.nextInt();

            //Making the 2d array
            int[][] data = new int[rows][cols];

            //Putting data in the array
            for(int i=0; i<rows; ++i){
                for(int j=0; j<cols; ++j){
                    int number = sc.nextInt();
                    data [i][j]=number;
                }
            }
            System.out.println("Image " + arrayNum + " Data: \n");
            for(int i = 0; i<data.length; ++i){
                for (int j = 0; j<data[i].length; ++j){
                    if (j<data[i].length-1) {
                        System.out.print(data[i][j] + " ");
                    } else if (j==data[i].length-1){
                        System.out.println(data[i][j]);
                    }
                }
            }
            System.out.print("\n");
            //Printing filter 1
            int[][]filter1 = applyFilter1(data);
            System.out.println("Image " + arrayNum + " Filter 1 Data: \n");
            for(int i = 0; i<filter1.length; ++i){
                for (int j = 0; j<filter1[i].length; ++j){
                    if (j<filter1[i].length-1) {
                        System.out.print(filter1[i][j] + " ");
                    } else if (j==filter1[i].length-1){
                        System.out.println(filter1[i][j]);
                    }
                }
            }
            System.out.print("\n");
            //Printing filter 1
            int[][]filter2 = applyFilter2(data);
            System.out.println("Image " + arrayNum + " Filter 2 Data: \n");
            for(int i = 0; i<filter2.length; ++i){
                for (int j = 0; j<filter2[i].length; ++j){
                    if (j<filter2[i].length-1) {
                        System.out.print(filter2[i][j] + " ");
                    } else if (j==filter2[i].length-1){
                        System.out.println(filter2[i][j]);
                    }
                }
            }
            System.out.print("\n");
        }

    }

    /**
     * Replaces each cell with the average of itself and the adjacent cells
     * @param data <the image data read from the file>
     * @return <int[][] with the new cells>
     */

    public static int[][] applyFilter1(int[][] data){

        int sum = 0;
        int [][] filter1 = new int[data.length][data[1].length];
        for(int i = 0; i<data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                int[] neighbors = getNeighbors(i, j, data);
                for(int k = 0; k<neighbors.length; ++k ){
                    sum = sum + neighbors[k];
                }
                filter1[i][j] = sum/neighbors.length;
            }
        }

        return filter1;
    }

    /**
     * <Obtains adjacent values for a cell in a 2d array>
     * @param  row <the number of rows in the 2d array>
     * @param  col <the number of columns in the 2d array>
     * @param data <the image data read from the file>
     * @return <int[] with the adjacent values>
     */

    public static int[] getNeighbors(int row, int col, int[][] data){

        int[] neighbors;

        if (row==0 && col==0){
            neighbors = new int [3];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row+1][col];
            neighbors[2] = data[row][col+1];

        } else if (row==0 && col>0 && col<data[row].length-1){
            neighbors = new int [4];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row][col-1];
            neighbors[2] = data[row+1][col];
            neighbors[3] = data[row][col+1];

        } else if (row==0 && col==data[row].length-1){
            neighbors = new int [3];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row+1][col];
            neighbors[2] = data[row][col-1];

        } else if (row>0 && row< data.length-1 && col==data[row].length-1){
            neighbors = new int [4];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row-1][col];
            neighbors[2] = data[row][col-1];
            neighbors[3] = data[row+1][col];

        } else if (row==data.length-1 && col==data[row].length-1){
            neighbors = new int [3];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row][col-1];
            neighbors[2] = data[row-1][col];

        } else if (row==data.length-1 && col>0 && col<data[row].length-1){
            neighbors = new int [4];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row][col-1];
            neighbors[2] = data[row][col+1];
            neighbors[3] = data[row-1][col];

        } else if (row==data.length-1 && col==0){
            neighbors = new int [3];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row-1][col];
            neighbors[2] = data[row][col+1];

        } else if (row>0 && row< data.length-1 && col==0){
            neighbors = new int [4];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row-1][col];
            neighbors[2] = data[row+1][col];
            neighbors[3] = data[row][col+1];

        } else {
            neighbors = new int [5];
            neighbors[0] = data[row][col];
            neighbors[1] = data[row-1][col];
            neighbors[2] = data[row][col-1];
            neighbors[3] = data[row+1][col];
            neighbors[4] = data[row][col+1];
        }

        return neighbors;
    }

    /**
     * <Finds the negated value of each cell>
     * @param data <the image data read from the file>
     * @return <int[][] with the negated values>
     */

    public static int[][] applyFilter2(int[][] data){

        int [][] filter2 = new int[data.length][data[1].length];

        for(int i = 0; i<data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {

                if (data[i][j]<128){
                    filter2[i][j] = 255-data[i][j];
                } else{
                    filter2[i][j] = data[i][j];
                }
            }
        }

        return filter2;
    }
}
