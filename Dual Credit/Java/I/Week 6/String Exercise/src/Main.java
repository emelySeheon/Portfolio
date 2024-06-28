
public class Main {
    public static void main(String[] args) {
        String given = "The quick brown fox jumped over the moon!";

        String given2 = "mary";

        String given3 = "D";


        //Write a Java code snippet to check (print a boolean) whether the given String ends with the contents of "oon!", see endsWith method
        boolean snip1 = given.endsWith("oon!");
        System.out.println(snip1);

        //Write a Java code snippet to check (print a boolean) whether the given String starts with the contents of "Th", see startsWith method
        boolean snip2 = given.startsWith("Th");
        System.out.println(snip1);

        //Write a Java code snippet to check (print a boolean) whether the given2 String has the same contents as the String "mark", see equals method
        boolean snip3 = given2.equals("mark");
        System.out.println(snip3);

        //Write a Java code snippet to check (print a boolean) whether the given2 String has the same contents, ignoring case, as the String "MARY", see equalsIgnoreCase method
        boolean snip4 = given2.equalsIgnoreCase("MARY");
        System.out.println(snip4);

        //Write a Java code snippet to print all the indices where the letter 'o' is located at in the given String. See indexOf method and use a while loop.
        int i = given.indexOf("o");
        while(i >= 0) {
            System.out.println((i));
            i=given.indexOf("o", i+1);
        }

        //Write a Java code snippet to print the substring of the given String from indices 3 through 8. See substring method.
        System.out.println(given.substring(3,8));

        //Write a Java code snippet to compare the given3 String with the String "Z" using the compareTo method. Print the integer that is
        //returned from the compareTo method.
        int snip7 = given3.compareTo("Z");
        System.out.println(snip7);

        //Write a Java code snippet to compare the given3 String with the String "A" using the compareTo method. Print the integer that is
        //returned from the compareTo method.
        int snip8 = given3.compareTo("A");
        System.out.println(snip8);

        //Write a Java code snippet to compare the given3 String with the String "D" using the compareTo method. Print the integer that is
        //returned from the compareTo method.
        int snip9 = given3.compareTo("D");
        System.out.println(snip9);
    }

}