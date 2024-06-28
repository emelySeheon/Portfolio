import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Project is a program to analyze data in a file.
 *
 *  @author Emely Seheon
 */


public class Project_eseheon1 {
    public static void main(String[] args) {
    //Getting the file name from the user
	Scanner input = new Scanner (System.in);
	String fileName = input.nextLine();
	//Calling processFile
	processFile(fileName);
    }

    /**
     * <Reads the file and Processes it.>
     * @param //<fileName> <String with the name of the file that is being read>
     * @return <void>
     */

    public static void processFile(String fileName){
        //Creating the ArrayLists needed and putting the information from the file into them.
        ArrayList<String> lineList = new ArrayList<String>();
        ArrayList<String> wordList = new ArrayList<String>();
        try{
            File file = new File (fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                String lines = reader.nextLine();
                lineList.add(lines);
                String[] words = lines.split(" ");
                for (int i = 0; i<words.length; ++i) {
                    wordList.add(words[i]);
                }
            }
        //Making sure the file exists
        }catch(FileNotFoundException e){
            System.out.println("Error: File not found.");
            System.exit(-1);
        }

        //Creating the unique words list by calling createUniqueList
        ArrayList<String> uniqueWords = createUniqueList(wordList);
        //Outputting file info
        System.out.println("Number of Words: " + wordList.size());
        System.out.println("Number of Unique Words: " + uniqueWords.size());

        System.out.println("");
        //Sorting the unique words and outputting them
        ArrayList<String> sortedUnique = sortList(uniqueWords);
        for (int i = 0; i<sortedUnique.size(); ++i){
            System.out.println(sortedUnique.get(i));
        }

        //Calling the search method
        search(lineList);
    }

    /**
     * <Creates anArrayList with only the unique words in the file>
     * @param //<wordList> <A string ArrayList with all the words in the file>
     * @return <A String ArrayList with all the unique words in the file>
     */

    public static ArrayList<String> createUniqueList (ArrayList<String> wordList){
        ArrayList<String> uniqueWordList = new ArrayList<String>();
        //Creating a new list with no duplicated words
        for (int i = 0; i<wordList.size(); ++i){
            if (uniqueWordList.contains(wordList.get(i))){
                continue;
            }else{
                uniqueWordList.add(wordList.get(i));
            }
        }
        return uniqueWordList;
    }

    /**
     * <Sorts words in alphabetical order>
     * @param //<uniqueWordList> <List of all the unique words in the file>
     * @return <String ArrayList with the sorted words>
     */

    public static ArrayList<String> sortList (ArrayList<String> uniqueWordList){
        //Creating the new array that will have the sorted words
        ArrayList<String> alphabetical = new ArrayList<String>();
        for (int h = 0; h<uniqueWordList.size(); ++h){
            alphabetical.add(uniqueWordList.get(h));
        }

        //Sorting the words with insertion sort
        for(int i = 1; i<uniqueWordList.size(); ++i){
            String key = uniqueWordList.get(i);
            int j = i-1;

            while (j>=0 && (uniqueWordList.get(j).compareToIgnoreCase(key))< 0){
                uniqueWordList.set(j+1, uniqueWordList.get(j));
                j = j-1;
            }
            uniqueWordList.set(j+1, key);
        }

        //Putting the sorted words in the new arraylist
        for(int k = 0; k<uniqueWordList.size(); ++k){
            alphabetical.set(k, uniqueWordList.get(uniqueWordList.size()-(k+1)));
        }

        return alphabetical;
    }

    /**
     * <Searches the file for any term that the user inputs and indicates where the term is in each line>
     * @param //<lineList> <List of all the words in each line>
     * @return <void>
     */

    public static void search (ArrayList<String> lineList){
        Scanner searchObj = new Scanner(System.in);
        String searchFor = "";
        ArrayList<Integer> index = new ArrayList<Integer>();
        ArrayList<String> spaces = new ArrayList<String>();

        while (!searchFor.equals("EINPUT")){
            //Getting user input
            System.out.println("\nEnter Search Pattern: ");
            searchFor = searchObj.nextLine();
            for (int i = 0; i < lineList.size(); i++){
                spaces.clear();
                index.clear();
                lineList.set(i, lineList.get(i).toLowerCase());
                if (lineList.get(i).contains(searchFor)){

                    //Getting the index of the location of the search term
                    String line = lineList.get(i);
                    while(line.contains(searchFor)) {
                        if (index.size()>0) {
                            index.add(line.indexOf(searchFor) + index.get(index.size() - 1) + searchFor.length());
                        } else{
                            index.add(line.indexOf(searchFor));
                        }
                        line = line.substring(index.get(index.size()-1)+searchFor.length(), line.length());
                    }

                    //Printing the lines with the search term
                    System.out.println("\nLine number "+ (i+1));
                    System.out.println(lineList.get(i));

                    //Creating an ArrayList woth '^' pointing to the locations of the search key
                    for(int j = 0; j<lineList.get(i).length(); ++j){
                        spaces.add(" ");
                    }
                    for (int l = 0; l<index.size();++l) {
                        spaces.set(index.get(l), "^");
                    }

                    //Printing '^' at the location of each search key.
                    for (int k = 0; k<spaces.size(); ++k){
                        System.out.print(spaces.get(k));
                    }
                }
            }
        }
        System.out.println("Bye!");
    }

}
