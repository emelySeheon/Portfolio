
public class BinarySearchExercise {
    public static int binaryRecursive(int[] array, int min, int max, int target) {
        //Binary Search Recursive search Exercise. Same as below except use recursion.

        int midpoint = (min + max)/2;

        if (target == array[midpoint]) {
            return midpoint;
        } else if (target < array[midpoint]) {
            //look left
            max = midpoint - 1;

            return binaryRecursive(array, min, max, target);
        } else {
            //look right
            min = midpoint + 1;

            return binaryRecursive(array, min, max, target);
        }

        //return -1;
    }
    public static int binarySearch(int[] array, int target) {
        //implement binary search that returns index where target
        // is found. Look at binaryLoop and instead of returning a boolean
        // return an index.
        int min = 0;
        int max = array.length - 1;

        while(min <= max) {
            int midpoint = (min + max)/2;

            if (target == array[midpoint]) {
                return midpoint;
            } else if (target < array[midpoint]) {
                //look left
                max = midpoint - 1;
            } else {
                //look right
                min = midpoint + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1,6,45,67,89,100,201,456,890};

        int index = binarySearch(array, 45);


        System.out.println("binarySearch with while loop = "+binarySearch(array, 45));
        System.out.println("binarySearch with recursion = "+binaryRecursive(array, 0, array.length-1, 456));
    }

}