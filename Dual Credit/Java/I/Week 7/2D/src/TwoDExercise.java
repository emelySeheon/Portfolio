public class TwoDExercise {
    //NOTES:
    // remember:
    // number of rows in a 2d array is found array.length
    // number of cols in a row is found array[row number].length

    // Given the array in the main method

    // Part 1. Write a method
    // that returns the maximum value in the 2d array.
    public static double TwoDExercise(double[][] array) {
        // you will need 2 nested for loops to accomplish finding the maximum value.
        double max = array[0][0];

        //write loops here...
         double maxCol = array[0][0];
         for (int i=0;i<array.length;++i){
             double maxRow = array[i][0];
             for (int j=0; j<array[i].length;++j){
                 if (array[i][j]>=maxRow){
                     maxRow = array [i][j];
                 } else{
                     continue;
                 }
             }
             if (maxRow>=maxCol){
                 maxCol=maxRow;
             }else{
                 continue;
             }
         }
        max = maxCol;
        return max;// stub
    }

    // Part 2. Write a method
    // that returns the sum of the elements in column of array
    // e.g. if column = 1, the method should return 9.3 i.e. 4.6+3.6+1.1
    public static double columnSum(double[][] array, int column) {
        //You will only need one for loop that iterates through the rows.
        double sum = 0;

        //write loop here....
        for (int i=0; i<array.length; ++i){
            sum = ( sum + array[i][column]);
        }
        return sum;
    }

    // Part 3. Write a method
    // that calculates the row sum for every row and returns
    // each of the values in an array.
    // Index i of the return array contains the sum of elements in row i.
    // e.g. the return array should contain the values
    // [0] = 14.4 i.e. 5.5 + 4.6 + 4.3
    // [1] = 9.3 i.e. 2.9 + 3.6 + 2.8
    // [2] = 16.0 i.e. 5.0 + 1.1 + 9.9
    public static double allRowSums(double[][] array, int row) {
        //You will only need one for loop that iterates through the rows.
        double sum = 0;

        //write loop here....
        for (int i=0; i<array[row].length; ++i){
            sum = ( sum + array[row][i]);
        }
        return sum;
    }

    // Part 4. Write a method
    // that prints the values of the array in tabular format
    // e.g. output should look like:
    // 5.5 4.6 4.3
    // 2.9 3.6 2.8
    // 5.0 1.1 9.9
    public static void printArray(double[][] array) {
        //You will need two nested for loops to sum the row values
        for(int i = 0; i<array.length; ++i){
            for (int j = 0; j<array[i].length; ++j){
                if (j<array[i].length-1) {
                    System.out.print(array[i][j] + " ");
                } else if (j==array[i].length-1){
                    System.out.println(array[i][j]);
                }
            }
        }

    }

    public static void main(String[] args) {

        double[][] array = {
                { 5.5, 4.6 , 4.3 },
                { 2.9, 3.6, 2.8 },
                { 5.0, 1.1, 9.9 } };

        double max = TwoDExercise(array);
        System.out.println("max value = " + max);// prints 9.9

        double sum = columnSum(array, 0);
        System.out.println("sum of column 0 = " + sum);// prints 13.4
        sum = columnSum(array, 1);
        System.out.println("sum of column 1 = " + sum); // prints 9.3
        sum = columnSum(array, 2);
        System.out.println("sum of column 2 = " + sum); // prints 17.0

        double sumRows = allRowSums(array, 0);
        System.out.println("sum of row 0 = " + sumRows);// prints 14.4
        sumRows = allRowSums(array, 1);
        System.out.println("sum of row 1 = " + sumRows);// prints 9.3
        sumRows = allRowSums(array, 2);
        System.out.println("sum of row 2 = " + sumRows);// prints 16.0

        printArray(array);

    }
}