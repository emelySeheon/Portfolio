//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 10/29/2020
//Course: CSCI 2251
//Program: MatrixThread is a program to add two matrices together

import java.io.*;
import java.lang.Thread;
import java.util.Scanner;

public class MatrixThread {

    public static void main(String[] args) {
        String filename = args[0];

        int rows = 0;
        int cols = 0;
        int[][] matrixA = new int[0][];
        int[][] matrixB = new int[0][];
        int[][] matrixC = new int[0][];

        boolean rowsEven = false;
        boolean colsEven = false;
        int halfRows = 0;
        int halfCols = 0;

        int[][] ASubOne = new int[0][];
        int[][] ASubTwo = new int[0][];
        int[][] ASubThree = new int[0][];
        int[][] ASubFour = new int[0][];

        int[][] BSubOne = new int[0][];
        int[][] BSubTwo = new int[0][];
        int[][] BSubThree = new int[0][];
        int[][] BSubFour = new int[0][];

        int[][] CSubOne = new int[0][];
        int[][] CSubTwo = new int[0][];
        int[][] CSubThree = new int[0][];
        int[][] CSubFour = new int[0][];

        //Reading from the file
        try {
            File matrixInfo = new File(filename);
            Scanner fileReader = new Scanner(matrixInfo);
            rows = fileReader.nextInt();
            cols = fileReader.nextInt();

            matrixA = new int[rows][cols];
            matrixB = new int[rows][cols];
            matrixC = new int[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    matrixA[i][j] = fileReader.nextInt();
                }
            }
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    matrixB[i][j] = fileReader.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
            e.printStackTrace();
        }

        //Seeing if the rows and columns are even or odd
        if (rows % 2 == 0) {
            rowsEven = true;
        }
        if (cols % 2 == 0) {
            colsEven = true;
        }

        halfRows = rows / 2;
        halfCols = cols / 2;

        //Making the A sub arrays
        ASubOne = new int[halfRows][halfCols];

        if (!rowsEven) {
            if (!colsEven) {
                ASubTwo = new int[halfRows][halfCols + 1];
                ASubThree = new int[halfRows + 1][halfCols + 1];
                ASubFour = new int[halfRows + 1][halfCols + 1];
            } else {
                ASubTwo = new int[halfRows][halfCols];
                ASubThree = new int[halfRows + 1][halfCols];
                ASubFour = new int[halfRows + 1][halfCols];
            }
        } else if (!colsEven) {
            ASubTwo = new int[halfRows][halfCols + 1];
            ASubThree = new int[halfRows][halfCols + 1];
            ASubFour = new int[halfRows][halfCols + 1];
        } else {
            ASubTwo = new int[halfRows][halfCols];
            ASubThree = new int[halfRows][halfCols];
            ASubFour = new int[halfRows][halfCols];
        }

        //Making the B sub arrays
        BSubOne = new int[halfRows][halfCols];
        BSubTwo = new int[halfRows][halfCols];

        if (!rowsEven) {
            if (!colsEven) {
                BSubTwo = new int[halfRows][halfCols + 1];
                BSubThree = new int[halfRows + 1][halfCols];
                BSubFour = new int[halfRows + 1][halfCols + 1];
            } else {
                BSubTwo = new int[halfRows][halfCols];
                BSubThree = new int[halfRows + 1][halfCols];
                BSubFour = new int[halfRows + 1][halfCols];
            }
        } else if (!colsEven) {
            BSubTwo = new int[halfRows][halfCols + 1];
            BSubThree = new int[halfRows][halfCols];
            BSubFour = new int[halfRows][halfCols + 1];
        } else {
            BSubTwo = new int[halfRows][halfCols];
            BSubThree = new int[halfRows][halfCols];
            BSubFour = new int[halfRows][halfCols];
        }

        //Filling up the A sub arrays
        for (int i = 0; i < ASubOne.length; ++i) {
            for (int j = 0; j < ASubOne[i].length; ++j) {
                ASubOne[i][j] = matrixA[i][j];
            }
        }

        for (int i = 0; i < ASubTwo.length; ++i) {
            for (int j = 0; j < ASubTwo[i].length; ++j) {
                ASubTwo[i][j] = matrixA[i][j + ASubOne[0].length];
            }
        }

        for (int i = 0; i < ASubThree.length; ++i) {
            for (int j = 0; j < ASubThree[i].length; ++j) {
                ASubThree[i][j] = matrixA[i + ASubOne.length][j];
            }
        }

        for (int i = 0; i < ASubFour.length; ++i) {
            for (int j = 0; j < ASubFour[i].length; ++j) {
                ASubFour[i][j] = matrixA[i + ASubOne.length][j + ASubOne[0].length];
            }
        }

        // Filling up the B sub arrays
        for (int i = 0; i < BSubOne.length; ++i) {
            for (int j = 0; j < BSubOne[i].length; ++j) {
                BSubOne[i][j] = matrixB[i][j];
            }
        }

        for (int i = 0; i < BSubTwo.length; ++i) {
            for (int j = 0; j < BSubTwo[i].length; ++j) {
                BSubTwo[i][j] = matrixB[i][j + BSubOne[0].length];
            }
        }

        for (int i = 0; i < BSubThree.length; ++i) {
            for (int j = 0; j < BSubThree[i].length; ++j) {
                BSubThree[i][j] = matrixB[i + BSubOne.length][j];
            }
        }

        for (int i = 0; i < BSubFour.length; ++i) {
            for (int j = 0; j < BSubFour[i].length; ++j) {
                BSubFour[i][j] = matrixB[i + BSubOne.length][j + BSubOne[0].length];
            }
        }

        //Using the threads to add the matrices
        ThreadOperation subOne = new ThreadOperation(ASubOne, BSubOne);
        subOne.start();
        ThreadOperation subTwo = new ThreadOperation(ASubTwo, BSubTwo);
        subTwo.start();
        ThreadOperation subThree = new ThreadOperation(ASubThree, BSubThree);
        subThree.start();
        ThreadOperation subFour = new ThreadOperation(ASubFour, BSubFour);
        subFour.start();

        //Making the C sub arrays
        CSubOne = new int[halfRows][halfCols];

        if (!rowsEven) {
            if (!colsEven) {
                CSubTwo = new int[halfRows][halfCols + 1];
                CSubThree = new int[halfRows + 1][halfCols];
                CSubFour = new int[halfRows + 1][halfCols + 1];
            } else {
                CSubTwo = new int[halfRows][halfCols];
                CSubThree = new int[halfRows + 1][halfCols];
                CSubFour = new int[halfRows + 1][halfCols];
            }
        } else if (!colsEven) {
            CSubTwo = new int[halfRows][halfCols + 1];
            CSubThree = new int[halfRows][halfCols];
            CSubFour = new int[halfRows][halfCols + 1];
        } else {
            CSubTwo = new int[halfRows][halfCols];
            CSubThree = new int[halfRows][halfCols];
            CSubFour = new int[halfRows][halfCols];
        }

        //So the C subsets are not prematurely filled
        while (subOne.isAlive()
                || subTwo.isAlive()
                || subThree.isAlive()
                || subFour.isAlive()) {
            int i = 0;
            ++i;
        }

        //Filling up the C sub arrays
        for (int i = 0; i < CSubOne.length; ++i) {
            for (int j = 0; j < CSubOne[i].length; ++j) {
                CSubOne[i][j] = subOne.CSubmatrix[i][j];
            }
        }

        for (int i = 0; i < CSubTwo.length; ++i) {
            for (int j = 0; j < CSubTwo[i].length; ++j) {
                CSubTwo[i][j] = subTwo.CSubmatrix[i][j];
            }
        }

        for (int i = 0; i < CSubThree.length; ++i) {
            for (int j = 0; j < CSubThree[i].length; ++j) {
                CSubThree[i][j] = subThree.CSubmatrix[i][j];
            }
        }

        for (int i = 0; i < CSubFour.length; ++i) {
            for (int j = 0; j < CSubFour[i].length; ++j) {
                CSubFour[i][j] = subFour.CSubmatrix[i][j];
            }
        }

        //Putting the C sub array contents in the C array
        for (int i = 0; i < CSubOne.length; ++i) {
            for (int j = 0; j < CSubOne[i].length; ++j) {
                matrixC[i][j] = CSubOne[i][j];
            }
        }

        for (int i = 0; i < CSubTwo.length; ++i) {
            for (int j = 0; j < CSubTwo[i].length; ++j) {
                matrixC[i][j + CSubOne[0].length] = CSubTwo[i][j];
            }
        }

        for (int i = 0; i < CSubThree.length; ++i) {
            for (int j = 0; j < CSubThree[i].length; ++j) {
                matrixC[i + CSubOne.length][j] = CSubThree[i][j];
            }
        }

        for (int i = 0; i < CSubFour.length; ++i) {
            for (int j = 0; j < CSubFour[i].length; ++j) {
                matrixC[i + CSubOne.length][j + CSubOne[0].length] = CSubFour[i][j];
            }
        }

        //Print the results
        for (int i = 0; i < matrixC.length; ++i) {
            for (int j = 0; j < matrixC[i].length; ++j) {
                System.out.print(matrixC[i][j]);
            }
            System.out.print("\n");


        }
    }
}