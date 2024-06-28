//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 11/15/2020
//Course: CSCI 2251
//Program: Client is a program to add two matrices together

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String host = args[0];
        String filename = JOptionPane.showInputDialog("Enter File Name:");

        int rows = 0;
        int cols = 0;
        int[][] matrixA = new int [0][];
        int[][] matrixB = new int [0][];
        int[][] matrixC = new int [0][];

        boolean rowsEven = false;
        boolean colsEven = false;
        int halfRows = 0;
        int halfCols = 0;

        int[][] ASubOne = new int [0][];
        int[][] ASubTwo = new int [0][];
        int[][] ASubThree = new int [0][];
        int[][] ASubFour = new int [0][];

        int[][] BSubOne = new int [0][];
        int[][] BSubTwo = new int [0][];
        int[][] BSubThree = new int [0][];
        int[][] BSubFour = new int [0][];

        int[][] CSubOne = new int [0][];
        int[][] CSubTwo = new int [0][];
        int[][] CSubThree = new int [0][];
        int[][] CSubFour = new int [0][];

        //Reading from the file
        try{
            File matrixInfo = new File (filename);
            Scanner fileReader = new Scanner (matrixInfo);
            rows = fileReader.nextInt();
            cols = fileReader.nextInt();

            matrixA = new int[rows][cols];
            matrixB = new int[rows][cols];
            matrixC = new int[rows][cols];

            for (int i =0; i<rows;++i){
                for(int j = 0; j<cols;++j){
                    matrixA[i][j] = fileReader.nextInt();
                }
            }
            for (int i =0; i<rows;++i){
                for(int j = 0; j<cols;++j){
                    matrixB[i][j] = fileReader.nextInt();
                }
            }
        } catch (FileNotFoundException e){
            MatrixAdditionGUI Interface = new MatrixAdditionGUI("File not found :(");
            Interface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Interface.setSize(350, 100);
            Interface.setVisible(true);
            e.printStackTrace();

            ClientClass giveInfo = new ClientClass();

            for (int i = 0; i< matrixA.length; ++i){
                for (int j = 0; j<matrixA[i].length; ++j){
                    giveInfo.MatrixA[i][j] = matrixA[i][j];
                }
            }
            for (int i = 0; i< matrixB.length; ++i){
                for (int j = 0; j<matrixB[i].length; ++j){
                    giveInfo.MatrixB[i][j] = matrixB[i][j];
                }
            }

            giveInfo.runClient(host);
            giveInfo.readResults(host);

            for (int i = 0; i<giveInfo.MatrixC.length; ++i){
                for (int j = 0; j< giveInfo.MatrixC[i].length; ++j){
                    matrixC[i][j] = giveInfo.MatrixC[i][j];
                }
            }
        }

        //Make the results string
        StringBuilder results = new StringBuilder();
        for(int i =0; i<matrixC.length;++i){
            for (int j = 0; j<matrixC[i].length;++j){
                results.append(matrixC[i][j]).append("  ");
            }
            results.append("\n");
        }

        //Print the results
        System.out.println(results.toString());
        MatrixAdditionGUI Interface = new MatrixAdditionGUI(results.toString());
        Interface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Interface.setSize(400, 400);
        Interface.setVisible(true);
    }
}