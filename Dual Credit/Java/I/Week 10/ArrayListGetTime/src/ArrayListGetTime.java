import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListGetTime {
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        for (int i=0; i < 100; i++) {
            list.add(i);
        }

        int numberOfGets = 100000000;

        long start = System.currentTimeMillis();
        int y = 0;
        for (int i=0; i < numberOfGets; i++) {
            int x = list.get(50);
            y = y + x;
        }
        //calculate run time by subtracting the current time from the start time.
        long runTime = System.currentTimeMillis()-start;
        System.out.println("Time to get "+numberOfGets+" gets using "+list.getClass().getName()+": "+ runTime +" milliseconds");
    }

}

//ArrayList was faster because it's more organised than LinkedList, so it's easier to find something.