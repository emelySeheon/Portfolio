/**
 * Lab3 is a program to process rainfall data.
 * @author Emely Seheon
 */

public class Lab3_eseheon1 {

    public static void main(String[] args) {
        int [] years;
        years = new int[] {1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990};

        float [] rainData;
        rainData = new float[] {(float) 0.2744447, (float) 0.036378264, (float) 1.4680095, (float) 2.8227994, (float) 2.2719026, (float) 2.0574791, (float) 2.6848125, (float) 0.56718653, (float) 0.9973374, (float) 0.39861685, (float) 0.8117836, (float) 1.2329354, (float) 2.4103663, (float) 2.4921546, (float) 2.2694077, (float) 0.78657675, (float) 0.105816364, (float) 0.8013573, (float) 0.6838444, (float) 0.20090479, (float) 2.089554};

        System.out.printf("The Average Rain Fall: %.2f",calculateAverage(rainData));
        System.out.println("\nThe Largest Rain Fall Year: " + findLargestRainFallYear(years, rainData));
        System.out.println("The Smallest Rain Fall Year: " + findSmallestRainFallYear(years, rainData));
    }

    /**
     * Method to calculate the average rainfall given the array
     * @param rainData array that contains the rainfall number for years 1970 to 1990
     * @return the average rainfall
     */
    public static float calculateAverage(float[] rainData){
        float sum=0;
        float average;
        for (int i=0; i<rainData.length; ++i){
            sum = sum+rainData[i];
        }
        average = sum/(rainData.length-1);

        return average;
    }

    /**
     * Method to find the year in which the largest rainfall occurred.
     * @param rainData array that contains rainfall numbers for years 1970 to 1990
     * @return the year in which the smallest rainfall occurred
     */
    public static int findLargestRainFallYear(int[] years, float[] rainData){
        float maxRain=0;
        int index=0;

        for (int i=0; i<rainData.length; ++i){
            if (rainData[i]>maxRain){
                maxRain = rainData [i];
                index = i;
            }
        }
        int year = years [index];

        return year;
    }

    /**
     * Method to find the year in which the smallest rainfall occurred.
     * @param rainData array that contains rainfall numbers for years 1970 to 1990
     * @return the year in which the smallest rainfall occurred
     */
    public static int findSmallestRainFallYear(int[] years, float[] rainData){
        float minRain=10;
        int index=0;

        for (int i=0; i<rainData.length; ++i){
            if (rainData[i]<minRain){
                minRain = rainData [i];
                index = i;
            }
        }
        int year = years [index];

        return year;
    }
}


