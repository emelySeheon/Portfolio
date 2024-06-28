public class ArrayUtils {
    //1.
    public static void printArray(char[] array) {
        for (int i = 0; i <= array.length-1; ++i)
        {
            System.out.println(array[i]);
        }
    }
    //2.
    public static void printArray(int[] array) {
        for (int i = 0; i <= 3; ++i)
        {
            System.out.println(array[i]);
        }
    }
    //3.
    public static void swap(char[] array, int i, int j) {
        char h = array[i];
        array[i]=array[j];
        array[j]=h;
    }
    //4.
    public static void shiftDown(char[] array, int index) {
        System.out.println(array);
        char saved= array[array.length-1];
        for (int i = array.length-1; i >= index; i--) {
            if (i==0){
                array[i]=saved;
            } else {
                array[i] = array[i - 1];
                System.out.println(array);
            }
        }
        System.out.println(array);
    }
    //5.
    public static int findMinValue(int[] array) {
        int currentSmallest = array[0];
        for (int i = 0; i<= array.length-1; i++){
            if (array[i]< currentSmallest){
                currentSmallest = array[i];
            }
        }
        return currentSmallest;// this is here just so the code compiles
    }
    //6.
    public static int findMinIndex(int[] array) {
        int smallestIndex = 0;
        int currentSmallest = array[0];
        for (int i = 0; i<= array.length-1; i++){
            if (array[i]< currentSmallest){
                currentSmallest = array[i];
                smallestIndex=i;
            }
        }
        return smallestIndex;// this is here just so the code compiles
    }
    //7.
    public static char[] copy(char[] fromArray) {
        char[] toArray = new char[fromArray.length];
        for (int i =0; i < fromArray.length; ++i){
            toArray[i] = fromArray[i];
        }
        return toArray;

    }
    public static void main(String[] args) {
        char[] charArray = {'h','e','l','l','o'};
        int[] intArray = {99, 100, 85, 101};

        printArray(charArray);
        printArray(intArray);


        swap(charArray, 0, charArray.length-1);
        printArray(charArray);

        shiftDown(charArray, 1);
        printArray(charArray);

        int minValue = findMinValue(intArray);
        int minIndex = findMinIndex(intArray);
        System.out.println("minValue = "+minValue+ " minIndex = "+minIndex);

        char[] toArray = copy(charArray);
        printArray(toArray);

    }
}